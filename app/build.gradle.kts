plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

}
// Applying it manually at the end prevents it from locking dependencies too early
apply(plugin = "com.google.gms.google-services")

// This forces the "debugCompileClasspath" to stay open longer
project.configurations.configureEach {
    if (name.contains("CompileClasspath")) {
        resolutionStrategy.activateDependencyLocking()
    }
}
configurations.all {
    resolutionStrategy {
        // This forces Gradle to pick one version and NOT let plugins change it later
        force("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
        force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
        force("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    }
}
android {
    namespace = "com.example.madhumargaa"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.madhumargaa"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/INDEX.LIST"
            // You may also need these for Netty/Logback projects:
            excludes += "/META-INF/io.netty.versions.properties"
            excludes += "/META-INF/logback.xml"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10" // Matches Kotlin 1.9.22
    }
}

dependencies {
    // Import the BOMs
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    // Android Core & Lifecycle
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

    // Compose UI
    val compose_version = "1.6.0"
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.2.0")

    // Navigation & ViewModel
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Firebase
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Icons & Testing
    implementation("androidx.compose.material:material-icons-extended:1.6.3")
    testImplementation("junit:junit:4.13.2")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("io.ktor:ktor-server-core:2.3.11")
    implementation("io.ktor:ktor-server-netty:2.3.11")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.11")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.11")
    implementation("ch.qos.logback:logback-classic:1.4.7")
}