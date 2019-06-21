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
    app.goTo().groupPage();
    if (!app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().gotoHomePage();
    if (! app.contact().isThereAContact()) {
      app.contact().create(new ContactData().withFirstname("Ivan"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {

    Contacts before=app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteContact(deletedContact);
    Contacts after=app.contact().all();
    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deletedContact)));


  }
}
