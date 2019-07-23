package ru.rd.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.rd.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void before() throws RemoteException, ServiceException, MalformedURLException {
        BigInteger issueId = new BigInteger("2");
        skipIfNotFixed(issueId);
    }

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator","root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
