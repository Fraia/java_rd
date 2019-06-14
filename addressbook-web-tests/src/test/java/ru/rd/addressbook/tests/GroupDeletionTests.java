package ru.rd.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.rd.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup (new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() -1);
      Assert.assertEquals(before, after);

    }
  }

