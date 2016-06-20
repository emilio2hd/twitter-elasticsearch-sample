package com.twitterelasticsearch.domain;

import com.twitterelasticsearch.util.DataConverter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Document(indexName = "#{@elastisearchIndexName}", type = "user")
public class User {
    @Id
    private String    id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String  utcOffset;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long  friendsCount;

    @Field(type = FieldType.String, index = FieldIndex.no, store = true)
    private String  profileImageUrlHttps;

    @Field(type = FieldType.String, index = FieldIndex.no, store = true)
    private String  profileImageUrl;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long  listedCount;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long  favouritesCount;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  description;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String    createdAt;

    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean isProtected;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  name;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  screenName;

    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean geoEnabled;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String  timeZone;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long    statusesCount;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  lang;

    @Field(type = FieldType.Long, index = FieldIndex.not_analyzed, store = true)
    private Long    followersCount;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    private String  location;

    @Field(type = FieldType.Boolean, index = FieldIndex.not_analyzed, store = true)
    private Boolean verified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getProfileImageUrlHttps() {
        return profileImageUrlHttps;
    }

    public void setProfileImageUrlHttps(String profileImageUrlHttps) {
        this.profileImageUrlHttps = profileImageUrlHttps;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Long getListedCount() {
        return listedCount;
    }

    public void setListedCount(Long listedCount) {
        this.listedCount = listedCount;
    }

    public Long getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(Long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getProtected() {
        return isProtected;
    }

    public void setProtected(Boolean aProtected) {
        isProtected = aProtected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Boolean getGeoEnabled() {
        return geoEnabled;
    }

    public void setGeoEnabled(Boolean geo_enabled) {
        this.geoEnabled = geo_enabled;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(Long statusesCount) {
        this.statusesCount = statusesCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public static User createFromMap(Map<String, Object> sourceMap) {
        User newUser = new User();

        newUser.setId(DataConverter.toString(sourceMap.get("id_str")));
        newUser.setUtcOffset(DataConverter.toString(sourceMap.get("utc_offset")));
        newUser.setFriendsCount(DataConverter.toLong(sourceMap.get("friends_count")));
        newUser.setProfileImageUrlHttps(DataConverter.toString(sourceMap.get("profile_image_url_https")));
        newUser.setProfileImageUrl(DataConverter.toString(sourceMap.get("profile_image_url")));
        newUser.setListedCount(DataConverter.toLong(sourceMap.get("listed_count")));
        newUser.setFavouritesCount(DataConverter.toLong(sourceMap.get("favourites_count")));
        newUser.setDescription(DataConverter.toString(sourceMap.get("description")));
        newUser.setCreatedAt(DataConverter.toString(sourceMap.get("created_at")));
        newUser.setProtected(DataConverter.toBoolean(sourceMap.get("protected")));
        newUser.setName(DataConverter.toString(sourceMap.get("name")));
        newUser.setScreenName(DataConverter.toString(sourceMap.get("screen_name")));
        newUser.setGeoEnabled(DataConverter.toBoolean(sourceMap.get("geo_enabled")));
        newUser.setTimeZone(DataConverter.toString(sourceMap.get("time_zone")));
        newUser.setStatusesCount(DataConverter.toLong(sourceMap.get("statuses_count")));
        newUser.setLang(DataConverter.toString(sourceMap.get("lang")));
        newUser.setFollowersCount(DataConverter.toLong(sourceMap.get("followers_count")));
        newUser.setLocation(DataConverter.toString(sourceMap.get("location")));
        newUser.setVerified(DataConverter.toBoolean(sourceMap.get("verified")));

        return newUser;
    }
}
