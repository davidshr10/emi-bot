import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.11"
val spekVersion = "2.0.0"

plugins {
    kotlin("jvm") version("1.3.11")
}

group = "dev.davidsth"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("net.dv8tion:JDA:3.8.1_437")
    compile("com.fasterxml.jackson.core:jackson-databind:2.7.1-1")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.7.1-2")
    compile("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.7.1")

    testCompile("junit:junit:4.12")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion") {
        exclude("org.junit.platform")
        exclude("org.jetbrains.kotlin")
    }
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

apply {
    plugin("kotlin")
    plugin("application")
}

configure<ApplicationPluginConvention> {
    mainClassName = "dev.davidsth.bot.MainAppKt"
    applicationDefaultJvmArgs = listOf("-Dprofiles.active=prod")
}