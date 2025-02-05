package com.kombat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class GameConfig {
    private static Map<String, Object> config;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            config = objectMapper.readValue(new File("config.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getInt(String key) {
        return (int) config.getOrDefault(key, 0);
    }

    public static String getString(String key) {
        return (String) config.getOrDefault(key, "");
    }
}
