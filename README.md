This simple application connects to Twitter Streaming API 1.1 and filters streaming data. The aim is to visualize the filtered data to the user. 

## Features ##
+ The application connects to Twitter Streaming API 1.1, using the following values:
    + Consumer Key: `*insertConsumerKey*`
    + Consumer Secret: `*insertConsumerSecret`
    + The file 'authenticator.txt' contains the required link for the access to the API.
 * The app name is `java-exercise`
+ It retrieves the incoming messages for 30 seconds or up to 100 messages, whichever comes first.
+ It returns the messages grouped by user. Users are sorted chronologically in ascending order.
+ For each user, the messages per user is also  sorted chronologically in ascending order
+ It filters the messages that contains the word "bieber"
+ Each message contains 
    + The message ID
    + The creation date of the message as epoch value
    + The text of the message
    + The author of the message
+ Each author information contains 
    + The user ID
    + The creation date of the user as epoch value
    + The name of the user
    + The screen name of the user
+ All the above information is provided both in Standard output and in a log file, called `output.txt`. The user receives the output in both format. 
+ The application can run as a Docker container. 
+ The application keeps track of messages per second statistics across multiple runs of the application. The results are written in `TweetStatistics.txt` file.

## Application Installation in Docker ##
To run the application in docker, one must install Docker first. After the installation of Docker, install jar file of the application, as `bieber-tweets-1.0.0-SNAPSHOT.jar`, (Maven can be used to install it) and place it under `target` folder (target folder is placed in root directory of the project). 
Precisement both in naming and in path selection is important since the Dockerfile contains `COPY target/bieber-tweets-1.0.0-SNAPSHOT.jar twitter-service.jar` command. If the user wants to customise naming or path, he/she should simply change the paths and the namings as desired in this command, as well. 
