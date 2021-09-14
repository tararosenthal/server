package server.update;

import server.Database;

public class DeleteElement implements DatabaseUpdater {
    private final String output;

    public DeleteElement(Database database, String input) {
        String[] temp = input.toLowerCase().split(" ");
        int position = Integer.parseInt(temp[1]) - 1;
        output = database.delete(position);
    }

    @Override
    public String toString() {
        return output;
    }
}
