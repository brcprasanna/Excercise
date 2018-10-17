package com.prasanna.yelpreviewapp.utils;

public class AppConstants {
    public static final String API_KEY = "_o-e3-x4lZmB7pAzUnNBLmWfOdAse0A8Oyyaqf1yqCNj9rFTMK7AQPMoblqJOpGOnaFk0G_Msii52trP9qM8rRL3q0v7szvIxfe59OAZC8LxiwJ63YtlOiwMGdzEW3Yx";
    public static final String CATEGORY_URL = "https://api.yelp.com/v3/categories";
    public static final String BUSINESS_SEARCH_URL = "https://api.yelp.com/v3/businesses/search?latitude=43.773300&longitude=-79.341890";
    public static final String EMPTY = "";
    public static final String INTENT_DATA_SEARCH_TEXT = "SEARCH_TEXT";
    public static final String INTENT_DATA_PRICE_RANGE_TEXT = "PRICE_RANGE_TEXT";
    public static final String INTENT_DATA_CATEGORY_TEXT = "CATEGORY_TEXT";


    public enum ResponseStatus {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR
    }

}
