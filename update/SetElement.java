package server.update;

import server.Database;

public class SetElement implements DatabaseUpdater {
    private final String output;

    public SetElement(Database database, String input) {
        String[] temp = input.split(" ", 3);
        int position = Integer.parseInt(temp[1]) - 1;
        String value = temp[2];
        output = database.set(position, value);
    }

    @Override
    public String toString() {
        return output;
    }
}
