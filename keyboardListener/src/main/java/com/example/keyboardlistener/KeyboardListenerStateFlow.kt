import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import com.example.keyboardlistener.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KeyboardListenerStateFlow internal constructor(
    private val root: View, private val minKeyboardHeight: Int = 0
) {

    private val _statusFlow = MutableStateFlow<Status>(Status.Closed())
    val statusFlow: StateFlow<Status> = _statusFlow

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val displayRect = Rect().apply { root.getWindowVisibleDisplayFrame(this) }
        val keypadHeight = root.height - displayRect.bottom
        if (keypadHeight > minKeyboardHeight) {
            setDistinctValue(Status.Open(keypadHeight))
        } else {
            setDistinctValue(Status.Closed())
        }
    }

    init {
        root.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    private fun setDistinctValue(newValue: Status) {
        if (_statusFlow.value.height != newValue.height) {
            _statusFlow.value = newValue
        }
    }

    fun removeListener() {
        root.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
    }
}
