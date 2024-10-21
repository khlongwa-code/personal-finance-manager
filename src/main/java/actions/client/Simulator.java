package actions.client;

import database.DataAccessInterface;
import database.DataManager;
import kong.unirest.json.JSONObject;

public class Simulator extends Actions {

    public Simulator(String argument) {
        super("simulate", argument);
    }

    @Override
    public String execute(DataManager manager, DataAccessInterface dai, 
        String clientMessage, Integer userId) {
        JSONObject transactionInfo = new JSONObject(clientMessage).getJSONObject("data");

        Float amount = transactionInfo.getFloat("amount");
        String type = transactionInfo.getString("transaction");

        dai.makeTransaction(amount, type, userId);

        return "Done";
    }
}
