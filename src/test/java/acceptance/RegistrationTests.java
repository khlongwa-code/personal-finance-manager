package acceptance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.fasterxml.jackson.databind.JsonNode;

import database.DataManager;
import resources.SimulationClient;
import resources.Utils;
import resources.JsonClient;

public class RegistrationTests {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final SimulationClient serverClient = new JsonClient();
    private DataManager dm = new DataManager();
    private String testEmail = null;

    @BeforeEach
    public void connectToServer() {
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    public void disconnectFromServer() {
        dm.clearDataByEmail(testEmail);
        serverClient.disconnect();
    }

    @Test
    public void testSuccessfulRegistration() {

        assertTrue(serverClient.isConnected());
         
        String password = Utils.hashPassword("R2g@ff?G8");
        testEmail = "test@gmail.com";

        String requestJson = "{" +
            "\"action\": \"register\"," +
            "\"data\": {" +
                "\"firstname\": \"Kusasalakhe\"," +
                "\"lastname\": \"Hlongwa\"," +
                "\"email\": \"" + testEmail + "\"," +
                "\"password\": \"" + password + "\"" +
            "}" +
        "}";

        JsonNode response = serverClient.sendRequest(requestJson);

        assertEquals("OK", response.get("status").asText());
    }

    @Test
    public void testRegistrationWithEmailAlreadyTaken() {

        assertTrue(serverClient.isConnected());

        String password = Utils.hashPassword("R2g@ff?G8");
        testEmail = "test2@gmail.com";

        String requestJson = "{" +
            "\"action\": \"register\"," +
            "\"data\": {" +
                "\"firstname\": \"suguru\"," +
                "\"lastname\": \"geto\"," +
                "\"email\": \"" + testEmail + "\"," +
                "\"password\": \"" + password + "\"" +
            "}" +
        "}";

        String requestJson2 = "{" +
            "\"action\": \"register\"," +
            "\"data\": {" +
                "\"firstname\": \"suguru\"," +
                "\"lastname\": \"geto\"," +
                "\"email\": \"" + testEmail + "\"," +
                "\"password\": \"" + password + "\"" +
            "}" +
        "}";

        serverClient.sendRequest(requestJson);

        JsonNode response = serverClient.sendRequest(requestJson2);

        assertEquals("ERROR", response.get("status").asText());
    }
}
