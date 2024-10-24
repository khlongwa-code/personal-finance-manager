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
        String clientMessage) {
        JSONObject transactionInfo = new JSONObject(clientMessage).getJSONObject("data");
        String userEmail = transactionInfo.getString("email");

        Integer userId = dai.getUserId(userEmail);

        Float amount = transactionInfo.getFloat("amount");
        String type = transactionInfo.getString("transaction");

        dai.makeTransaction(amount, type, userId);

        return "Done";
    }
}
