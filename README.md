<img src="https://i.ibb.co/LddJWs1M/image-7.png"/>

## Features

- Custom join/quit messages  
- New player detection  
- Update checker system to notify about plugin updates  

## Requirements

- Minecraft / Spigot 1.21+  
- Java 17+ (compatible with your Spigot server)

## Installation

1. Download the latest release JAR file (`WelcomeMessage-1.0.1.jar`) from the [Releases](https://github.com/MatthieuClaessens/WelcomeMessage/releases).  
2. Place the JAR file in your server’s `plugins` folder.  
3. Start or reload your Minecraft server.

## Configuration

Edit the `config.yml` file in the plugin folder to customize messages and settings.

## Maven Build

If you want to build the plugin from source using Maven:
```yaml
mvn clean package
```
This will produce the JAR in the `target/` directory.

Make sure your `pom.xml` contains relevant metadata, for example:

