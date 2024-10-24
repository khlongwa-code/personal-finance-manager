package actions.client;

import communication.Response;
import database.DataAccessInterface;
import database.DataManager;
import kong.unirest.json.JSONObject;
import resources.Utils;

public class Login extends Actions{

    public Login() {
        super("login");
    }

    @Override
    public String execute(DataManager manager, DataAccessInterface dai, 
        String clientMessage) {

        JSONObject userData = new JSONObject(clientMessage).getJSONObject("data");
        String userEmail = userData.getString("email");
        String userPassword = userData.getString("password");

        Integer emailCount = dai.emailExists(userEmail);
        
        if (emailCount == 0 || emailCount == null) {
            return Response.login("ERROR", "Invalid email!");
        }

        String hashedPassword = dai.getUserPassword(userEmail);

        if (!Utils.passwordMatcher(userPassword, hashedPassword)) {
            return Response.login("ERROR", "Incorrect password!");
        }
        
        return Response.login("OK", "Login in successful!");
    }
}