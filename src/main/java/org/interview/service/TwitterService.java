package org.interview.service;

import java.io.PrintStream;
import org.interview.oauth.twitter.TwitterAuthenticator;

public class TwitterService {
    final String consumerKey = "RLSrphihyR4G2UxvA0XBkLAdl";
    final String consumerSecret = "FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4";
    final String authenticatorFile = "authenticator.txt";

    /**
     * Authenticates the user to Twitter API
     */
    public void authenticate() {
        try {
            final PrintStream out = new PrintStream(authenticatorFile);
            TwitterAuthenticator authenticator = new TwitterAuthenticator(out, consumerKey, consumerSecret);
            authenticator.getAuthorizedHttpRequestFactory();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
