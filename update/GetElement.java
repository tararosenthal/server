package server.update;

import server.Database;

public class GetElement implements DatabaseUpdater {
    private final String output;

    public GetElement(Database database, String input) {
        String[] temp = input.toLowerCase().split(" ");
        int position = Integer.parseInt(temp[1]) - 1;
        output = database.get(position);
    }

    @Override
    public String toString() {
        return output;
    }
}
