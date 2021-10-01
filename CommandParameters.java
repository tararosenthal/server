package server;

public class CommandParameters {
    private final String type;
    private String key = "";
    private String value = "";

    public CommandParameters(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public CommandParameters(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public CommandParameters(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
