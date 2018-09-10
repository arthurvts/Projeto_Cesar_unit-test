package school.cesar.unit;

import java.util.Collection;

public class EmailClientBuilder {

    private Collection<EmailAccount> account;
    private EmailService emailService;

    public EmailClientBuilder(){}

    public EmailClientBuilder setAccount(Collection<EmailAccount> account) {
        this.account = account;
        return this;
    }

    public EmailClientBuilder setEmailService(EmailService emailService) {
        this.emailService = emailService;
        return this;
    }

    public EmailClient build(){

        return new EmailClient(account, emailService);
    }

}
