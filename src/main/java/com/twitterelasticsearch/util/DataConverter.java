package com.twitterelasticsearch.util;

import org.apache.commons.lang.StringUtils;
import twitter4j.JSONObject;

public class DataConverter {

    public static Long toLong(Object object) {
        Long longConverted = 0L;

        try{
            longConverted = Long.valueOf(toString(object));
        } catch (Exception ignored){
        }

        return longConverted;
    }

    public static Boolean toBoolean(Object object) {
        return Boolean.valueOf(String.valueOf(object));
    }

    public static String toString(Object object) {
        return JSONObject.NULL.equals(object) ? StringUtils.EMPTY : String.valueOf(object);
    }
}
