plugins {
    alias(libs.plugins.android.application) // Плагин Android Application (собираем APK):
    alias(libs.plugins.kotlin.android) // Поддержка Kotlin для Android
    id("androidx.navigation.safeargs") // SafeArgs — безопасная передача аргументов между фрагментами
    id("org.jetbrains.kotlin.plugin.parcelize") // @Parcelize для удобной сериализации объектов
    id("kotlin-kapt")  // Kotlin Annotation Processing Tool (для Room, Dagger и др.)
    id("com.google.devtools.ksp")  // KSP (Kotlin Symbol Processing) — более быстрая альтернатива kapt
}

android {
    namespace = "com.example.notes" // Пространство имён приложения (package)
    compileSdk = 36 // Версия SDK, с которой компилируем проект

    defaultConfig {
        applicationId = "com.example.notes" // ID приложения
        minSdk = 28 // Минимальная версия Android
        targetSdk = 36 // Целевая версия Android
        versionCode = 1 // Код версии (для Play Market)
        versionName = "1.0" // Человеческая версия

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Раннер для UI-тестов
    }

    buildTypes {
        release {
            isMinifyEnabled = false // В релизе отключена оптимизация (ProGuard / R8)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // стандартный файл правил
                "proguard-rules.pro" // кастомные правила оптимизации
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Совместимость с Java 11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11" // Целевая версия JVM
    }
    buildFeatures {
        viewBinding = true // Включаем ViewBinding для удобной работы с layout-ами
    }
}

dependencies {
    // Базовые библиотеки Android
    implementation(libs.androidx.core.ktx) // Kotlin-расширения для Android API
    implementation(libs.androidx.appcompat) // Совместимость с более старыми версиями Android
    implementation(libs.material)  // Material Design компоненты
    implementation(libs.androidx.activity) // Activity KTX
    implementation(libs.androidx.constraintlayout) // ConstraintLayout для разметки

    // Навигация
    implementation(libs.androidx.navigation.fragment.ktx) // Навигация во фрагментах
    implementation(libs.androidx.navigation.ui.ktx) // Навигация в UI (ActionBar, BottomNav и т.д.)

    // Тесты
    testImplementation(libs.junit) // Unit-тесты
    androidTestImplementation(libs.androidx.junit) // Инструментальные тесты
    androidTestImplementation(libs.androidx.espresso.core) // Espresso UI-тесты

    // Навигация (ручное указание версии)
    val navVersion = "2.9.3"
    implementation("androidx.navigation:navigation-fragment:$navVersion") // Фрагменты
    implementation("androidx.navigation:navigation-ui:$navVersion") // UI-компоненты для навигации

    // Glide — загрузка и кеширование изображений
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // CircleIndicator — индикатор для ViewPager
    implementation("me.relex:circleindicator:2.1.6")

    // Lottie — анимации в формате JSON
    implementation("com.airbnb.android:lottie:6.0.0")

    // ViewPager2 — пейджинг по экранам
    implementation("androidx.viewpager2:viewpager2:1.1.0-beta01")

    // Room (база данных)
    val roomVersion = "2.8.0"

    implementation("androidx.room:room-runtime:$roomVersion") // Room runtime
    ksp("androidx.room:room-compiler:$roomVersion") // Компилятор Room (через KSP)
}
