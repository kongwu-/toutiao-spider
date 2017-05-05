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

    @Scheduled(fixedRate = 5000000)
    public void refresh() {
        LOGGER.info("---------------------------------------refresh");
        Plan plan = new Plan();
        plan.setSource("京东");
        plan.setId(1);
//        plan.setKeywords("信用卡,汽车,贷款,神器,炒股,房价,经历,付款,面子,舒服,免费,震惊,抢购,满意");
        plan.setKeywords("办卡,额度,信用卡");
        spiderManager.newPlan(plan);
    }
}
