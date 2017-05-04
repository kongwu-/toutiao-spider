package cc.leevi.toutiao.task;

import cc.leevi.toutiao.model.Plan;
import cc.leevi.toutiao.process.SpiderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by jiang on 2017/5/4.
 */
@Component
public class PlanTask {

    @Autowired
    private SpiderManager spiderManager;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 5000)
    public void refresh() {
        LOGGER.info("---------------------------------------refresh");
        Plan plan = new Plan();
        plan.setSource("京东");
        plan.setId(1);
        spiderManager.newPlan(plan);
    }
}
