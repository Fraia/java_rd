package ru.rd.addressbook.tests;

import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup (new GroupData("test1", "test2", "test3"));
        }

        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89046555467", "ivan@mail.ru", "test1"));
        }

        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Vanya", "Petrov", "89046333467", "vanya@mail.ru",null));
        app.getContactHelper().submitContactModification();
    }

}
