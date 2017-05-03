package cc.leevi.toutiao.process;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by jiang on 2017/5/3.
 */
@Component
public class SpiderManager {
    @PostConstruct
    public void start(){
        System.out.println("run !!!");
    }

    @PreDestroy
    public void stop(){

    }
}
