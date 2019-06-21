package ru.rd.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.ContactData;
import ru.rd.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFieldsTests  extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().gotoHomePage();
        if (! app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withFirstname("Ivan").withHomePhone("73737373737").withAddress("Test").withEmail("vanya@mail.ru"));
        }
    }

    @Test
    public void testContactFields() {
        app.goTo().gotoHomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }

    private String mergePhones(ContactData contact) {

        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactFieldsTests::cleaned)
                .collect(Collectors.joining("\n"));


    }

    private String mergeEmails(ContactData contact) {

        return Arrays.asList(contact.getEmail(), contact.getEmail1(), contact.getEmail2())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactFieldsTests::clean)
                .collect(Collectors.joining("\n"));


    }



    public static String cleaned (String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
    public static String clean (String email){
        return email.replaceAll("\\s","");
    }

    
    
    
}
