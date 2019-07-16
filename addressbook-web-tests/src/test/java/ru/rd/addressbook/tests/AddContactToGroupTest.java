package ru.rd.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.GroupData;
import ru.rd.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactToGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        // проверяем, что есть хотя бы один контакт
        if (app.db().contacts().size() == 0) {
            // если нет, то создаём контакт
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData().withFirstname("Ivan"));
        }

        // проверяем, что контакт добавлен не во все группы
        int countOfContactGroups = app.db().contacts().iterator().next().getGroups().size();
        if (countOfContactGroups == app.db().groups().size()) {
            // если контакт добавлен во все группы или групп вообще нет, то создаём новую
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
    }


    @Test
    public void addContactToGroupTest() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups before = contact.getGroups();
        Groups allGroups = app.db().groups();

        allGroups.removeAll(before);
        GroupData freeGroup = allGroups.iterator().next();

        app.contact().addContactToGroup(contact, freeGroup);

        Groups after = app.db().contact(contact.getId()).getGroups();

        assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(before.withAdded(freeGroup)));
    }

}
