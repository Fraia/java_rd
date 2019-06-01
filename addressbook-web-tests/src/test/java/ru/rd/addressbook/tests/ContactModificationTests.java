package ru.rd.addressbook.tests;

import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Vanya", "Petrov", "89046333467", "vanya@mail.ru"));
        app.getContactHelper().submitContactModification();
    }

}
