plugins {
    id("java")
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    testImplementation("org.assertj:assertj-core:3.22.0")
    }

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "hexlet.code.App"
}

