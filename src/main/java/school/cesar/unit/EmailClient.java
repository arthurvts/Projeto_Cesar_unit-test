package school.cesar.unit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class EmailClient {

    private EmailService emailService;


    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public boolean isAValidUser(String user) {
        return user.matches("[a-zA-Z0-9._-]+");
    }

    public boolean isAValidDomain(String domain) {
        if (domain.contains("..")) {
            return false;
        } else {
            return domain.matches("^(?!\\.)[a-zA-Z0-9.]*[^.]$");
        }
    }

//Um endereço é considerado valido se possuir usuario valido, seguido pelo caractere arroba (@) e posteriormente um dominio valido.

    public boolean isValidAddress(String emailAddress) {
        String[] temp = emailAddress.split("@");

        if (emailAddress.split("@").length == 2) {

            String user = emailAddress.split("@")[0];
            String domain = emailAddress.split("@")[1];

            return isAValidUser(user) && isAValidDomain(domain);
        } else {
            return false;
        }
    }

    public boolean isValidAddress(Collection<String> emailAddresses) {
        boolean flag = true;

        for (String emailAddress : emailAddresses) {
            if (!isValidAddress(emailAddress)) {
                flag = false;
            }
        }
        return flag;
    }


    public boolean isValidEmail(Email email) {

        //creationDate valido
        if (email.getCreationDate() != null) {
            //endereço valido
            if (isValidAddress(email.getFrom())) {
                //to valido
                if (email.getTo().size() != 0 && isValidAddress(email.getTo())) {
                    //Cc valido
                    if (email.getCc().size() != 0 && !isValidAddress(email.getCc())) {

                        return false;
                    }

                    if (email.getBcc().size() != 0 && !isValidAddress(email.getBcc())) {

                        return false;
                    }
                } else return false;
            } else return false;
        } else return false;

        return true;
    }


    //Se password for invalido, levanta uma exceção do tipo RuntimeException.

    public Collection<Email> emailList(EmailAccount emailAccount) {

        if (emailAccount.isPasswordValid(emailAccount.getPassword(), emailAccount.getLastPasswordUpdate())) {
            return emailService.emailList(emailAccount);
        } else {
            throw new RuntimeException("Your password has been expired.");
        }
    }

    //verifica se o email é válido. Se for falso, retorna uma exceção do tipo RuntimeException.

    public void sendEmail(Email email) {

        if (isValidEmail(email)) {
            emailService.sendEmail(email);
        } else {
            throw new RuntimeException("This is an invalid email.");
        }
    }

    //verifica se o usuario e o dominio são validos;
    //verifica se o password é maior que 6 caracteres;
    //adiciona data atual ao lastPasswordUpdate;
    //adiciona objeto a coleção accounts.

    boolean createAccount(EmailAccount account) {
        Collection<EmailAccount> accounts = new ArrayList<EmailAccount>();

        if (isAValidUser(account.getUser())) {

            if (isAValidDomain(account.getDomain())) {

                if (account.isPasswordMoreThanSixCharactersLong(account.getPassword())) {

                    account.setLastPasswordUpdate(Instant.now());

                    return accounts.add(account);
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

}

