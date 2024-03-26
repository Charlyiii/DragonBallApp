plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    val coroutinesVersion = "1.7.3"

    // Dagger Hilt
    implementation("javax.inject:javax.inject:1")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    //Testing
    testImplementation("junit:junit:4.13.2")
}