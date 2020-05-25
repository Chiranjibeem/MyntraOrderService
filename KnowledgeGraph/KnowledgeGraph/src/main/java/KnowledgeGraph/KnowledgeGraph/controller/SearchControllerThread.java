package KnowledgeGraph.KnowledgeGraph.controller;

import KnowledgeGraph.KnowledgeGraph.model.SearchEntity;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Example of Java client calling Knowledge Graph Search API
 */
@Configuration
public class SearchControllerThread {

    private FileUtilityConfig fileUtilityConfig = new FileUtilityConfig();

    public static void main(String[] args) {
        SearchControllerThread searchExample = new SearchControllerThread();
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
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception("File Does Not Exist : " + filePath);
            }
            List<SearchEntity> searchEntities = fileUtilityConfig.getAllReport(file);
            List<SearchExecutor> executorList = new ArrayList<>();
            for (SearchEntity entity : searchEntities) {
                SearchExecutor searchExecutor = new SearchExecutor(fileUtilityConfig, entity.getCompany(), entity.getAssigned());
                executorList.add(searchExecutor);
            }
            if (!CollectionUtils.isEmpty(executorList)) {
                List<Future<SearchEntity>> searchEntityFutureList = executorService.invokeAll(executorList);
                List<SearchEntity> searchEntityResponseList = searchEntityFutureList.stream().map(sResponse -> {
                    try {
                        SearchEntity searchEntity = sResponse.get();
                        return searchEntity;
                    } catch (InterruptedException i) {
                        i.printStackTrace();

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(searchEntityResponseList)) {
                    searchEntityResponseList.add(fileUtilityConfig.addHeader());
                    fileUtilityConfig.generateStatusFile(searchEntityResponseList, System.getProperty("user.home") + "/" + "eduSystem" + getTimestamp() + ".csv");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        executorService.shutdown();
    }
}