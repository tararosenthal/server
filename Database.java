package server;

import java.util.Arrays;

public class Database {
    String[] array = new String[100];
    String updated = "OK";
    String error = "ERROR";

    public Database() {
        Arrays.fill(array, "");
    }

    public String set(int position, String value) {
        if (checkPosition(position)) {
            array[position] = value;
            return updated;
        }
        return error;
    }

    public String get(int position) {
        if (checkPosition(position) && !array[position].isEmpty()) {
            return array[position];
        }
        return error;
    }

    public String delete(int position) {
        if (checkPosition(position)) {
            array[position] = "";
            return updated;
        }
        return error;
    }

    private boolean checkPosition(int position) {
        return position >= 0 && position < array.length;
    }
}
