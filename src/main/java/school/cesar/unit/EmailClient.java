package school.cesar.unit;

import java.util.ArrayList;
import java.util.Collection;

public class EmailClient {

    private Collection<EmailAccount> accounts;
    private EmailService emailService;
    private Email email;


    public EmailClient(Collection<EmailAccount> account, EmailService emailService, Email email){

        this.accounts = account;
        this.emailService = emailService;
        this.email = email;
    }

    public Collection<EmailAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<EmailAccount> accounts) {
        this.accounts = accounts;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    //metodos usando o padrao Regex

    public static boolean isValidUser(String user){
        return user.matches("[a-zA-Z0-9._-]+");
    }

    public static boolean isValidDomain(String domain){
        if(domain.contains("..")){
            return false;
        } else {
            return domain.matches("^(?!\\.)[a-zA-Z0-9.]*[^.]$");
        }
    }

    public static boolean isValidAddress(String emailAddress){
        String[] temp = emailAddress.split("@");

        try {
            String userPattern = "^([a-zA-Z]|[0-9]|[.]|[_]|[-])*$";
            String domainPattern = "^([a-zA-Z]|[0-9])+(\\.([a-zA-Z]|[0-9])+)+$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(userPattern);
            java.util.regex.Matcher m = p.matcher(temp[0]);
            java.util.regex.Pattern p1 = java.util.regex.Pattern.compile(domainPattern);
            java.util.regex.Matcher m1 = p1.matcher(temp[1]);
            return m.matches() && m1.matches();
        } catch (Exception e){
            return false;
        }
    }


    public static boolean isValidEmail(Email email){
        return false; //todo
    }

    //Se password for invalido, levanta uma exceção do tipo RuntimeException.

    public Collection<Email> emailList(EmailAccount emailAccount){

        if(emailAccount.isPasswordValid(emailAccount.getPassword(), emailAccount.getLastPasswordUpdate())){
            return emailService.emailList(emailAccount);
        } else {
            throw new RuntimeException("Password has been expired.");
        }

    }

    //verifica se o email é válido. Se for falso, retorna uma exceção do tipo RuntimeException.

    public void sendEmail(Email email){

        if(isValidEmail(email)){
            emailService.sendEmail(email);
        } else {
            throw new RuntimeException("Invalid email.");
        }


    }

    boolean createAccount(EmailAccount account){
        return false;
    }



}
