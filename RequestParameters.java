package server;

public class RequestParameters {
    private final String type;
    private String key = "";
    private String value = "";

    public RequestParameters(String type, String key, String value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public RequestParameters(String type, String key) {
        this.type = type;
        this.key = key;
    }

    public RequestParameters(String type) {
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
