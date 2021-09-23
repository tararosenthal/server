package server;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        Application application = new Application(database);
        Server server = new Server(23456, 50, application);
        server.interact();
    }
}
