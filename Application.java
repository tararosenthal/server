package server;

import server.command.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private final Database database;
    private String commandTypeAsString;
    private int index;
    private String value;

    public Application(Database database) {
        this.database = database;
    }

    public String executeCommandAndReturnOutput(String commandInput) {
        parseCommandComponentsFromInput(commandInput);
        CommandType commandType = getCommandType();
        if (!CommandType.INVALID.equals(commandType)) {
            Command command = getCommandByCommandType(commandType);
            assert command != null;
            if (command.execute()) {
                if (command.getStoredDatabaseValue().isEmpty()) {
                    return "OK";
                } else {
                    return command.getStoredDatabaseValue();
                }
            }
        }
        return "ERROR";
    }

    private void parseCommandComponentsFromInput(String commandInput) {
        String[] array = commandInput.split(" ", 3);
        if (array.length > 0) {
            commandTypeAsString = Objects.requireNonNull(array[0]);
        } else {
            commandTypeAsString = "";
        }
        if (array.length > 1) {
            index = Integer.parseInt(array[1]) - 1;
        } else {
            index = -1;
        }
        if (array.length > 2) {
            value = Objects.requireNonNull(array[2]);
        } else {
            value = "";
        }
    }

    private CommandType getCommandType() {
        HashMap<Pattern, CommandType> map = createCommandTypeMatchingMap();
        Matcher matcher;
        for (Map.Entry<Pattern, CommandType> entry: map.entrySet()) {
            matcher = entry.getKey().matcher(commandTypeAsString);
            if (matcher.matches()) {
                return entry.getValue();
            }
        }
        return CommandType.INVALID;
    }

    private Command getCommandByCommandType(CommandType commandType) {
        switch (commandType) {
            case GET:
                return new GetCommand(database, index);
            case DELETE:
                return new DeleteCommand(database, index);
            case SET:
                return new SetCommand(database, index, value);
        }
        return null;
    }

    private HashMap<Pattern, CommandType> createCommandTypeMatchingMap() {
        Pattern get = Pattern.compile("(get)");
        Pattern delete = Pattern.compile("(delete)");
        Pattern set = Pattern.compile("(set)");

        HashMap<Pattern, CommandType> map = new HashMap<>();
        map.put(get, CommandType.GET);
        map.put(set, CommandType.SET);
        map.put(delete, CommandType.DELETE);

        return map;
    }
}
