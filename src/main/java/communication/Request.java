package communication;

import kong.unirest.json.JSONObject;
import resources.Utils;

public class Request {
    
    public String register(String firstName, String lastName, String email, String password) {
        JSONObject personalDetails = new JSONObject();
        JSONObject registrationData = new JSONObject();

        personalDetails.put("firstname", firstName);
        personalDetails.put("lastname", lastName);
        personalDetails.put("email", email);
        personalDetails.put("password", Utils.hashPassword(password));

        registrationData.put("action", "register");

        registrationData.put("data", personalDetails);

        return registrationData.toString();
    }

    public String login(String email, String password) {
        JSONObject loginDetails = new JSONObject();
        JSONObject loginData = new JSONObject();

        loginDetails.put("email", email);
        loginDetails.put("password", password);


        loginData.put("action", "login");

        loginData.put("data", loginDetails);

        return loginData.toString();
    }

    public String transaction(String transactionType, Float transactionAmount, String email) {
        JSONObject transData = new JSONObject();
        JSONObject transaction = new JSONObject();

        transaction.put("amount", transactionAmount);
        transaction.put("transaction", transactionType);
        transaction.put("email", email);
        transData.put("action", "simulate-transactions");
        transData.put("data", transaction);

        return transData.toString();
    }
}