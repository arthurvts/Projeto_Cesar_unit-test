package school.cesar.unit;

import java.util.ArrayList;
import java.util.Collection;

public class EmailClient {

    private Collection<EmailAccount> accounts;
    private EmailService emailService;

    public void setEmailService(){
        //todo
    }

    public boolean isValidAddress(){
        return false; //todo
    }

    public boolean isValidEmail(){
        return false; //todo
    }

    public Collection<Email> emailList(EmailAccount account){
        return emailService.emailList(account); //todo
    }

    public void sendEmail(){
        //todo
    }

    boolean createAccount(EmailAccount account){
        return false; //todo
    }



}
