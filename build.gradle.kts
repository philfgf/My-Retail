import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        maven("https://binrepo.target.com/artifactory/platform")
        maven("https://binrepo.target.com/artifactory/jcenter")
        maven("https://binrepo.target.com/artifactory/gradle")
        maven("https://binrepo.target.com/artifactory/maven-central")
        jcenter()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.target.platform:platform-connector-gradle:2.2.8"){
            exclude(group = "org.springframework.boot")
        }
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.5.1")
    }
}

plugins {
    val springVersion = "2.5.1"
    val kotlinVersion = "1.5.10"
    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.springframework.boot") version springVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
}

allprojects {
    group = "com.target"
    version = "1.0-SNAPSHOT"

    repositories {
        jcenter()
        mavenCentral()
    }

    val springVersion = "2.5.1"
    val kotlinVersion = "1.5.10"
    val ktlint by configurations.creating

    dependencies {
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-gradle-plugin", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-jetty", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux", version = springVersion)
        implementation(group = "org.apache.httpcomponents", name = "httpclient", version = "4.5.13")
        implementation(group = "org.apache.logging.log4j", name = "log4j-api", version = "2.14.1")
        implementation(group = "org.springframework.data", name = "spring-data-mongodb", version = "3.2.4")
        implementation(group = "javax.xml.bind", name = "jaxb-api", version = "2.3.1")

        kotlin(module = "stdlib-jdk8", version =  kotlinVersion)

        testImplementation(kotlin("test-junit"))
        testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test", version = springVersion)

        ktlint("com.pinterest:ktlint:0.41.0")

    }

    configurations.all {
        exclude (module = "spring-boot-starter-logging")
        exclude(module = "spring-boot-starter-tomcat")
    }

    tasks.register<JavaExec>("ktlint") {
        group = "verification"
        description = "Check Kotlin code style."
        main = "com.pinterest.ktlint.Main"
        classpath = ktlint
        args = listOf("src/**/*.kt")
        val check by tasks
        check.dependsOn(this)
    }

    tasks.register<JavaExec>("ktlintFormat") {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        main = "com.pinterest.ktlint.Main"
        classpath = ktlint
        args = listOf("-F", "src/**/*.kt")
    }

    tasks{
        withType<KotlinCompile>{
            kotlinOptions{
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }

    application {
        mainClassName = "com.target.myRetail.MyRetailKt"
    }
}