package server;

import java.util.Arrays;

public class Database {
    private final String[] database = new String[1000];
    private String storedDatabaseValue = "";

    public Database() {
        Arrays.fill(database, "");
    }

    public boolean set(int index, String value) {
        if (isIndexLocatedInDatabase(index)) {
            database[index] = value;
            return true;
        }
        return false;
    }

    public boolean get(int index) {
        if (isIndexLocatedInDatabase(index) && !isIndexValueEmpty(index)) {
            storedDatabaseValue = database[index];
            return true;
        }
        return false;
    }

    public boolean delete(int index) {
        if (isIndexLocatedInDatabase(index)) {
            database[index] = "";
            return true;
        }
        return false;
    }

    public String getStoredDatabaseValue() {
        return storedDatabaseValue;
    }

    private boolean isIndexLocatedInDatabase(int index) {
        return index >= 0 && index < database.length;
    }

    private boolean isIndexValueEmpty(int index) {
        return database[index].isEmpty();
    }
}
