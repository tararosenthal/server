package server;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class JSONOutput {
    String output;

    private JSONOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public static class Builder {
        private boolean response = false;
        private String value;
        private String reason;
        private final Gson gson = new Gson();

        public Builder setResponse(boolean response) {
            this.response = response;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return  this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public JSONOutput build() {
            Map<String, String> outputMap = new LinkedHashMap<>();
            outputMap.put("response", response ? "OK" : "ERROR");
            if (reason != null) {
                outputMap.put("reason", reason);
            }
            if (value != null) {
                outputMap.put("value", value);
            }
            return new JSONOutput(gson.toJson(outputMap));
        }
    }
}
