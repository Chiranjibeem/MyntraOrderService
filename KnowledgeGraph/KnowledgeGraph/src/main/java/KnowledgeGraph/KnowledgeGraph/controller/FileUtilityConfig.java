package KnowledgeGraph.KnowledgeGraph.controller;

import KnowledgeGraph.KnowledgeGraph.model.SearchEntity;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class FileUtilityConfig {

    public SearchEntity addHeader() {
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setCompany("Company");
        searchEntity.setAssigned("Assigned");
        searchEntity.setType("Type");
        return searchEntity;
    }

    public boolean generateStatusFile(List<SearchEntity> searchEntities, String fileName) {
        FileWriter fileWriter = null;
        CSVWriter writer = null;
        File file = null;
        try {
            if (fileName != null) {
                file = new File(fileName.replaceAll("%20", " "));
                if (!file.exists()) {
                    file.createNewFile();
                    file.canRead();
                    file.canWrite();
                    file.canExecute();
                }
            }
            fileWriter = new FileWriter(file, false);
            List<String[]> data = searchEntities.stream().map(i -> new String[]{i.getCompany(), i.getAssigned(), i.getType()}).collect(Collectors.toList());
            writer = new CSVWriter(fileWriter, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception e) {

                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }


    public List<SearchEntity> getAllReport(File file) throws Exception {
        List<SearchEntity> emailTemplatesList = new ArrayList<SearchEntity>();
        HeaderColumnNameTranslateMappingStrategy<SearchEntity> strategy
                = new HeaderColumnNameTranslateMappingStrategy<SearchEntity>();
        strategy.setType(SearchEntity.class);
        strategy.setColumnMapping(SearchEntity.hashMap);

        CSVReader csvReader = null;
        csvReader = new CSVReader(new FileReader(file));
        CsvToBean csvToBean = new CsvToBean();
        csvToBean.setMappingStrategy(strategy);
        csvToBean.setCsvReader(csvReader);
        emailTemplatesList = csvToBean.parse();
        return emailTemplatesList;
    }
}
