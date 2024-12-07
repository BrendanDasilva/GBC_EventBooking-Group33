plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "ca.gbc"
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
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("jakarta.persistence:jakarta.persistence-api:3.0.0")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// No Dependency on JPA
//	testImplementation(project(":user-service"))
}

tasks.bootJar{
	mainClass.set("ca.gbc.eventservice.EventServiceApplication")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// ----- Disabling bootJar; not runnable Spring Boot Service -----
//tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
//	enabled = false
//}


//	runtimeOnly("org.postgresql:postgresql")
//	testImplementation("io.rest-assured:rest-assured:5.2.0")
//	testRuntimeOnly("org.junit.platform:junit-platform-launcher")