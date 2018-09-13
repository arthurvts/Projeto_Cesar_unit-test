package school.cesar.unit;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;

public class EmailAccount {

    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;

    public EmailAccount(String user, String domain, String password, Instant lastPasswordUpdate){
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }


    //metodos

    public boolean isPasswordValid(String password, Instant lastPasswordUpdate) {
        return password.length() >= 6 && verifyPasswordExpiration(lastPasswordUpdate);

    }

        public boolean verifyPasswordExpiration(Instant lastPasswordUpdate){

        LocalDate today = LocalDate.now();
        if (this.lastPasswordUpdate.isAfter(Instant.from(today.minusDays(90)))){
            return false;
        }
        else {
            return true;
        }

    }

}
