package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class EmailAccountTest {


    private static EmailAccount emailAccountStub;
    private static long days;
    private static boolean expirationCheck;

    private static EmailAccount emailAccount;
    private static String user;
    private static String domain;
    private static String password;
    private static Instant lastPasswordUpdate;


    @BeforeAll
    public static void setUp() {
        emailAccountStub = new EmailAccount(user, domain, password, lastPasswordUpdate) {


            public long daysSinceLastPasswordUpdate(Instant lastPasswordUpdate) {
                return days;
            }

            public boolean verifyPasswordExpiration(Instant lastPasswordUpdate){
                return expirationCheck;
            }
        };
    }

    @Test
    public void isPasswordValid_ValidPassword_SixCharactersNotExpired(){
        expirationCheck = true;
        Assertions.assertTrue(emailAccountStub.isPasswordValid("135796", Instant.now()));
    }

    @Test
    public void isPasswordValid_InvalidPassword_FiveCharactersNotExpired(){
        expirationCheck = true;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("78945", Instant.now()));
    }

    @Test
    public void isPasswordValid_InvalidPassword_SixCharactersExpired(){
        expirationCheck = false;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("135796", Instant.now()));
    }

    @Test
    public void isPasswordValid_InvalidPassword_FiveCharactersExpired(){
        expirationCheck = false;
        Assertions.assertFalse(emailAccountStub.isPasswordValid("78945", Instant.now()));
    }

    @Test
    public void verifyPasswordExpiration_NotExpiredPassword() {
        days = 90;
        Assertions.assertTrue(emailAccountStub.verifyPasswordExpiration(lastPasswordUpdate));
    }

    @Test
    public void verifyPasswordExpiration_ExpiredPassword() {
        days = 91;
        Assertions.assertTrue(!emailAccountStub.verifyPasswordExpiration(lastPasswordUpdate));
    }


    private Date days_From_Today(Integer daysToAdd) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysToAdd);
        return cal.getTime();
    }

}
