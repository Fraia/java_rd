package ru.rd.mantis.appmanager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.id("username"), username);
        click(By.cssSelector("input[value='Login']"));
        type(By.id("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void changePassword(String changePasswordLink, String newPassword) {
        wd.get(changePasswordLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//span[text()='Update User']"));
    }
}
