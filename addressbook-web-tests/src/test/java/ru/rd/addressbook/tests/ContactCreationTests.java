package ru.rd.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test
  public void testContactCreation(){
    List<ContactData> before=app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ivan", "Ivanov", "89046555467", "ivan@mail.ru", "test1");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().saveContact();
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1 );



    int max= 0;
    for (ContactData g : after){
      if (g.getId()>max){
        max = g.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
