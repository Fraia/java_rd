package ru.rd.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.Contacts;
import ru.rd.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      app.contact().create(new ContactData().withFirstname("Ivan"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    Contacts before=app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().gotoHomePage();
    app.contact().deleteContact(deletedContact);
    Thread.sleep(3000);
    Contacts after=app.db().contacts();
    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();


  }
}
