package org.interview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.interview.api.FilteredStreamAPI;
import org.interview.model.Tweet;
import org.interview.service.TwitterService;
import org.interview.util.TweetUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class TwitterApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApplication.class);
    public static String bearerToken = "AAAAAAAAAAAAAAAAAAAAAEf9PAEAAAAAbV78FsX6ZsP0cAJlsBq6kziLtDY%3DZxkWQgGsQmI6MIs0Lcd1XvPQQos6bX6Ngz236QKGFDx8rkjRe0";
    public static ArrayList<String> streamList = new ArrayList<String>();
    public static ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
    public static ArrayList<Tweet> sortedtweetList = new ArrayList<Tweet>();
    public static TwitterService authenticator = new TwitterService();
    public static Map<String, String> rules = new HashMap<>();

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SpringApplication.run(TwitterApplication.class, args);

        runTweetEngine();

    }

    /**
     * runs the main tweet streaming process
     */
    public static void runTweetEngine() {
        // authenticates the user to Twitter API
        authenticator.authenticate();

        rules.put("bieber", "bieber-messages");
        try {
            // it sets up the rules to filter the tweets
            FilteredStreamAPI.setupRules(bearerToken, rules);

            // it connects to stream and starts filtering tweets according to setup rules
            streamList = FilteredStreamAPI.connectStream(bearerToken);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        for (int i = 0; i < streamList.size(); i++) {
            Tweet tweet = TweetUtil.createTweetObjects(streamList.get(i));
            tweetList.add(tweet);
        }

        try {
            for (int i = 0; i < tweetList.size(); i++) {

                long messageTimeInEpoch = TweetUtil.convertDateToEpoch(tweetList.get(i).getData().getCreated_at());
                long userCreationTimeInEpoch = TweetUtil
                        .convertDateToEpoch(tweetList.get(i).getInclude().getUser().get(0).getCreated_at());

                // set Message time as epoch format
                tweetList.get(i).getData().setCreated_at(Long.toString(messageTimeInEpoch));
                // set User creation date as epoch format
                tweetList.get(i).getInclude().getUser().get(0).setCreated_at(Long.toString(userCreationTimeInEpoch));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        sortedtweetList = TweetUtil.sortTweets(tweetList);
        // Standard output format, option 1
        System.out.println(sortedtweetList);
        // creates the log file, option 2
        try {
            TweetUtil.getLogFile(sortedtweetList);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}