package com.example.keyboardlistener

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class KeyboardListenerLivedata(
    private val root: View, private val minKeyboardHeight: Int = 0
) : LiveData<KeyboardListenerLivedata.Status>() {
    sealed class Status(val height: Int? = null) {
        class Open(height: Int) : Status(height)
        class Closed() : Status()
    }

    private val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val displayRect = Rect().apply { root.getWindowVisibleDisplayFrame(this) }
        val keypadHeight = root.height - displayRect.bottom
        if (keypadHeight > minKeyboardHeight) {
            setDistinctValue(Status.Open(keypadHeight))
        } else {
            setDistinctValue(Status.Closed())
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in Status>) {
        super.observe(owner, observer)
        observersUpdated()
    }

    override fun observeForever(observer: Observer<in Status>) {
        super.observeForever(observer)
        observersUpdated()
    }

    override fun removeObservers(owner: LifecycleOwner) {
        super.removeObservers(owner)
        observersUpdated()
    }

    override fun removeObserver(observer: Observer<in Status>) {
        super.removeObserver(observer)
        observersUpdated()
    }

    private fun setDistinctValue(newValue: Status) {
        if (value?.height != newValue.height) {
            value = newValue
        }
    }

    private fun observersUpdated() {
        if (hasObservers()) {
            root.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        } else {
            root.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        }
    }
}