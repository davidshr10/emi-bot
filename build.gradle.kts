import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = "1.3.11"
val spekVersion = "2.1.0-alpha.0.3+c70a3c3"
val mockkVersion = "1.9"

plugins {
    kotlin("jvm") version ("1.3.11")
}

group = "dev.davidsth"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/spekframework/spek-dev/")
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))

    compile("net.dv8tion:JDA:3.8.1_437")
    compile("com.fasterxml.jackson.core:jackson-databind:2.9.8")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
    compile("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.9.8")
    compile("org.reflections:reflections:0.9.11")


    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

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

apply {
    plugin("kotlin")
    plugin("application")
}

configure<ApplicationPluginConvention> {
    mainClassName = "dev.davidsth.bot.MainAppKt"
    applicationDefaultJvmArgs = listOf("-Dprofiles.active=prod")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }

        /* This configuration was found here:
           https://github.com/tieskedh/KotlinPoetDSL/blob/master/build.gradle.kts
         */
        testLogging {
            // set options for log level LIFECYCLE
            events = setOf(FAILED, PASSED, SKIPPED, STANDARD_OUT)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true

            // set options for log level DEBUG and INFO
            debug {
                events = setOf(STARTED, FAILED, PASSED, SKIPPED, STANDARD_ERROR, STANDARD_OUT)
                exceptionFormat = TestExceptionFormat.FULL
            }
            info {
                events = debug.events
                exceptionFormat = debug.exceptionFormat
            }
        }

        addTestListener(object : TestListener {
            override fun beforeTest(p0: TestDescriptor?) = Unit
            override fun beforeSuite(p0: TestDescriptor?) = Unit
            override fun afterTest(desc: TestDescriptor, result: TestResult) = Unit
            override fun afterSuite(desc: TestDescriptor, result: TestResult) {
                printResults(desc, result)
            }
        })
    }
}

fun printResults(desc: TestDescriptor, result: TestResult) {
    if (desc.parent != null) {
        val output = result.run {
            "Results: $resultType (" +
                "$testCount tests, " +
                "$successfulTestCount successes, " +
                "$failedTestCount failures, " +
                "$skippedTestCount skipped" +
                ")"
        }
        val testResultLine = "|  $output  |"
        val repeatLength = testResultLine.length
        val seperationLine = "-".repeat(repeatLength)
        println(seperationLine)
        println(testResultLine)
        println(seperationLine)
    }
}