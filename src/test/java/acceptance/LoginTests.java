package acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import resources.SimulationClient;
import resources.Utils;
import resources.JsonClient;

public class LoginTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final SimulationClient serverClient = new JsonClient();

    @BeforeEach
    public void connectToServer() {
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
        String password = Utils.hashPassword("R2g@ff?G8");
        String requestRegister = "{" +
             "\"action\": \"register\"," +
             "\"data\": {" +
                 "\"firstname\": \"john\"," +
                 "\"lastname\": \"cena\"," +
                 "\"email\": \"jcena@gmail.com\"," +
                 "\"password\": \"" + password + "\"" +
             "}" +
         "}";

        serverClient.sendRequest(requestRegister);
    }

    @AfterEach
    public void disconnectFromServer() {
        serverClient.disconnect();
    }

    @Test
    public void testSuccessfulLogin() {

        assertTrue(serverClient.isConnected());

        String requestLogin = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"jcena@gmail.com\"," +
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
                 "\"email\": \"jcena@gmail.com\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";

        JsonNode response = serverClient.sendRequest(requestLogin);
        assertEquals("OK", response.get("status").asText());

        String requestLogin2 = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"jcena@gmail.com\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";
        
        JsonNode response2 = serverClient.sendRequest(requestLogin2);
        assertEquals("ERROR", response2.get("status").asText());
    }

    @Test
    public void testLoginInvalidEmail() {
        assertTrue(serverClient.isConnected());

        String requestLogin = "{" +
             "\"action\": \"login\"," +
             "\"data\": {" +
                 "\"email\": \"jolenmoore@gmail.com\"," +
                 "\"password\": \"R2g@ff?G8\"" +
             "}" +
         "}";

        JsonNode response = serverClient.sendRequest(requestLogin);
        assertEquals("ERROR", response.get("status").asText());
    }
}
