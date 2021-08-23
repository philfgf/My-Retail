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
//        classpath("com.target.platform:platform-connector-gradle:2.2.8"){
//            exclude(group = "org.springframework.boot")
//        }
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.5.1")
    }
}

plugins {
    val springVersion = "2.0.1.RELEASE"
    val kotlinVersion = "1.5.10"
    application
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.springframework.boot") version springVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
}

allprojects {
    val ktlint by configurations.creating
    val springVersion = "2.0.1.RELEASE"
    val kotlinVersion = "1.5.10"

    repositories {
        // Use jcenter for resolving dependencies.
        // You can declare any Maven/Ivy/file repository here.
        maven("https://binrepo.target.com/artifactory/TargetOSS")
        maven("https://binrepo.target.com/artifactory/eno-release")
        jcenter()
    }

    dependencies {
        kotlin(module = "stdlib-jdk8", version =  kotlinVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-web", version = springVersion) {
            exclude(module = "spring-boot-starter-tomcat")
        }
//        implementation(group = "org.springframework.boot", name = "spring-boot-gradle-plugin", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-jetty", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator", version = springVersion)
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-webflux", version = springVersion)
        implementation(kotlin("stdlib-jdk8"))
        implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.12.3")
//        implementation(group = "com.target.platform", name = "platform-connector-gradle", version = "2.2.8")
        implementation(group = "org.apache.logging.log4j", name = "log4j-api", version = "2.14.1")
        implementation(group = "org.mongodb", name = "mongo-java-driver", version = "3.6.3")
        implementation(group = "org.mongodb", name = "mongodb-driver-core", version = "3.6.3")
        implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-mongodb", version = springVersion)
        implementation(group = "org.mongodb", name = "bson", version = "3.6.3")

        testImplementation(kotlin("test-junit"))
        testImplementation(group = "org.springframework.boot", name = "spring-boot-starter-test", version = springVersion)

        ktlint("com.pinterest:ktlint:0.41.0")
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

    apply{
//        plugin("com.target.platform.connector.spring-boot-webmvc")
        plugin("kotlin")
    }

    configurations.all {
        exclude (module = "spring-boot-starter-logging")
        exclude(module = "spring-boot-starter-tomcat")
    }

    application {
        mainClassName = "com.target.myRetail.MyRetailKt"
    }

    val jar: Jar by tasks
    jar.enabled = true

    group = "com.target"
    version = "1.0-SNAPSHOT"

    tasks{
        withType<KotlinCompile>{
            kotlinOptions{
                jvmTarget = JavaVersion.VERSION_11.toString()
            }
        }
    }
}
