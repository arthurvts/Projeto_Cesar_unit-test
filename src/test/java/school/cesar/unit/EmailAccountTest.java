package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailAccountTest {

    private EmailAccount isValidEmailAccount;
    private Email emailx;

    @BeforeEach
    public void setUp() {
        isValidEmailAccount = new EmailAccount();
        emailx = new EmailAccountBuilder()
                .setUser("arthur_vtavares")
                .setDomain("hotmail.com")
                .setPassword("12345")
                .setLastPasswordUpdate()
                .build();
    }

    @Test
    public void emailAccountOk() {
        Assertions.assertTrue();
    }

    @Test
    public void emailAccountNok() {
        Assertions.assertTrue();
    }

    @Test
    public void isPasswordExpired() {
        Assertions.assertTrue();
    }

}