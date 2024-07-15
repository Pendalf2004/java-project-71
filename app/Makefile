run-dist:
	./build/install/app/bin/app
help:
	./build/install/app/bin/app -h
version:
	./build/install/app/bin/app -V
run-default:
	./build/install/app/bin/app /home/john/gitDir/java-project-71/app/src/test/resources/file1.java /home/john/gitDir/java-project-71/app/src/test/resources/file2.java

.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.5

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

build-run: build run

.PHONY: build