package ru.rd.addressbook.applicationmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class ContactHelper {
    public boolean acceptNextAlert = true;
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {

        this.wd = wd;
    }

    public String closeAlertAndGetItsText() {
  try {
    Alert alert = wd.switchTo().alert();
    String alertText = alert.getText();
    if (acceptNextAlert) {
      alert.accept();
    } else {
      alert.dismiss();
    }
    return alertText;
  } finally {
    acceptNextAlert = true;
  }
}

    public void deleteContact() {
  acceptNextAlert = true;
  wd.findElement(By.xpath("//input[@value='Delete']")).click();
  assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
}

    public void selectContact() {
  wd.findElement(By.name("selected[]")).click();
}
}
