import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
}

group = "org.davidsth"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("net.dv8tion:JDA:3.8.1_437")

    testCompile("junit:junit:4.12")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}