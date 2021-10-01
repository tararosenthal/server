package server.command;

import server.Database;

public class DeleteCommand extends Command {
    public DeleteCommand(Database database, String key) {
        super(database, key);
    }

    @Override
    public boolean execute() {
        return database.delete(key);
    }
}
