package com.example.keyboardlistener

import KeyboardListenerStateFlow
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.StateFlow

class KeyboardListener(root: View, minKeyboardHeight: Int = 0) {
    val asLivedata: LiveData<Status> by lazy {
        KeyboardListenerLivedata(root, minKeyboardHeight)
    }

    val asStateFlow: StateFlow<Status> by lazy {
        KeyboardListenerStateFlow(root).statusFlow
    }

}

