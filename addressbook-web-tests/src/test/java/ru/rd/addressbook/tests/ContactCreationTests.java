package ru.rd.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation(){
    Contacts before=app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan")
            .withLastname("Ivanov").withHomePhone("89046555467").withEmail("ivan@mail.ru").withGroup("test1");
    app.contact().create(contact);
    Contacts after=app.contact().all();
    assertThat(after.size(), equalTo(before.size() +1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)-> c.getId()).max().getAsInt()))));
}

}
