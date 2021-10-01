package server.command;

import server.Database;

public abstract class Command {
    protected final Database database;
    protected String storedDatabaseValue = "";
    protected String key;
    protected String value;

    public Command(Database database, String key) {
        this.database = database;
        this.key = key;
    }

    public Command(Database database, String key, String value) {
        this.database = database;
        this.key = key;
        this.value = value;
    }

    public String getStoredDatabaseValue() {
        return storedDatabaseValue;
    }

    public abstract boolean execute();
}
