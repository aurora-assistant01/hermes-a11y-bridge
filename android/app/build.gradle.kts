plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hermes.a11ygo"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.hermes.a11ygo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        release { signingConfig = signingConfigs.getByName("debug"); isMinifyEnabled = false }
    }
}

afterEvaluate {
    apply(plugin = "dev.flutter.flutter-gradle-plugin")
}

flutter { source = "../" }

dependencies { implementation("androidx.core:core:1.12.0") }
