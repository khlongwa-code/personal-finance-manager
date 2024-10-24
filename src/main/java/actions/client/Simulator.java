package actions.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import communication.Response;
import database.DataAccessInterface;
import database.DataManager;
import kong.unirest.json.JSONObject;

public class Simulator extends Actions {
    private Float amount;
    private String type;
    private Integer userId;


    public Simulator(String argument) {
        super("simulate", argument);
    }

    @Override
    public String execute(DataManager manager, DataAccessInterface dai, 
        String clientMessage) {
        JSONObject transactionInfo = new JSONObject(clientMessage).getJSONObject("data");
        String userEmail = transactionInfo.getString("email");

        userId = dai.getUserId(userEmail);
        amount = transactionInfo.getFloat("amount");
        type = transactionInfo.getString("transaction");

        String message = handleSimulations(dai);

        return Response.success(message);
    }

    private String handleSimulations(DataAccessInterface dai) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String today = now.format(formatter);
        String message = null;

        if (type.equalsIgnoreCase("transfer")) {
            dai.makeTransaction(amount, type, today, userId);
            return "A transfer of R" + amount + " successfully made!";
        } else if (type.contains("income")) {
            String[] parts = type.split(" ", 2);
            dai.makeTransaction(amount, parts[0], today, userId);
            dai.makeIncome(amount, parts[1], today, userId);
            return "You received R" + amount + " from " + parts[1];
        } else {
            String[] parts = type.split(" ");
            dai.makeExpense(amount, type, today, userId);
            return "You bought " + parts[1] + " for R" + amount;
        }
    }
}
