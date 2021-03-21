import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.stplab.dictionarywords"
    const val compile_sdk = 30
    const val min_sdk = 21
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    // Features
    const val historyScreen = ":historyScreen"
    const val favoritesScreen = ":favoritesScreen"
    const val descriptionScreen = ":descriptionScreen"
}

object Versions {
    // Tools
    const val multidex = "1.0.3"

    // Design
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val constraintLayout = "2.0.4"
    const val swipeRefreshLayout = "1.1.0"
    const val androidx = "1.3.0-alpha04"

    // Kotlin
    const val core = "1.3.2"
    const val stdlib = "1.4.31"
    const val coroutinesCore = "1.3.8"
    const val coroutinesAndroid = "1.4.2"


    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val interceptor = "4.9.0"
    const val adapterCoroutines = "0.9.2"

    // Koin
    const val koinAndroid = "2.0.1"
    const val koinViewModel = "2.0.1"

    // Picasso
    const val picasso = "2.71828"

    // Room
    const val roomKtx = "2.2.6"
    const val runtime = "2.2.6"
    const val roomCompiler = "2.2.6"

    // Test
    const val jUnit = "4.13.2"
    const val runner = "1.2.0"
    const val espressoCore = "3.3.0"

    //Google Play
    const val googlePlayCore = "1.6.3"
}


object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidx = "androidx.activity:activity-ktx:${Versions.androidx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"

}

object Retrofit2 {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapterCoroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.adapterCoroutines}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val koinAndroid = "org.koin:koin-android:${Versions.koinAndroid}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koinViewModel}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtx}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object GooglePlay {
    const val googlePlayCore = "com.google.android.play:core:${Versions.googlePlayCore}"
}
