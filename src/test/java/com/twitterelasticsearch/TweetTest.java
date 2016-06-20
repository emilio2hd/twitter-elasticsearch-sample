package com.twitterelasticsearch;

import com.twitterelasticsearch.config.Config;
import com.twitterelasticsearch.domain.Tweet;
import com.twitterelasticsearch.service.TweetService;
import junit.framework.TestCase;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Config.class }, loader = AnnotationConfigContextLoader.class)
public class TweetTest extends TestCase {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private Client client;

    @Autowired
    private Config config;

    @Test
    public void prepareIndexTest() {
        elasticsearchTemplate.deleteIndex(Tweet.class);
        elasticsearchTemplate.createIndex(Tweet.class);
        elasticsearchTemplate.putMapping(Tweet.class);
        elasticsearchTemplate.refresh(Tweet.class);
    }

    @Test
    public void removeIndex(){
        client.admin().indices().prepareDelete(config.elastisearchIndexName).get();
    }

    @Test
    public void createIndex(){
        client.admin().indices().prepareCreate(config.elastisearchIndexName);
    }

    @Test
    public void search(){
        SearchResponse response = client.prepareSearch(config.elastisearchIndexName).execute().actionGet();
        System.out.println("Hits: " + response.getHits().totalHits() + "\n");

        SearchHit[] results = response.getHits().getHits();

        for (SearchHit hit : results) {
            System.out.println("Index: " + hit.getIndex());
            System.out.println("Hit ID: " + hit.getId());

            Map<String,Object> result = hit.getSource();
            System.out.println("User: " + result.get("user") + "\n");
        }
    }
}
