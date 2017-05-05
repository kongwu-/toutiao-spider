package cc.leevi.toutiao.task;

import cc.leevi.toutiao.cache.Constant;
import cc.leevi.toutiao.mapper.PlanMapper;
import cc.leevi.toutiao.model.Plan;
import cc.leevi.toutiao.model.PlanExample;
import cc.leevi.toutiao.process.SpiderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiang on 2017/5/4.
 */
@Component
public class PlanTask {

    @Autowired
    private SpiderManager spiderManager;

    @Autowired
    private PlanMapper planMapper;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedRate = 10000)
    public void refresh() {
        executePendingPlan();
    }


    /**
     * 执行待处理的计划
     */
    private void executePendingPlan(){
        List<Plan> planList = getPendingPlan();
        LOGGER.info("本次待处理计划{}条",planList.size());
        for(Plan plan : planList){
            if(Constant.PLAN_STATUS_TORUN.equals(plan.getStatus())){
                spiderManager.startPlan(plan);
                plan.setStatus(Constant.PLAN_STATUS_RUNNING);
                planMapper.updateByPrimaryKeySelective(plan);
            }
            if(Constant.PLAN_STATUS_TOSTOP.equals(plan.getStatus())){
                spiderManager.stopPlan(plan);
                plan.setStatus(Constant.PLAN_STATUS_STOPPED);
                planMapper.updateByPrimaryKeySelective(plan);
            }
        }
    }

    /**
     * 获取待处理的计划
     * @return
     */
    private List<Plan> getPendingPlan(){
        PlanExample planExample = new PlanExample();
        List<String> statusList = new ArrayList();
        statusList.add(Constant.PLAN_STATUS_TORUN);
        statusList.add(Constant.PLAN_STATUS_TOSTOP);
        planExample.createCriteria().andStatusIn(statusList);
        return planMapper.selectByExample(planExample);
    }
}
