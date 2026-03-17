plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("jacoco")
    id("io.gitlab.arturbosch.detekt")
}

jacoco {
    toolVersion = "0.8.12"
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

android {
    namespace = "com.example.unittesting"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.unittesting"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            enableUnitTestCoverage = true
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(kotlin("test"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.6")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

val jacocoTestReport by tasks.registering(JacocoReport::class) {
    dependsOn("testDebugUnitTest")

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    // ✅ Exclusions
    val fileFilter = listOf(
        "**/R.class",
        "**/R${'$'}*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/*${'$'}[0-9]*.*",
        "**/Lambda${'$'}*.class",
        "**/Lambda.class",
        "**/*Lambda.class",
        "**/*Lambda*.class",
        "**/*_LifecycleAdapter.*",
        "**/DataBinderMapperImpl.*",
        "**/DataBindingInfo.*",
        "**/*_ViewBinding*.*",
        "**/Dagger*.*",
        "**/Dagger*Component*.*",
        "**/Dagger*Component${'$'}Builder.*",
        "**/*Module_*.*",
        "**/*_Factory.*",
        "**/*_Provide*Factory.*",
        "**/*_MembersInjector.*",
        "**/*_MembersInjector${'$'}*.*",
        "**/*_Factory${'$'}*.*",
        "**/*_Provide*Factory${'$'}*.*",
        "**/*_ViewBinding.*"
    )

    // ✅ Kotlin compiled classes
    val kotlinClasses = fileTree("${buildDir}/tmp/kotlin-classes/debug") {
        exclude(fileFilter)
    }

    // ✅ Java compiled classes
    val javaClasses = fileTree("${buildDir}/intermediates/javac/debug/classes") {
        exclude(fileFilter)
    }

    // ✅ Combine both
    classDirectories.setFrom(files(kotlinClasses, javaClasses))

    // ✅ Source directories
    sourceDirectories.setFrom(
        files(
            "src/main/java",
            "src/main/kotlin"
        )
    )

    // ✅ Execution data (FIXED)
    executionData.setFrom(
        fileTree(buildDir) {
            include(
                "outputs/unit_test_code_coverage/debugUnitTest/*.exec",
                "jacoco/*.exec"
            )
        }
    )
}