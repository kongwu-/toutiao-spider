package cc.leevi.toutiao;

import cc.leevi.toutiao.autoconfig.LocalConfig;
import cc.leevi.toutiao.model.QueryParam;
import cc.leevi.toutiao.util.BeanUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiang on 2017/5/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ToutiaoApplicationTest {
    @Autowired
    private LocalConfig localConfig;
    @Test
    public void test(){
        System.out.println(BeanUtils.bean2map(new QueryParam()));
    }
}
