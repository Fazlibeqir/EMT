
i don't have a docker compose file for kafka and zookeeper, but you can download it from the official website.
https://kafka.apache.org/downloads
kafka_2.13-3.7.0.tgz download this file and extract it.

### First you need to start zookeeper and then kafka.
# Start zookeeper
Open a new Command Prompt window.


```bash 
cd /d F:/kafka

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
This will start Zookeeper with the default settings. Leave this command prompt open.

# Start Kafka
Open a new Command Prompt window.
```bash
cd /d F:/kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties
```