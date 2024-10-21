package acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import database.DataManager;
import resources.SimulationClient;
import resources.Utils;
import resources.JsonClient;

public class LoginTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final SimulationClient serverClient = new JsonClient();
    private DataManager dm = new DataManager();
    private String testEmail = null;

    @BeforeEach
    public void connectToServer() {
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
        String password = Utils.hashPassword("R2g@ff?G8");
        testEmail = "testlogin@gmail.com";

        String requestRegister = "{" +
             "\"action\": \"register\"," +
             "\"data\": {" +
                 "\"firstname\": \"john\"," +
                 "\"lastname\": \"cena\"," +
                 "\"email\": \"" + testEmail + "\"," +
                 "\"password\": \"" + password + "\"" +
             "}" +
         "}";

        serverClient.sendRequest(requestRegister);
    }

    @AfterEach
    public void disconnectFromServer() {
        dm.clearDataByEmail(testEmail);
        serverClient.disconnect();
    }

    @Test
    public void testSuccessfulLogin() {

        assertTrue(serverClient.isConnected());

        String requestLogin = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"" + testEmail + "\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";

        JsonNode response = serverClient.sendRequest(requestLogin);
        assertEquals("OK", response.get("status").asText());
    }

    @Test
    public void testLoginWhenAlreadyLoggedIn() {

        assertTrue(serverClient.isConnected());

        String requestLogin = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"" + testEmail + "\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";

        JsonNode response = serverClient.sendRequest(requestLogin);
        assertEquals("OK", response.get("status").asText());

        String requestLogin2 = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"" + testEmail + "\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";
        
        JsonNode response2 = serverClient.sendRequest(requestLogin2);
        assertEquals("ERROR", response2.get("status").asText());
    }

    @Test
    public void testLoginInvalidEmail() {
        assertTrue(serverClient.isConnected());
        testEmail = "invalid@gmail.com";

        String requestLogin = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"" + testEmail + "\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";

        JsonNode response = serverClient.sendRequest(requestLogin);
        assertEquals("ERROR", response.get("status").asText());
    }
}