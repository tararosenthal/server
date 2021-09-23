package server.command;

import server.Database;

public class SetCommand extends Command {

    public SetCommand(Database database, int index, String value) {
        super(database, index, value);
    }

    @Override
    public boolean execute() {
        return database.set(index, value);
    }
}
