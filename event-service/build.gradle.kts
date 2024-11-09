plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "ca.gbc"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(22)
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

	// Spring Boot Support
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Mongo Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
	testImplementation("org.testcontainers:testcontainers")
//	testImplementation("org.testcontainers:postgresql")

	// jUnit Testing
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-engine")

	// No Dependency on JPA
//	testImplementation(project(":user-service"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// ----- Disabling bootJar; not runnable Spring Boot Service -----
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}


//	runtimeOnly("org.postgresql:postgresql")
//	testImplementation("io.rest-assured:rest-assured:5.2.0")
//	testRuntimeOnly("org.junit.platform:junit-platform-launcher")