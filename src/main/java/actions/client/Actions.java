package actions.client;

import database.DataAccessInterface;
import database.DataManager;
import kong.unirest.json.JSONObject;

public abstract class Actions {
    private final String action;
    private String argument;

    public Actions(String action) {
        this.action = action.trim().toLowerCase();
        this.argument = "";
    }

    public Actions(String action, String argument) {
        this(action);
        this.argument = argument.trim().toLowerCase();
    }

    public static Actions create(String clientMessage) {
        JSONObject msgObject = new JSONObject(clientMessage);
        String action = msgObject.getString("action");
        String[] args = action.toLowerCase().trim().split("-");

        if (args.length == 1) {
            args = new String[] {args[0], ""};
        }

        switch (args[0]) {

            case "register":
                return new Register();
            case "login":
                return new Login();
            case "simulate":
                return new Simulator(args[1]);
            default:
                throw new IllegalArgumentException("Unsupported command: " + action);
        }
    }

    public abstract String execute(DataManager manager, DataAccessInterface dai, String clientMessage);
}
