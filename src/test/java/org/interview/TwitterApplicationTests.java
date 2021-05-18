package org.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import org.junit.jupiter.api.extension.ExtendWith;
import org.interview.api.FilteredStreamAPI;
import org.interview.model.Tweet;
import org.interview.service.TwitterService;
import org.interview.util.TweetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TwitterApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterApplicationTests.class);
    public String bearerToken = "AAAAAAAAAAAAAAAAAAAAAEf9PAEAAAAAbV78FsX6ZsP0cAJlsBq6kziLtDY%3DZxkWQgGsQmI6MIs0Lcd1XvPQQos6bX6Ngz236QKGFDx8rkjRe0";
    public ArrayList<String> TweetTestList = new ArrayList<String>();
    public ArrayList<Tweet> TweetObjectTestList = new ArrayList<Tweet>();


    public String tweet1= "{\"data\":{\"created_at\":\"1817586118\",\"id\":\"1390951258085855232\",\"text\":\"RT @thentrepredoer: 21 dressed like he was finna meet Bieber n Bieber dressed like he was finna meet 21 lol\",\"author_id\":\"959331988581896192\"},\"includes\":{\"users\":[{\"created_at\":\"1517586118\",\"name\":\"Michael\",\"username\":\"snanaekfgjua1\",\"id\":\"95933198858189876192\"}]},\"matching_rules\":[{\"id\":1390951293410234369,\"tag\":\"bieber-messages\"}]}\r\n";
    public String tweet2= "{\"data\":{\"created_at\":\"1617586118\",\"id\":\"1490951258085855232\",\"text\":\"RTukyu @thentrepredoer: 21 dressed like he was finna meet Bieber n Bieber dressed like he was finna meet 21 lol\",\"author_id\":\"959331988581896192\"},\"includes\":{\"users\":[{\"created_at\":\"1617586118\",\"name\":\"Dwight\",\"username\":\"snanaefgjkua1\",\"id\":\"95933198858172896192\"}]},\"matching_rules\":[{\"id\":1390951293410234369,\"tag\":\"bieber-messages\"}]}\r\n";
    public String tweet3= "{\"data\":{\"created_at\":\"1517586118\",\"id\":\"1590951258085855232\",\"text\":\"RTtuktuk @thentrepredoer: 21 dressed like he was finna meet Bieber n Bieber dressed like he was finna meet 21 lol\",\"author_id\":\"959331988581896192\"},\"includes\":{\"users\":[{\"created_at\":\"1417586118\",\"name\":\"Jim\",\"username\":\"snafgjnaekua1\",\"id\":\"95933198858781896192\"}]},\"matching_rules\":[{\"id\":1390951293410234369,\"tag\":\"bieber-messages\"}]}\r\n";
    public String tweet4= "{\"data\":{\"created_at\":\"1417586118\",\"id\":\"1590951258085855232\",\"text\":\"RTtuktuk @thentrepredoer: asfasf21 dressed like he was finna meet Bieber n Bieber dressed like he was finna meet 21 lol\",\"author_id\":\"959331988581896192\"},\"includes\":{\"users\":[{\"created_at\":\"1417586118\",\"name\":\"Pam\",\"username\":\"snafgjnaekua1\",\"id\":\"95933198858781896192\"}]},\"matching_rules\":[{\"id\":1390951293410234369,\"tag\":\"bieber-messages\"}]}\r\n";

    public Map<String, String> rules = new HashMap<>();

    public String dateToBeConverted = "2021-05-08T21:01:20.000Z";
    public int numberOfMessages=4;
    public double elapsedTime=20000;

    @Test
    public void setupRulesTest() throws IOException {
      
        rules.put("bieber", "bieber-messages");
        try {
            FilteredStreamAPI.setupRules(bearerToken, rules);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    
    @Test
	public void createTweetObjectsTest() {

        TweetObjectTestList = createTweetObjects();

        String nameTweet1=TweetObjectTestList.get(0).getInclude().getUser().get(0).getName();
        String nameTweet2=TweetObjectTestList.get(1).getInclude().getUser().get(0).getName();
        String nameTweet3=TweetObjectTestList.get(2).getInclude().getUser().get(0).getName();
        String nameTweet4=TweetObjectTestList.get(3).getInclude().getUser().get(0).getName();

        assertEquals(nameTweet1, "Michael");
        assertEquals(nameTweet2, "Dwight");
        assertEquals(nameTweet3, "Jim");
        assertEquals(nameTweet4, "Pam");

	}
    
    @Test
	public void connectStreamTest() throws IOException, URISyntaxException {
        FilteredStreamAPI.connectStream(bearerToken);
    }


    @Test
    public void toPrettyFormatTest() {
        String jsonStringTest = tweet1+tweet2+tweet3+tweet4;
        TweetUtil.toPrettyFormat(jsonStringTest);
    }

    @Test
    public void sortTweetsTest() {
        TweetObjectTestList = createTweetObjects();

        TweetUtil.sortTweets(TweetObjectTestList);

    }

    @Test
    public void getLogFileTest() throws IOException{
        TweetObjectTestList = createTweetObjects();

        TweetUtil.getLogFile(TweetObjectTestList);
    }

    @Test
    public void convertDateToEpochTest() throws ParseException {
        TweetUtil.convertDateToEpoch(dateToBeConverted);
    }

    @Test
    public void generateTweetStatisticsTest() throws IOException{
        TweetUtil.generateTweetStatistics(numberOfMessages,elapsedTime);
        File tmpDir = new File("./TweetStatistics.txt");
        boolean exists = tmpDir.exists();
        assertEquals(exists, true);
    }

    public static Tweet createTweetObjects(String jsonString) {
        return TweetUtil.createTweetObjects(jsonString);
    }

    public ArrayList<Tweet> createTweetObjects() {
        TweetTestList.add(tweet1);
        TweetTestList.add(tweet2);
        TweetTestList.add(tweet3);
        TweetTestList.add(tweet4);
        for (int i = 0; i < TweetTestList.size(); i++) {
            Tweet tweet = createTweetObjects(TweetTestList.get(i));
            TweetObjectTestList.add(tweet);
        }
        return TweetObjectTestList;
    }

}