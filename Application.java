package server;

import com.google.gson.Gson;
import server.command.*;

import java.util.Objects;

public class Application {
    private final Database database;
    Gson gson = new Gson();

    public Application(Database database) {
        this.database = database;
    }

    public String executeCommandAndReturnJSONOutput(String jsonCommand) {
        CommandParameters commandParameters = gson.fromJson(jsonCommand, CommandParameters.class);

        if ("exit".equals(commandParameters.getType())) {
            return buildJSONOutput(true, "");
        } else {
            Command command = getCommandFromCommandParameters(commandParameters);
            if (command != null) {
                if (command.execute()) {
                    return buildJSONOutput(true, command.getStoredDatabaseValue());
                }
                return buildJSONOutput(false, "No such key");
            }
            return buildJSONOutput(false, "Invalid command type");
        }
    }

    private Command getCommandFromCommandParameters(CommandParameters commandParameters) {
       switch (Objects.requireNonNullElse(
               commandParameters.getType(), "")) {
           case "get":
               return new GetCommand(database, Objects.requireNonNullElse(
                       commandParameters.getKey(), ""));
           case "set":
               return new SetCommand(database, Objects.requireNonNullElse(
                       commandParameters.getKey(), ""), Objects.requireNonNullElse(
                               commandParameters.getValue(), ""));
           case "delete":
               return new DeleteCommand(database, Objects.requireNonNullElse(
                       commandParameters.getKey(), ""));
           default:
               return null;
       }
    }

    private String buildJSONOutput(boolean isExecuted, String parameter) {
        JSONOutput jsonOutput;
        JSONOutput.Builder builder = new JSONOutput.Builder();

        if (isExecuted && !parameter.isEmpty()) {
            jsonOutput = builder
                    .setResponse(true)
                    .setValue(parameter)
                    .build();
        } else if (isExecuted) {
            jsonOutput = builder
                    .setResponse(true)
                    .build();
        } else {
            jsonOutput = builder
                    .setResponse(false)
                    .setReason(parameter)
                    .build();
        }

        return jsonOutput.getOutput();
    }
}
