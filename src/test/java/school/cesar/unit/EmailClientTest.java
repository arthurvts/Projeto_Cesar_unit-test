package school.cesar.unit;

import java.time.Instant;
import java.util.Collection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


    public class EmailClientTest {
        private static Collection<String> to;
        private static Collection<String> cc;
        private static Collection<String> bcc;
        private static EmailClient emailClient;
        private static EmailAccount emailAccount;
        private static String user;
        private static String domain;
        private static String password;
        private static Instant lastPasswordUpdate;
        private static boolean validUser;
        private static Collection<EmailAccount> accounts;


        @BeforeAll
        public static void setUp() {
            emailClient = new EmailClient();
        }

        @Test
        public void testingIsValidAddress_UsingValidUserAndInvalidDomain() {
            Assertions.assertFalse(emailClient.isValidAddress("arthurvts@hotmail..com"));
        }

        @Test
        public void testingIsValidAddress_UsingInvalidUserAndInvalidDomain() {
            Assertions.assertFalse(emailClient.isValidAddress("@rthur@hotmail.com"));
        }

        @Test
        public void testingIsValidAddress_UsingInvalidAddress_WithoutArroba() {
            Assertions.assertFalse(emailClient.isValidAddress("arthurvts.com"));
        }

        @Test
        public void testingIsValidAddress_UsingInvalidAddress_ArrobaAndDotTogether() {
            Assertions.assertFalse(emailClient.isValidAddress("@.rthurvts@hotmail.com"));
        }

        @Test
        public void thisValidationShouldReturnFalse_WhenInvalidDomainTyped_UsingDotInFirstCharacter() {
            Assertions.assertFalse(emailClient.isAValidDomain(".hotmail.com"));
        }

        @Test
        public void thisValidationReturnsTrue_WhenAValidDomainIsTyped() {
            Assertions.assertTrue(emailClient.isAValidDomain("hotmail.com"));
        }

        @Test
        public void thisValidationReturnsFalse_WhenAnInvalidDomainTyped_UsingDotInLastCharacter() {
            Assertions.assertFalse(emailClient.isAValidDomain("hotmail.com."));
        }

        @Test
        public void thisValidationReturnsFalse_WhenInvalidDomainTyped_UsingTwoConsecutiveDots() {
            Assertions.assertFalse(emailClient.isAValidDomain("hotmail..com"));
        }

        @Test
        public void ThisUserValidationShouldReturnFalse_WhenInvalidUserIsTyped() {
            Assertions.assertFalse(emailClient.isAValidUser("An,~ony&#.usu@rio"));
        }

        @Test
        public void ThisUserValidationShouldReturnTrue_WhenValidUserTyped() {
            Assertions.assertTrue(emailClient.isAValidUser("usu4Ri0._vA-lid0"));
        }











    }




