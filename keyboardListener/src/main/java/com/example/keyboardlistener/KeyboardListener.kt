package com.example.keyboardlistener

import android.view.View

class KeyboardListener(root: View, minKeyboardHeight: Int = 0) {
    val listener = KeyboardListenerLivedata(root, minKeyboardHeight)
}