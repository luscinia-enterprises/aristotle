package ca.luscinia.aristotle.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aristotle")
public class AristotleConfigProperties {
    private String title;
    private String author;
    private String version;
    private String description;
    private String owner;
    private boolean bootstrapBeta;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean getBootstrapBeta() {
        return bootstrapBeta;
    }

    public void setBootstrapBeta(boolean bootstrapBeta) {
        this.bootstrapBeta = bootstrapBeta;
    }

}
