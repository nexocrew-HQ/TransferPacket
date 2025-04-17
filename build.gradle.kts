import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    kotlin("jvm") version "2.0.20-Beta1"
    id("com.gradleup.shadow") version "9.0.0-beta8"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("de.eldoria.plugin-yml.paper") version "0.7.0"
    kotlin("plugin.serialization") version "2.1.0"
    id("maven-publish")
}

val mcVersion = properties["minecraftVerions"] as String
val projectVersion = properties["version"] as String
val projectName = properties["name"] as String
val groupID = properties["group"] as String
val mainClass = properties["main"] as String
val projectDescription = properties["description"] as String
val twilightVersion = properties["twilightVersion"] as String
val commandAPIVersion = properties["commandAPIVersion"] as String

group = groupID
version = projectVersion

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.flyte.gg/releases")
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Paper
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    // Twilight
    implementation("gg.flyte:twilight:${twilightVersion}")

    // Command API
    compileOnly("dev.jorel:commandapi-bukkit-core:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-bukkit-shade-mojang-mapped:${commandAPIVersion}")
    implementation("dev.jorel:commandapi-bukkit-kotlin:${commandAPIVersion}")
}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks {
    runServer {
        minecraftVersion(mcVersion)
    }
}

publishing {
    repositories {
        maven {
            name = "Reposilite"
            url = uri("https://repo.nexocrew.com/releases")
            credentials {
                username = System.getenv("REPOSILITE_USER") ?: System.getProperty("REPOSILITE_USER") ?: "USERNAME"
                password = System.getenv("REPOSILITE_TOKEN") ?: System.getProperty("REPOSILITE_TOKEN") ?: "TOKEN"
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("reposilite") {
            from(components["java"])
            artifactId = "transferpacket"
            groupId = groupID
            version = projectVersion
        }
    }
}

paper {
    name = projectName
    version = version
    description = projectDescription
    main = mainClass
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    authors = listOf("jesforge")
    apiVersion = "1.21"
}
