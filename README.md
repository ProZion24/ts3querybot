# TS3-Querybot

This is a open-source TeamSpeak-3 Querybot  

- **Something's broken or it's complicated?** [Open an issue](https://github.com/ProZion24/ts3querybot/issues/new/choose)
  - Please keep issues in english, this makes it easier for everyone to participate and keeps issues relevant to link to.

## Features
* Webinterface to manage configurations, banns and other administration stuff
* Supportmanager
* implemented commandmanager, so you can add your own commands easily

## Bot Commands
The bot is fully operable via chat and the webinterface. 
To get started write `!help` to the bot.

## Download
* Download the bot from [releases](https://github.com/ProZion24/ts3querybot/releases/)

## Install

#### Linux
1. You need to install Java-8 JRE on your machine
1. Update the repositories: `sudo apt-get update`
1. Install OpenJDK: `sudo apt-get install openjdk-8-jdk`
1. Verify the version of the JDK: `java -version`
1. The output shoud be like this:
```
openjdk version "1.8.0_242"
OpenJDK Runtime Environment (build 1.8.0_242-b09)
OpenJDK 64-Bit Server VM (build 25.242-b09, mixed mode)
```
* then you have to execute `java -jar ts3querybot.jar`

#### Windows
1. Download JRE from [here](https://www.java.com/de/download/)
1. Install the JRE with it's installer
1. then you have to execute `java -jar ts3querybot.jar`
## TODO 
* Webinterface
* Fix NullPointerException in SupportCommand