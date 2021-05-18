package org.interview.util;

import org.interview.model.Tweet;
import org.json.JSONObject;
import java.util.ArrayList;
import java.text.ParseException;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TweetUtil {

    /**
     * Converts the json string into a pretty format. Not used currently, optional
     * feature
     * 
     * @param jsonString the json string to be converted to a pretty format
     * @return String
     */
    public static String toPrettyFormat(String jsonString) {
        JSONObject json = new JSONObject(jsonString); // Convert text to object
        return json.toString(4);
    }

    /**
     * Sorts the arraylist of tweet objects based on two criteria. The users sorted
     * chronologically in ascending order The messages per user is also sorted
     * chronologically in ascending order
     * 
     * @param tweetList arraylist of tweet objects
     * @return ArrayList<Tweet>
     */
    public static ArrayList<Tweet> sortTweets(ArrayList<Tweet> tweetList) {
        tweetList.sort((o1, o2) -> {
            // we sort the users by their creation date in ascending order
            int cmp = o1.getInclude().getUser().get(0).getCreated_at()
                    .compareTo(o2.getInclude().getUser().get(0).getCreated_at());
            // if user creation date is same, we check the message time
            if (cmp == 0) {
                // we sort the messages by their time in ascending order
                boolean isMessageEqual = (o1.getData().getCreated_at()).equals(o2.getData().getCreated_at());
                if (isMessageEqual) {
                    cmp = 0;
                } else {
                    cmp = -1;
                }
            }

            return cmp;
        });

        return tweetList;
    }

    /**
     * Creates a log file containing all the tweets fetched
     * 
     * @param sortedtweetList
     * @throws IOException
     */
    public static void getLogFile(ArrayList<Tweet> sortedtweetList) throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for (Tweet tweet : sortedtweetList) {
            writer.write(tweet + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Maps the tweet in json format received to 'tweet' model
     * 
     * @param jsonString tweet in json format
     * @return Tweet
     */
    public static Tweet createTweetObjects(String jsonString) {
        Gson gson = new Gson();
        Tweet tweet = gson.fromJson(jsonString, Tweet.class);
        return tweet;
    }

    /**
     * It converts the yyyy-MM-dd'T'HH:mm:ss.SSSXXX format date into epoch
     * 
     * @param dateToBeConverted
     * @return long
     * @throws ParseException
     */
    public static long convertDateToEpoch(String dateToBeConverted) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = dateFormat.parse(dateToBeConverted);
        long epoch = date.getTime();
        return epoch;
    }

      /**
     * Keeps track of messages per second statistics across multiple runs of the application
     * @param numberOfMessages
     * @param elapsedTime
     * @throws IOException
     */
    public static void generateTweetStatistics(int numberOfMessages, double elapsedTime)  throws IOException {

        double tweetsPerSecond = 0;
        FileWriter file = new FileWriter("TweetStatistics.txt", true);

        if (numberOfMessages < 1) {
            tweetsPerSecond = 0;
          } else {
            tweetsPerSecond = numberOfMessages / (((double) elapsedTime) / 1000);
          }
          file.write("Number of Tweets per second: ");
          file.append(String.valueOf(tweetsPerSecond));
          file.append("\n");
          file.close();
    }

}
