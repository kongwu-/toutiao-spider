package cc.leevi.toutiao.autoconfig;

import cc.leevi.toutiao.model.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by jiang on 2017/5/3.
 */
@Component
@ConfigurationProperties(prefix = "local")
public class LocalConfig {
    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
