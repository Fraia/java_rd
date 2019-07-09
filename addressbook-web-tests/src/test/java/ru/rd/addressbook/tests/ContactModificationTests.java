package ru.rd.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.Contacts;
import ru.rd.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification () {
        Contacts before=app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Petrov").withHomePhone("89046333467").withEmail("vanya@mail.ru");
        app.contact().modify(contact);
        Contacts after=app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }



}
