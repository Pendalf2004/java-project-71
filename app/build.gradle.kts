import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    jacoco
    id ("checkstyle")
    id ("com.adarshr.test-logger") version "3.0.0"
    id ("com.github.ben-manes.versions") version "0.39.0"
}
application {
    mainClass = "hexlet.code.App"
}
group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStackTraces = true
        showCauses = true
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}

dependencies {
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation(platform("org.junit:junit-bom:5.11.0-M1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0-M1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation ("info.picocli:picocli:4.7.6")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0-rc1")
}
/*plugins {
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

checkstyle {
    toolVersion = "10.12.4"
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}

dependencies {

    checkstyle ("com.puppycrawl.tools:checkstyle:${checkstyle.toolVersion}")

 //   checkstyle ("group:artifact:version")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.22.0")
    implementation ("info.picocli:picocli:4.7.6")
    implementation("org.jacoco:jacoco-maven-plugin:0.8.12")
    implementation("org.apache.maven.reporting:maven-reporting-api:4.0.0-M12")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.2")
    }

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
        //xml.outputLocation = file(
        //    application.executableDir + "build/reports/jacoco/test/" + "jacocoTestCoverageReport")
        csv.required = false
        html.required = false
    }
}

application {
    mainClass = "hexlet.code.App"
}*/
