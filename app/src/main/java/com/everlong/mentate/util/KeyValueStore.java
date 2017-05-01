package com.everlong.mentate.util;

/**
 * Created by akshit on 3/16/17.
 */

public interface KeyValueStore {
    void put(String key, String value);

    void put(String key, boolean value);

    void put(String key, Long value);

    void put(String key, int value);

    String get(String key, String value);

    int getInt(String key, int defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    long getLong(String key, long defaultValue);

    void remove(String key);

    void clear();
}
