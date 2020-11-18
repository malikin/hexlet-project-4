.DEFAULT_GOAL := build-run
DATE_WITH_TIME := $(shell /bin/date "+%Y%m%d%H%M%S")
LABEL?=changeset

run:
	java -jar ./target/hexlet-project-4-*.jar

clean:
	rm -rf ./target

build-run: build run

build:
	./mvnw clean package

update:
	./mvnw versions:update-properties versions:display-plugin-updates

test:
	./mvnw clean test

generate-migration:
	./mvnw clean compile liquibase:update liquibase:diff -DskipTests=true -DdiffChangeLogFile=./src/main/resources/db/changelog/changesets/${DATE_WITH_TIME}-${LABEL}.yaml \
 	&& rm /tmp/liquibase_migration*
