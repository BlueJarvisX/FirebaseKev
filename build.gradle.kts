// Top-level Gradle configuration

plugins {
    // Plugin de Google Services (Firebase)
    id("com.google.gms.google-services") version "4.4.4" apply false

    // Plugin Android de aplicación (ACTUALIZADO)
    id("com.android.application") version "8.9.1" apply false

    // Plugin Android de librería (por si lo necesitas)
    id("com.android.library") version "8.9.1" apply false
}
