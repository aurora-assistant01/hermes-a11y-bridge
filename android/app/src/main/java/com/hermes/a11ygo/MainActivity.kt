package com.hermes.a11ygo

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.hermes.a11y/bridge"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "tap" -> {
                    val x = call.argument<Int>("x") ?: 0
                    val y = call.argument<Int>("y") ?: 0
                    result.success(A11yBridgeService.tapScreen(this, x, y))
                }
                "back" -> result.success(A11yBridgeService.back(this))
                "startService" -> result.success(runCatching { startService(Intent(this, A11yBridgeService::class.java)); true }.getOrDefault(false))
                "stopService" -> result.success(runCatching { stopService(Intent(this, A11yBridgeService::class.java)); true }.getOrDefault(false))
                "serviceStatus" -> result.success(A11yBridgeService.isServiceActive())
                "openAccessibilitySettings" -> result.success(runCatching { startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); true }.getOrDefault(false))
                else -> result.notImplemented()
            }
        }
    }
}
