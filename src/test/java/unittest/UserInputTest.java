package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import client.UserInputs;

public class UserInputTest {
    
    @Test
    public void testEnteringIncorrectNamesThenCorrectName() {
        String simulatedInput = "J@ne\nJ4ne\n123\nJane";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UserInputs userInput = new UserInputs();

        assertEquals("Jane", userInput.getUserFirstName());
    }

    @Test
    public void testEnteringIncorrectLastNameThenCorrectLastName() {
        String simulatedInput = "C0le\nCole";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UserInputs userInput = new UserInputs();

        assertEquals("Cole", userInput.getUserLastName());
    }

    @Test
    public void testEnteringIncorrectEmailThenCorrectEmail() {
        String simulatedInput = "jcolegmail.com\njcole@gmail.com";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UserInputs userInput = new UserInputs();

        assertEquals("jcole@gmail.com", userInput.getUserEmail());
    }

    @Test
    public void testIncorrectPasswordsThenCorrect() {
        String simulatedInput = "123\njcole@gmail\nRb12@gl?]";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UserInputs userInput = new UserInputs();

        assertEquals("Rb12@gl?]", userInput.getUserPassword());
    }

    @Test
    public void testSimulateTransactions() {
        String simulatedInput = "simulate\n";
        InputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        UserInputs userInput = new UserInputs();

        //assertTrue(userInput.isValidAction(simulatedInput));
    }
}

