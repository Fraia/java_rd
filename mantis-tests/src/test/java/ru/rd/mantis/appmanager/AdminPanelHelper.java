package ru.rd.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminPanelHelper extends HelperBase {

    public AdminPanelHelper(ApplicationManager app) {
        super(app);
    }

    public void clickMainMenuLink(String linkTitle) {
        click(By.xpath("//span[@class='menu-text' and contains(text(), '" + linkTitle + "')]"));
    }

    public void clickSubMenuLink(String linkTitle) {
        click(By.xpath("//ul[contains(@class, 'nav-tabs')]/li/a[text()='" + linkTitle + "']"));
    }

    public void goToUserManagement() {
        clickMainMenuLink("Manage");
        clickSubMenuLink("Manage Users");
    }
    public void searchUser(String username) {
        type(By.id("search"), username);
        click(By.cssSelector("input[value='Apply Filter']"));
    }

    public void clickUserLink(String username) {
        click(By.xpath("//table//td/a[text()='" + username + "']"));
    }

    public void selectUser(String username) {
        searchUser(username);
        clickUserLink(username);
    }

    public void resetPasswordOfUser(String username) {
        goToUserManagement();
        selectUser(username);
        resetPassword();
    }

    private void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void logout() {
        click(By.cssSelector("span.user-info"));
        click(By.xpath("//i[contains(@class, 'fa-sign-out')]/parent::a"));
    }
}
