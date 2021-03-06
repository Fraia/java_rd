package ru.rd.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.rd.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String password = "password";
        String user = String.format("user%s", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 1000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user,password));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
