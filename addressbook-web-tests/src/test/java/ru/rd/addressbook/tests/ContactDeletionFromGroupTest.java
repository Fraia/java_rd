package ru.rd.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.Contacts;
import ru.rd.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionFromGroupTest extends TestBase {

    GroupData group;

    @BeforeMethod
    public void ensurePreconditions() {
        // проверяем что существует хотя бы одна группа
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }

        // выбираем первую группу
        group = app.db().groups().iterator().next();
        // если в выбранной группе нет контактов, создаём новый контакт в этой группе
        if (group.getContacts().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withEmail("ivan@mail.ru").withHomePhone("93736283").inGroup(group));
        }
    }


    @Test
    public void deleteContactFromGroup() throws Exception {
        app.contact().selectGroupById(group.getId());
        Contacts before = app.db().group(group.getId()).getContacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().deleteContact(deletedContact);
        Thread.sleep(3000);
        Contacts after = app.db().group(group.getId()).getContacts();

        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedContact)));

    }
}
