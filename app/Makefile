.DEFAULT_GOAL := build-run

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain checkstyleTest

update-deps:
	./gradlew useLatestVersions


build-run: build run

.PHONY: build


##run-dist:
##	./build/install/app/bin/app
help:
	./build/install/app/bin/app -h
version:
	./build/install/app/bin/app -V
run-default:
	./build/install/app/bin/app /home/john/gitDir/java-project-71/app/src/main/resources/file1.yaml /home/john/gitDir/java-project-71/app/src/main/resources/file2.yaml
run-plain:
	./build/install/app/bin/app /home/john/gitDir/java-project-71/app/src/main/resources/file1.yaml /home/john/gitDir/java-project-71/app/src/main/resources/file2.yaml -f=plain
run-json:
	./build/install/app/bin/app /home/john/gitDir/java-project-71/app/src/main/resources/file1.yaml /home/john/gitDir/java-project-71/app/src/main/resources/file2.yaml -f=json

##test:
##	./gradlew test
##report:
##	./gradlew jacocoTestReport
##build:
##	./gradlew build

##.PHONY: build
##.PHONY: test
