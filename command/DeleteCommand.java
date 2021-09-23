package server.command;

import server.Database;

public class DeleteCommand extends Command {
    public DeleteCommand(Database database, int index) {
        super(database, index);
    }

    @Override
    public boolean execute() {
        return database.delete(index);
    }
}
