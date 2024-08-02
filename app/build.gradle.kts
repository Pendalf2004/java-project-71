plugins {
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
}

