package server;

import com.google.gson.Gson;
import server.command.*;

import java.util.Objects;

public class Application {
    private final Database database;

    public Application(Database database) {
        this.database = database;
    }

    public String executeJSONRequestAndReturnJSONResponse(String jsonRequest) {
        Gson gson = new Gson();
        RequestParameters requestParameters = gson.fromJson(jsonRequest, RequestParameters.class);

        if ("exit".equals(requestParameters.getType())) {
            return buildJSONResponse(true, "");
        } else {
            Command command = getCommandFromRequestParameters(requestParameters);
            if (command != null) {
                if (command.execute()) {
                    return buildJSONResponse(true, command.getStoredDatabaseValue());
                }
                return buildJSONResponse(false, "No such key");
            }
            return buildJSONResponse(false, "Invalid command type");
        }
    }

    private Command getCommandFromRequestParameters(RequestParameters requestParameters) {
       switch (Objects.requireNonNullElse(
               requestParameters.getType(), "")) {
           case "get":
               return new GetCommand(database, Objects.requireNonNullElse(
                       requestParameters.getKey(), ""));
           case "set":
               return new SetCommand(database, Objects.requireNonNullElse(
                       requestParameters.getKey(), ""), Objects.requireNonNullElse(
                               requestParameters.getValue(), ""));
           case "delete":
               return new DeleteCommand(database, Objects.requireNonNullElse(
                       requestParameters.getKey(), ""));
           default:
               return null;
       }
    }

    private String buildJSONResponse(boolean isRequestExecuted, String responseParameter) {
        JSONResponse.Builder builder = new JSONResponse.Builder()
                .isRequestExecuted(isRequestExecuted);

        if (!responseParameter.isEmpty()) {
            builder = builder.setResponseParameter(responseParameter);
        }

        JSONResponse jsonResponse = builder.build();
        return jsonResponse.getOutput();
    }
}
