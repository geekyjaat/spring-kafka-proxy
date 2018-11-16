# Spring Kafka Proxy
This is a demo project to help get started with Spring Boot + Kafka Integration.

## Technologies used
1. [Apache Kafka](http://kafka.apache.org)
2. [Spring Boot](https://spring.io/projects/spring-boot)
3. Microsoft Visual Studio Code ([VS Code](https://code.visualstudio.com)) + [Spring Support](https://code.visualstudio.com/docs/java/java-spring-boot)
4. MacOS terminal
5. [Gradle](https://gradle.org)

## Things we are going to learn -
1. Run a kafka server locally
2. Create a gradle + Spring project using VS Code's Spring support
3. Set up Spring Kafka Integration
4. Produce messages to Kafka using a endpoint.
5. Set up a Kafka consumer in our app to listen to produced messages

## Running Kafka locally
So let us get started. Go to this link - [Apache Kafka](http://kafka.apache.org) and go to download link to get the latest version to Apache Kafka. Now, extract the archive into new folder.

### Running Zookeeer
1. In your terminal, go to your new folder and `cd` into the `bin` folder.
2. As Apache kafka uses zookeeperm we need to first start the zookeeper server.
3. Use this command to start our zookeeper server - `./zookeeper-server-start.sh ../config/zookeeper.properties` from your bin folder.
4. This command provides the default properties from `config` folder to the start command and our zookeeper server will start and will be listening on port 2181.
> if you are on windows, there is a windows folder in bin directory where we same commands present for windows.

### Running Kafka
Now, use this command to start the kafka server - `./kafka-server-start.sh ../config/server.properties`. Similarly, we are providing the default properties that came with archive to start our server. The Kafka server will be listening on port 9092 on localhost.

So now we have our servers up and running, go to next step.

## Java Spring Project Using VS Code
Let us open the VS code application now. We will use its Spring Initializr support to create a spring project with gradle. Here are the steps -
1. With a window in VS Code open, press `Shift + CMD + P` ([Key Map reference](https://code.visualstudio.com/docs/getstarted/keybindings#_keyboard-shortcuts-reference)). It will open the vs code command window.
2. If you have installed the spring support extension in VS Code (If not, go [here](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-spring-initializr) to see how), you will see a option `Spring Initializr: Generate a gradle project`. Go to that using arrows and press enter Or click on it.
3. Next, it will ask for which language, select `Java` and continue.
4. Now, provide it a group id value for your project - for example - `com.example.demo`.
5. Provide artifact id for your project.
6. Now, select the Spring Boot Version - I am using `2.1.0`.
7. Now select your dependencies - Kafka and Web. Click enter and save your project.
8. VS Code will open up your project and you are ready to go now.

## Setting up properties
TODO

## The Code
### Admin
TODO
### Producer
TODO
### Consumer
TODO

## See it in Action
### Create a topic
TODO
### Produce & Consumer Messages
TODO
