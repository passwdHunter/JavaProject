plugins {
	java
	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.psu"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.0")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.flywaydb:flyway-core:10.15.0")
	implementation("org.flywaydb:flyway-database-postgresql:10.15.0")
	compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")
	runtimeOnly("org.postgresql:postgresql:42.7.3")
	testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
}
tasks.withType<Test> {
	useJUnitPlatform()
}

