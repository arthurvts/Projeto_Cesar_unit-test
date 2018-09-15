package school.cesar.unit;

import java.time.Instant;
import java.util.Collection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;



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

        @Test
        public void testingIsValidAddressCollection_UsingAValidEmailCollection() {
            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthurvts@hotmail.com");
            ((ArrayList<String>) to).add("a4t8@hotmail.com");
            ((ArrayList<String>) to).add("art-hur@hotmail.com");
            ((ArrayList<String>) to).add("art_hur@hotmail.com");
            ((ArrayList<String>) to).add("art-hu_r@hotmail.com");

            Assertions.assertTrue(emailClient.isValidAddress(to));
        }


        @Test
        public void testingIsValidAddressCollection_UsingAnInvalidEmailCollection() {
            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("a&*@hotmail.com");
            ((ArrayList<String>) to).add("art..@hotmail.com");
            ((ArrayList<String>) to).add("@rt_hur@hotmail.com");
            ((ArrayList<String>) to).add("art-hu_r@hotmail");

            Assertions.assertFalse(emailClient.isValidAddress(to));
        }

        @Test
        public void testingIsValidEmail_UsingValidEmailsValidFrom(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("arthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();
            ((ArrayList<String>) bcc).add("arthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt_hur@hotmail.com");

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("arthur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertTrue(emailClient.isValidEmail(email));
        }

        @Test
        public void testingIsValidEmail_UsingValidEmailsInvalidFrom(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("arthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();
            ((ArrayList<String>) bcc).add("arthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt_hur@hotmail.com");

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("art*hur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertFalse(emailClient.isValidEmail(email));

        }

        @Test
        public void testingIsValidEmail_UsingInvalidEmailsValidFrom(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("a@rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("ar*thur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();
            ((ArrayList<String>) bcc).add("arthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt._@hur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt_hur@hotmail.com");

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("arthur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertFalse(emailClient.isValidEmail(email));

        }

        @Test
        public void testingIsValidEmail_UsingInvalidEmailsInvalidFrom(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("a@rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("ar*thur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();
            ((ArrayList<String>) bcc).add("arthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt._@hur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt_hur@hotmail.com");

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("a@rt@hur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertFalse(emailClient.isValidEmail(email));

        }

        @Test
        public void testingIsValidEmail_UsingValidEmails_WithNoBccNoCc(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            bcc = new ArrayList<String>();

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("arthur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertTrue(emailClient.isValidEmail(email));

        }

        @Test
        public void testingIsValidEmail_UsingValidEmailsValidFrom_WithNoCreationDate(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("arthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();
            ((ArrayList<String>) bcc).add("arthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rthur@hotmail.com");
            ((ArrayList<String>) bcc).add("4rt_hur@hotmail.com");

            Email email = new EmailBuilder()
                    .setFrom("arthur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertFalse(emailClient.isValidEmail(email));
        }

        @Test
        public void testingIsValidEmail_UsingValidEmailsValidFrom_WithNoBccOnly(){

            to = new ArrayList<String>();
            ((ArrayList<String>) to).add("arthur@hotmail.com");
            ((ArrayList<String>) to).add("4rthur@hotmail.com");
            ((ArrayList<String>) to).add("4rt_hur@hotmail.com");

            cc = new ArrayList<String>();
            ((ArrayList<String>) cc).add("arthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rthur@hotmail.com");
            ((ArrayList<String>) cc).add("4rt_hur@hotmail.com");

            bcc = new ArrayList<String>();

            Email email = new EmailBuilder()
                    .setCreationDate(Instant.now())
                    .setFrom("arthur@hotmail.com")
                    .setTo(to)
                    .setCc(cc)
                    .setBcc(bcc)
                    .build();

            Assertions.assertTrue(emailClient.isValidEmail(email));
        }

        @Test
        public void creatingAnAccountUsingAValidAccount(){
            emailAccount = new EmailAccountBuilder()
                    .setUser("Arthur")
                    .setDomain("hotmail")
                    .setPassword("987654")
                    .setLastPasswordUpdate(Instant.now())
                    .build();

            Assertions.assertTrue(emailClient.createAccount(emailAccount));
        }

        @Test
        public void creatingAnAccountUsingAInvalidAccountPassword(){
            emailAccount = new EmailAccountBuilder()
                    .setUser("Arthur")
                    .setDomain("hotmail")
                    .setPassword("987")
                    .setLastPasswordUpdate(Instant.now())
                    .build();

            Assertions.assertFalse(emailClient.createAccount(emailAccount));
        }

        @Test
        public void creatingAnAccountUsingAInvalidAccountUser(){
            emailAccount = new EmailAccountBuilder()
                    .setUser("@rthu*")
                    .setDomain("hotmail")
                    .setPassword("847603")
                    .setLastPasswordUpdate(Instant.now())
                    .build();

            Assertions.assertFalse(emailClient.createAccount(emailAccount));
        }

        @Test
        public void creatingAnAccountUsingAInvalidAccountDomain(){
            emailAccount = new EmailAccountBuilder()
                    .setUser("Arthur")
                    .setDomain("hotm@il")
                    .setPassword("847603")
                    .setLastPasswordUpdate(Instant.now())
                    .build();

            Assertions.assertFalse(emailClient.createAccount(emailAccount));
        }








    }




