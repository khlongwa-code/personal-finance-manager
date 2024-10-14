package unittest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import client.UserInputsValidator;

public class UserInputsValidatorTest {
    @Test
    public void testValidName() {
        UserInputsValidator validator = new UserInputsValidator();

        assertTrue(validator.isValidName("Makhosazane"));
    }

    @Test
    public void testNumericName() {
        UserInputsValidator validator = new UserInputsValidator();

        assertFalse(validator.isValidName("123"));
    }

    @Test
    public void testAlphaNumericName() {
        UserInputsValidator validator = new UserInputsValidator();

        assertFalse(validator.isValidName("Sphe123"));
    }

    @Test
    public void testEmptyName() {
        UserInputsValidator validator = new UserInputsValidator();

        assertFalse(validator.isValidName(""));
    }

    @Test
    public void testNameWithSymbols() {
        UserInputsValidator validator = new UserInputsValidator();

        assertFalse(validator.isValidName("@nele"));
    }
}
