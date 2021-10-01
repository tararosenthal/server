package server.command;

import server.Database;

public class GetCommand extends Command {

    public GetCommand(Database database, String key) {
        super(database, key);
    }

    @Override
    public boolean execute() {
        if (database.get(key)) {
            storedDatabaseValue = database.getStoredDatabaseValue();
            return true;
        }
        return false;
    }
}
