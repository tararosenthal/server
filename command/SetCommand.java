package server.command;

import server.Database;

public class SetCommand extends Command {

    public SetCommand(Database database, String key, String value) {
        super(database, key, value);
    }

    @Override
    public boolean execute() {
        return database.set(key, value);
    }
}
