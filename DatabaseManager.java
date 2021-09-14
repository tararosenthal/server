package server;

import server.update.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseManager {
    private final Database database;

    public DatabaseManager(Database database) {
        this.database = database;
    }

    public void getAction() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().strip();
        Action action = checkInput(input);
        DatabaseUpdater databaseUpdater = getUpdater(action, input);
        System.out.println(Objects.toString(databaseUpdater, "ERROR"));
    }

    private Action checkInput(String input) {
        HashMap<Pattern, Action> map = createMap();
        Matcher matcher;
        for (Map.Entry<Pattern, Action> entry: map.entrySet()) {
            matcher = entry.getKey().matcher(input);
            if (matcher.matches()) {
                return entry.getValue();
            }
        }
        return Action.INVALID;
    }

    private DatabaseUpdater getUpdater(Action action, String input) {
        switch (action) {
            case EXIT:
                System.exit(0);
            case GET:
                return new GetElement(database, input);
            case DELETE:
                return new DeleteElement(database, input);
            case SET:
                return new SetElement(database, input);
        }
        return null;
    }
    
    private HashMap<Pattern, Action> createMap() {
        Pattern exit = Pattern.compile("(?i)exit");
        Pattern get = Pattern.compile("(?i)(get)\\s\\d{1,3}");
        Pattern delete = Pattern.compile("(?i)(delete)\\s\\d{1,3}");
        Pattern set = Pattern.compile("(?i)(set)\\s\\d{1,3}\\s.+");
        
        HashMap<Pattern, Action> map = new HashMap<>();
        map.put(get, Action.GET);
        map.put(set, Action.SET);
        map.put(delete, Action.DELETE);
        map.put(exit, Action.EXIT);
        
        return map;
    }
}
