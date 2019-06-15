package ru.rd.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import java.util.Comparator;
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



    before.add(contact);
    Comparator<? super ContactData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
}

}
