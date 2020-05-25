package KnowledgeGraph.KnowledgeGraph.controller;

import KnowledgeGraph.KnowledgeGraph.model.SearchEntity;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

/**
 * Example of Java client calling Knowledge Graph Search API
 */
@Configuration
public class SearchController {

    private String API_KEY = "AIzaSyD_Ejsx2U3srog0NAA1C0g1q1BdOoLV3wo";
    private String LIMIT = "10";
    private String INDENT = "true";
    private String type1 = "Organization";
    private String type2 = "EducationalOrganization";
    private String type3 = "GovernmentOrganization";
    private List<String> typeList = Arrays.asList(type1, type2, type3);
    private FileUtilityConfig fileUtilityConfig = new FileUtilityConfig();


    public static void main(String[] args) {
        SearchController searchExample = new SearchController();
        if (args[0] != null) {
            String filePath = args[0].trim();
            if (filePath.toUpperCase().endsWith("CSV")) {
                searchExample.findRelevantOrNot(filePath);
            } else {
                System.out.println("File Should Be CSV");
            }
        } else {
            System.out.println("Please Give File Path");
        }
    }

    public String getTimestamp() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public void findRelevantOrNot(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception("File Does Not Exist : " + filePath);
            }
            List<SearchEntity> searchEntities = fileUtilityConfig.getAllReport(file);
            // endpoints to use in list.subList() function
            int[] endpoints = {0, (searchEntities.size() + 1) / 2, searchEntities.size()};

            List<List<SearchEntity>> lists =
                    IntStream.rangeClosed(0, 1)
                            .mapToObj(i -> searchEntities.subList(endpoints[i], endpoints[i + 1]))
                            .collect(Collectors.toList());

            List[] partList = new List[]{lists.get(0), lists.get(1)};

            for (int i = 0; i < partList.length; i++) {
                List<SearchEntity> entityList = partList[i];
                List<SearchEntity> searchEntityList = new ArrayList<SearchEntity>();
                searchEntityList.add(fileUtilityConfig.addHeader());
                for (SearchEntity entity : entityList) {
                    HttpTransport httpTransport = new NetHttpTransport();
                    HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
                    JSONParser parser = new JSONParser();
                    GenericUrl url = new GenericUrl("https://kgsearch.googleapis.com/v1/entities:search");
                    String query = entity.getCompany();
                    url.put("query", query);
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
                        if (result != null && result.toUpperCase().contains(query.toUpperCase())) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        SearchEntity searchEntity = new SearchEntity();
                        searchEntity.setCompany(entity.getCompany());
                        searchEntity.setAssigned(entity.getAssigned());
                        searchEntity.setType("Relevant");
                        searchEntityList.add(searchEntity);
                    } else {
                        SearchEntity searchEntity = new SearchEntity();
                        searchEntity.setCompany(entity.getCompany());
                        searchEntity.setAssigned(entity.getAssigned());
                        searchEntity.setType("Irrelevant");
                        searchEntityList.add(searchEntity);
                    }
                }
                    if (!CollectionUtils.isEmpty(searchEntityList)) {
                        fileUtilityConfig.generateStatusFile(searchEntityList, System.getProperty("user.home") + "/" + "eduSystem" + getTimestamp() + ".csv");
                    }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}