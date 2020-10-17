package del.ac.id.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("store")
public class Store {
    @Id
    private String id;
    private String store_name;
    private String location;
    private Integer points;
    private String reputation;
    private Integer latest_online;

    public Store() {
    }

    public Store(String id, String store_name, String location, Integer points, String reputation, Integer latest_online) {
        this.id = id;
        this.store_name = store_name;
        this.location = location;
        this.points = points;
        this.reputation = reputation;
        this.latest_online = latest_online;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public Integer getLatest_online() {
        return latest_online;
    }

    public void setLatest_online(Integer latest_online) {
        this.latest_online = latest_online;
    }
}
