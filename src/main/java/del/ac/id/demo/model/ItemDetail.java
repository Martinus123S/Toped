package del.ac.id.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemDetail {
    private double weight;
    private String condition;
    private String insurance;
    private String category;

    public ItemDetail() {
    }

    public ItemDetail(double weight, String condition, String insurance, String category) {
        this.weight = weight;
        this.condition = condition;
        this.insurance = insurance;
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
