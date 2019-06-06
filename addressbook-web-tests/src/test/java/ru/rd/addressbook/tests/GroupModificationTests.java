package ru.rd.addressbook.tests;

import org.testng.annotations.Test;
import ru.rd.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification () {
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()) {
          app.getGroupHelper().createGroup (new GroupData("test1", "test2", "test3"));
      }
      app.getGroupHelper().selectGroup();
      app.getGroupHelper().initGroupModification();
      app.getGroupHelper().fillGroupForm(new GroupData("Test", "Test1", "Test3"));
      app.getGroupHelper().submitGroupModification();
      app.getGroupHelper().returnGroupPage();
  }
}
