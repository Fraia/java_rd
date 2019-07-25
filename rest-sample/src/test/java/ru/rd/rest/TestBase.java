package ru.rd.rest;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.math.BigInteger;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    private boolean isIssueOpen(BigInteger issueId) throws IOException {
        Issue issue = app.rest().getIssueById(issueId);

        return !issue.getStateName().equals("Resolved");
    }

    public void skipIfNotFixed(BigInteger issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
