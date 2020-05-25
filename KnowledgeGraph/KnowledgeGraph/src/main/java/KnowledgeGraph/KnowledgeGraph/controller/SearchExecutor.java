package KnowledgeGraph.KnowledgeGraph.controller;

import KnowledgeGraph.KnowledgeGraph.model.SearchEntity;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class SearchExecutor implements Callable<SearchEntity> {

    private String API_KEY = "AIzaSyACqQGfeCo-_jJCn8-Md292qHAauNKJ_UU";
    private String LIMIT = "10";
    private String INDENT = "true";
    private String type1 = "Organization";
    private String type2 = "EducationalOrganization";
    private String type3 = "GovernmentOrganization";
    private List<String> typeList = Arrays.asList(type1, type2, type3);

    private FileUtilityConfig utilityConfig;

    private String company;

    private String assignedName;

    public SearchExecutor(FileUtilityConfig utilityConfig, String company, String assignedName) {
        this.utilityConfig = utilityConfig;
        this.company = company;
        this.assignedName = assignedName;
    }

    @Override
    public SearchEntity call() throws Exception {
        HttpTransport httpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
        JSONParser parser = new JSONParser();
        GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
        url.put("query", company);
        url.put("limit", LIMIT);
        url.put("indent", INDENT);
        url.put("key", API_KEY);
        url.put("types", typeList);
        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse httpResponse = request.execute();
        JSONObject response = (JSONObject) parser.parse(httpResponse.parseAsString());
        JSONArray elements = (JSONArray) response.get("itemListElement");
        boolean flag = false;
        for (Object element : elements) {
            String result = JsonPath.read(element, "$.result.name").toString();
            if (result != null && result.toUpperCase().contains(company.toUpperCase())) {
                flag = true;
                break;
            }
        }
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setCompany(company);
        searchEntity.setCompany(assignedName);
        if (flag) {
            searchEntity.setType("Relevant");
        } else {
            searchEntity.setType("Irrelevant");
        }
        return searchEntity;
    }
}
