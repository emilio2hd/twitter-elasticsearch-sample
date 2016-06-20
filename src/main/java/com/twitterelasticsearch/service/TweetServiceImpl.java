package com.twitterelasticsearch.service;

import com.twitterelasticsearch.domain.Tweet;
import com.twitterelasticsearch.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }
}
