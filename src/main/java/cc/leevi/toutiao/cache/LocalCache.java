package cc.leevi.toutiao.cache;

import cc.leevi.toutiao.model.Location;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by jiang on 2017/5/3.
 */
@Component
public class LocalCache {
    public static List<Location> locationPool;

    @PostConstruct
    public void init(){
    }
}
