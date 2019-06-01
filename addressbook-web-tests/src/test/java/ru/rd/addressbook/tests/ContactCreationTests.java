package ru.rd.addressbook.tests;


import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation(){
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "89046555467", "ivan@mail.ru"));
    app.getContactHelper().saveContact();
  }

}
