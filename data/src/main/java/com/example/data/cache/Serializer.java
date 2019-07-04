package com.example.data.cache;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class Serializer {
    private final Gson gson = new Gson();

    @Inject
    public Serializer() {
    }

    /**
     * Serialize an Object to Json.
     */
    public String serialize(Object object, Class tClass) {
        return gson.toJson(object, tClass);
    }

    /**
     * Deserialize a Json representation of an object
     */
    public <T> T deserialize(String string, Type tClass) {
        return gson.fromJson(string, tClass);
    }
}
