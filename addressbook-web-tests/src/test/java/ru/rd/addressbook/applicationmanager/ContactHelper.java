package ru.rd.addressbook.applicationmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.rd.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void saveContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void deleteContact() {
      acceptNextAlert = true;
      click(By.xpath("//input[@value='Delete']"));
      assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
    }

    public void selectContact() {
      click(By.name("selected[]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("email"), contactData.getEmail());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }
}
