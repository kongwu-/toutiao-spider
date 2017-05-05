package cc.leevi.toutiao.process;

import cc.leevi.toutiao.ThreadPoolExecutor;
import cc.leevi.toutiao.model.Plan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang on 2017/5/3.
 */
@Component
public class SpiderManager {

    Map<String,ThreadPoolExecutor> executors = new HashMap<>();

    private int threadNum = 5;

    private ThreadPoolExecutor newExecutor(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor();
        return executor;
    }

    @PostConstruct
    public void start(){
        System.out.println("run !!!");
    }

    /**
     * 删除计划
     * @param plan
     */
    public void stopPlan(Plan plan){
        ThreadPoolExecutor executor = executors.get(String.valueOf(plan.getId()));
        if(executor!=null){
            executor.shutdown();
        }
        executors.remove(plan.getId());
    }

    /**
     * 新建计划
     * @param plan
     */
    public void startPlan(Plan plan){
        ThreadPoolExecutor executor = newExecutor();

        for(int i = 0;i<threadNum;i++){
            Spider spider = new Spider(plan);
            executor.execute(spider);
        }
        executors.put(String.valueOf(plan.getId()),executor);
    }

    @PreDestroy
    public void stop(){

    }
}
