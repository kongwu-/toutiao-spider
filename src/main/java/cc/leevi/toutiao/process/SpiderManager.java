package cc.leevi.toutiao.process;

import cc.leevi.toutiao.model.Plan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by jiang on 2017/5/3.
 */
@Component
public class SpiderManager {

    Map<String,ThreadPoolTaskExecutor> executors = new HashMap<>();

    private int threadNum = 10;

    private ThreadPoolTaskExecutor newExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        executor.initialize();
        return executor;
    }

    @PostConstruct
    public void start(){
        System.out.println("run !!!");
    }

    /**
     * 新建计划
     * @param plan
     */
    public void newPlan(Plan plan){
        ThreadPoolTaskExecutor executor = newExecutor();
        for(int i = 0;i<threadNum;i++){
            Spider spider = new Spider(plan.getSource(),plan.getKeywords());
            executor.execute(spider);
        }
        executors.put(String.valueOf(plan.getId()),executor);
    }


    @PreDestroy
    public void stop(){

    }
}
