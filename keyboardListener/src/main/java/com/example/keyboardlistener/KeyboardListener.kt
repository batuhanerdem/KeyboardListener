package com.example.keyboardlistener

import android.view.View
import androidx.lifecycle.LiveData

class KeyboardListener(root: View? = null, minKeyboardHeight: Int = 0) {
    private val listener = KeyboardListenerLivedata(root!!, minKeyboardHeight)

    fun getListener(): LiveData<Status> {
        return listener
    }
}