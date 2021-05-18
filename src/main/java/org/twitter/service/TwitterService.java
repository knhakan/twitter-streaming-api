package org.twitter.service;

import java.io.PrintStream;

import org.twitter.oauth.twitter.TwitterAuthenticator;

public class TwitterService {
    final String consumerKey = "consumerKey" //insert the consumer key;
    final String consumerSecret = "consumerSecret" //insert the consumer secret;
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
