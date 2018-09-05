package school.cesar.unit;

import java.time.Instant;
import java.util.Collection;

public class EmailAccount {

    private String user;
    private String domain;
    private String password;

    public EmailAccount(String user, String domain, String password){
        this.user = user;
        this.domain = domain;
        this.password = password;

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

    public boolean verifyPasswordExpiration(){
        return false; //todo
    }


}
