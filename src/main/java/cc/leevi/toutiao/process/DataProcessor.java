package cc.leevi.toutiao.process;

import cc.leevi.toutiao.mapper.PlanDataMapper;
import cc.leevi.toutiao.model.Content;
import cc.leevi.toutiao.model.PlanData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据处理
 * Created by jiang on 2017/5/5.
 */
@Component
public class DataProcessor {
    @Autowired
    private PlanDataMapper planDataMapper;

    /**
     * 添加计划结果
     * @param planId
     * @param content
     */
    public void add(Integer planId,String content){
        PlanData planData = new PlanData();
        planData.setPlanId(planId);
        planData.setContent(content);
        planDataMapper.insertSelective(planData);
    }
}
