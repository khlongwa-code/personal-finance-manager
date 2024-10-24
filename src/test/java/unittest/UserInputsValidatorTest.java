package unittest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import client.UserInputsValidator;

public class UserInputsValidatorTest {
    UserInputsValidator validator = new UserInputsValidator();

    @Test
    public void testValidName() {
        assertTrue(validator.isValidName("Makhosazane"));
    }

    @Test
    public void testNumericName() {
        assertFalse(validator.isValidName("123"));
    }

    @Test
    public void testAlphaNumericName() {
        assertFalse(validator.isValidName("Sphe123"));
    }

    @Test
    public void testEmptyName() {
        assertFalse(validator.isValidName(""));
    }

    @Test
    public void testNameWithSymbols() {
        assertFalse(validator.isValidName("@nele"));
    }

    @Test
    public void testPasswordSameCharacterCategory() {
        assertFalse(validator.isValidPassword("123456789"));
        assertFalse(validator.isValidPassword("abcdefr"));
        assertFalse(validator.isValidPassword("&%$#@!?>)_-"));
    }


    @Test
    public void testValidPasswordTooShort() {
        assertFalse(validator.isValidPassword("Rb1@gl"));
    }

    @Test
    public void testValidPassword() {
        assertTrue(validator.isValidPassword("Rb12@gl?]"));
    }

    // @Test
    // public void guidTest() {
    //     assertEquals(1, 2);
    // }
}
