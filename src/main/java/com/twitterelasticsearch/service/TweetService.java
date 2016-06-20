package com.twitterelasticsearch.service;

import com.twitterelasticsearch.domain.Tweet;

public interface TweetService {
    Tweet save(Tweet tweet);
}
