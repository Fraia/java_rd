package ru.rd.addressbook;


import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion() throws Exception {
    selectContact();
    deleteContact();
  }

}
