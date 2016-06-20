package com.twitterelasticsearch.repository;

import com.twitterelasticsearch.domain.Tweet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends ElasticsearchRepository<Tweet, String> {

}
