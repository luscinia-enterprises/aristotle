package ca.luscinia.aristotle.database.user;

import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class PrivacySettings {

    @Id
    private ObjectId id;

    private Date lastUpdate;
    private boolean usageData= false;
    private boolean anonymized = true;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isUsageData() {
        return usageData;
    }

    public void setUsageData(boolean usageData) {
        this.usageData = usageData;
    }

    public boolean isAnonymized() {
        return anonymized;
    }

    public void setAnonymized(boolean anonymized) {
        this.anonymized = anonymized;
    }
}
