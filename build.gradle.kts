import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.11"
val spekVersion = "2.0.0"
val mockkVersion = "1.9"

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
    compile("com.fasterxml.jackson.core:jackson-databind:2.9.8")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    compile("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.8")

    testImplementation ("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")  {
        exclude ("org.jetbrains.kotlin")
    }
    testRuntimeOnly ("org.spekframework.spek2:spek-runner-junit5:$spekVersion") {
        exclude("org.junit.platform")
        exclude("org.jetbrains.kotlin")
    }

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    compile(kotlin("reflect"))

    testImplementation("io.mockk:mockk:$mockkVersion")
    //mock lib
    testCompile("org.mockito:mockito-inline:2.24.5")

    // expect matchers
    testCompile("net.oddpoet:kotlin-expect:1.2.1")


    //logger
    compile("org.apache.logging.log4j:log4j-api:2.11.2")
    compile("org.apache.logging.log4j:log4j-core:2.11.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

apply {
    plugin("kotlin")
    plugin("application")
}

configure<ApplicationPluginConvention> {
    mainClassName = "dev.davidsth.bot.MainAppKt"
    applicationDefaultJvmArgs = listOf("-Dprofiles.active=prod")
}