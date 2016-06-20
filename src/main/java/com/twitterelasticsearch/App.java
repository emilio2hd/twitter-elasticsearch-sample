package com.twitterelasticsearch;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitterelasticsearch.domain.Tweet;
import com.twitterelasticsearch.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.JSONException;
import twitter4j.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@SpringBootApplication
public class App implements CommandLineRunner {
    private static final Logger  LOGGER = LoggerFactory.getLogger(App.class);
    private static final Integer QUEUE_SIZE = 1000;

    @Autowired
    private TweetService tweetService;
    @Autowired
    private ClientBuilder twitterClientBuilder;

    public static void main( String[] args ) throws IOException, InterruptedException, JSONException {
        SpringApplication app = new SpringApplication(App.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    public void run(String... strings) throws Exception {
        if(strings.length == 0){
            throw new IllegalArgumentException("You must provide at least one word as argument");
        }

        while(true) {
            BlockingQueue<String> queue = new LinkedBlockingQueue<String>(QUEUE_SIZE);

            Client client = getTwitterClient(queue, Lists.newArrayList(strings));
            client.connect();

            for (int msgRead = 0; msgRead < QUEUE_SIZE; msgRead++) {
                this.indexJson(queue.take());
            }

            client.stop();
        }
    }

    private void indexJson(String json) throws JSONException {
        LOGGER.info("Indexing: " + json);

        JSONObject obj = new JSONObject(json);
        Map<String, Object> sourceMap  = new HashMap<String, Object>();

        convertJsonObjectToMap(obj, sourceMap);

        Tweet newTweet = Tweet.createFromMap(sourceMap);
        tweetService.save(newTweet);
    }

    private void convertJsonObjectToMap(JSONObject jsonObject, Map<String, Object> sourceMap) throws JSONException {
        Iterator keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value = jsonObject.get(key);

            if(value instanceof JSONObject) {
                Map<String, Object> subMap = new HashMap<String, Object>();

                convertJsonObjectToMap((JSONObject) jsonObject.get(key), subMap);

                value = subMap;
            }

            sourceMap.put(key, value);
        }
    }

    private Client getTwitterClient(BlockingQueue<String> queue, List<String> terms){
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        endpoint.trackTerms(terms);

        return twitterClientBuilder.endpoint(endpoint).processor(new StringDelimitedProcessor(queue)).build();
    }
}
