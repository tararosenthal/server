package server;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONResponse {
    String output;

    private JSONResponse(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public static class Builder {
        private boolean isRequestExecuted = false;
        private String responseParameter;
        private final Gson gson = new Gson();

        public Builder isRequestExecuted(boolean isRequestExecuted) {
            this.isRequestExecuted = isRequestExecuted;
            return this;
        }

        public Builder setResponseParameter(String responseParameter) {
            this.responseParameter = responseParameter;
            return this;
        }

        public JSONResponse build() {
            Map<String, String> outputMap = new LinkedHashMap<>();
            outputMap.put("response", isRequestExecuted ? "OK" : "ERROR");
            if (responseParameter != null && !isRequestExecuted) {
                outputMap.put("reason", responseParameter);
            } else if (responseParameter != null) {
                outputMap.put("value", responseParameter);
            }
            return new JSONResponse(gson.toJson(outputMap));
        }
    }
}
