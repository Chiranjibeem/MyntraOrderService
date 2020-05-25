package KnowledgeGraph.KnowledgeGraph.model;

import java.util.HashMap;

public class SearchEntity {

    public static HashMap<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("Company", "Company");
        hashMap.put("Assigned", "Assigned");
        hashMap.put("Type", "Type");
    }

    private String company;
    private String assigned;
    private String type;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
