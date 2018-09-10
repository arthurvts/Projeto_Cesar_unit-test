package school.cesar.unit;

import java.util.ArrayList;
import java.util.Collection;

public class EmailClient {

    private Collection<EmailAccount> accounts;
    private EmailService emailService;

    public EmailClient(Collection<EmailAccount> account, EmailService emailService){

        this.accounts = account;
        this.emailService = emailService;
    }

    public Collection<EmailAccount> getAccount() {
        return accounts;
    }

    public void setAccount(Collection<EmailAccount> account) {
        this.accounts = account;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    //metodos

    public void setEmailService(){}

    public boolean isValidAddress(String accounts ){
        String[] temp = accounts.split("@");

        try {
            String userPattern = "^([a-zA-Z]|[0-9]|[.]|[_]|[-])*$";
            String domainPattern = "^([a-zA-Z]|[0-9])+(\\.([a-zA-Z]|[0-9])+)+$";
            //String userPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(userPattern);
            java.util.regex.Matcher m = p.matcher(temp[0]);
            java.util.regex.Pattern p1 = java.util.regex.Pattern.compile(domainPattern);
            java.util.regex.Matcher m1 = p1.matcher(temp[1]);
            return m.matches() && m1.matches();
        } catch (Exception e){
            return false;
        }
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
