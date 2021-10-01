package server;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private final Map<String, String> JSON_PAIRS = new HashMap<>();
    private String storedDatabaseValue = "";

    public boolean set(String key, String value) {
        JSON_PAIRS.put(key, value);
        return true;
    }

    public boolean get(String key) {
        if (JSON_PAIRS.containsKey(key)) {
            storedDatabaseValue = JSON_PAIRS.get(key);
            return true;
        }
        return false;
    }

    public boolean delete(String key) {
        if (JSON_PAIRS.containsKey(key)) {
            JSON_PAIRS.remove(key);
            return true;
        }
        return false;
    }

    public String getStoredDatabaseValue() {
        return storedDatabaseValue;
    }
}
