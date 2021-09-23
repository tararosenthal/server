package server.command;

import server.Database;

public abstract class Command {
    protected final Database database;
    protected String storedDatabaseValue = "";
    protected String value;
    protected int index;

    public Command(Database database, int index) {
        this.database = database;
        this.index = index;
    }

    public Command(Database database, int index, String value) {
        this.database = database;
        this.index = index;
        this.value = value;
    }

    public String getStoredDatabaseValue() {
        return storedDatabaseValue;
    }

    public abstract boolean execute();
}
