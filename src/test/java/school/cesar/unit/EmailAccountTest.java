package school.cesar.unit;

import java.time.Instant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Date;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;

public class EmailAccountTest {


    private static long days;
    private static boolean expirationDateCheck;
    private static EmailAccount stubEmailAccount;


    private static EmailAccount emailAccount;
    private static String user;
    private static String domain;
    private static String password;
    private static Instant lastPasswordUpdate;


    @BeforeAll
    public static void setUp() {

        stubEmailAccount = new EmailAccount(user, domain, password, lastPasswordUpdate) {
            @Override

            public long daysFromLatestPasswordUpdate(Instant lastPasswordUpdate) {
                return days;
            }

            @Override

            public boolean verifyPasswordExpiration(Instant lastPasswordUpdate){
                return expirationDateCheck;
            }
        };
    }


    @Test
    public void isPasswordValid_usingInvalidPassword_withFiveCharactersNotExpired(){
        expirationDateCheck = true;
        Assertions.assertFalse(stubEmailAccount.isPasswordValid("78945", Instant.now()));
    }

    @Test
    public void isPasswordValid_usingInvalidPassword_withSixCharactersExpired(){
        expirationDateCheck = false;
        Assertions.assertFalse(stubEmailAccount.isPasswordValid("135796", Instant.now()));
    }

    @Test
    public void isPasswordValid_usingValidPassword_withSixCharactersNotExpired(){
        expirationDateCheck = true;
        Assertions.assertTrue(stubEmailAccount.isPasswordValid("135796", Instant.now()));
    }

    @Test
    public void isPasswordValid_usingInvalidPassword_FiveCharactersExpired(){
        expirationDateCheck = false;
        Assertions.assertFalse(stubEmailAccount.isPasswordValid("78945", Instant.now()));
    }

    @Test
    public void verifyPasswordExpiration_usingPasswordNotExpired() {
        days = 85;
        Assertions.assertTrue(stubEmailAccount.verifyPasswordExpiration(lastPasswordUpdate));
    }

    @Test
    public void verifyPasswordExpiration_usingPasswordExpired() {
        days = 98;
        Assertions.assertTrue(!stubEmailAccount.verifyPasswordExpiration(lastPasswordUpdate));
    }

    @Test
    public void hasThePasswordMoreThanSixCharacters_usingNineCharacters(){
        emailAccount = new EmailAccountBuilder()
                .setPassword("836021254")
                .build();
        Assertions.assertTrue(emailAccount.isPasswordMoreThanSixCharactersLong(emailAccount.getPassword()));
    }

    @Test
    public void hasThePasswordMoreThanSixCharacters_usingSixCharacters(){
        emailAccount = new EmailAccountBuilder()
                .setPassword("836210")
                .build();
        Assertions.assertTrue(emailAccount.isPasswordMoreThanSixCharactersLong(emailAccount.getPassword()));
    }

    @Test
    public void hasThePasswordMoreThanSixCharacters_usingFourCharacters(){
        emailAccount = new EmailAccountBuilder()
                .setPassword("8362")
                .build();
        Assertions.assertFalse(emailAccount.isPasswordMoreThanSixCharactersLong(emailAccount.getPassword()));
    }


    private Date daysSinceToday(Integer numberOfDaysToAdd) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, numberOfDaysToAdd);
        return cal.getTime();
    }


    @Test
    public void verifyDaysFromLastPasswordUpdate_100DaysBeforeToday() {

        emailAccount = new EmailAccountBuilder()
                .setLastPasswordUpdate(daysSinceToday(-100).toInstant())
                .build();
        Assertions.assertEquals(100, emailAccount.daysFromLatestPasswordUpdate(emailAccount.getLastPasswordUpdate()));
    }
    private Date days_From_Today(Integer daysToAdd) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysToAdd);
        return cal.getTime();
    }

}
