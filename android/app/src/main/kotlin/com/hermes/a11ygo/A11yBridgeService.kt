package com.hermes.a11ygo

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class A11yBridgeService : AccessibilityService() {
    override fun onServiceConnected() {}
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}
    companion object {
        fun isServiceActive(): Boolean = false
        fun tapScreen(ctx: Context, x: Int, y: Int): Boolean = false
        fun back(ctx: Context): Boolean = false
    }
}
