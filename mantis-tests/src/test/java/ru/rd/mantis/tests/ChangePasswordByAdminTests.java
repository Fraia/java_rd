package ru.rd.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.rd.mantis.appmanager.HttpSession;
import ru.rd.mantis.model.MailMessage;
import ru.rd.mantis.model.UserData;
import ru.rd.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ChangePasswordByAdminTests extends TestBase {

    private UserData user;

    @BeforeMethod
    public void before() {
        user = app.db().users().iterator().next();
        app.mail().start();
    }

    @Test
    public void changePasswordByAdmin() throws IOException, MessagingException {
        app.user().login("administrator", "root");

        app.adminPanel().resetPasswordOfUser(user.getUsername());
        app.adminPanel().logout();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 1000);
        String changePasswordLink = findChangePasswordLink(mailMessages, user.getEmail());

        String newPassword = "newPassword";
        app.user().changePassword(changePasswordLink, newPassword);

        HttpSession session = app.newSession();
        assertTrue(session.login(user.getUsername(),newPassword));
        assertTrue(session.isLoggedInAs(user.getUsername()));
    }

    private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);

    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
