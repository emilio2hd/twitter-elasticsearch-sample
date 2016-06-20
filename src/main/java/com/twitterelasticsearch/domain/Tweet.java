package com.twitterelasticsearch.domain;

import com.twitterelasticsearch.util.DataConverter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Document(indexName = "#{@elastisearchIndexName}", type = "tweet")
public class Tweet {
    @Id
    private String id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String    createdAt;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  source;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long    retweetCount;

    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean retweeted;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  text;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  place;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  lang;

    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean favorited;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long    favoriteCount;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long    timestampMs;

    @Field(type = FieldType.Nested)
    private User users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Long retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Boolean getRetweeted() {
        return retweeted;
    }

    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Long getTimestampMs() {
        return timestampMs;
    }

    public void setTimestampMs(Long timestampMs) {
        this.timestampMs = timestampMs;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public static Tweet createFromMap(Map<String, Object> sourceMap) {
        Tweet newTweet = new Tweet();

        newTweet.setId(DataConverter.toString(sourceMap.get("id_str")));
        newTweet.setCreatedAt(DataConverter.toString(sourceMap.get("created_at")));
        newTweet.setSource(DataConverter.toString(sourceMap.get("source")));
        newTweet.setRetweetCount(DataConverter.toLong(sourceMap.get("retweet_count")));
        newTweet.setRetweeted(DataConverter.toBoolean(sourceMap.get("retweeted")));
        newTweet.setText(DataConverter.toString(sourceMap.get("text")));
        newTweet.setPlace(DataConverter.toString(sourceMap.get("place")));
        newTweet.setLang(DataConverter.toString(sourceMap.get("lang")));
        newTweet.setFavorited(DataConverter.toBoolean(sourceMap.get("favorited")));
        newTweet.setFavoriteCount(DataConverter.toLong(sourceMap.get("favorite_count")));
        newTweet.setTimestampMs(DataConverter.toLong(sourceMap.get("timestamp_ms")));
        newTweet.setUsers(User.createFromMap((Map<String, Object>)sourceMap.get("user")));

        return newTweet;
    }
}
