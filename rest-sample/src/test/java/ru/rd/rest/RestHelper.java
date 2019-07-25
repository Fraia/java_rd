package ru.rd.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.math.BigInteger;

public class RestHelper {

    private ApplicationManager app;
    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public Issue getIssueById(BigInteger issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("bugify.url")+ "/api/issues/" + issueId + ".json")).returnContent()
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
        return new Gson().fromJson(issue, new TypeToken<Issue>(){}.getType());
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
