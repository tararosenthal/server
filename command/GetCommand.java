package server.command;

import server.Database;

public class GetCommand extends Command {

    public GetCommand(Database database, int index) {
        super(database, index);
    }

    @Override
    public boolean execute() {
        boolean temp = database.get(index);
        if (temp) {
            storedDatabaseValue = database.getStoredDatabaseValue();
        }
        return temp;
    }
}
