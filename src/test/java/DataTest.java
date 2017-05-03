import cc.leevi.toutiao.model.Content;
import cc.leevi.toutiao.model.Data;
import cc.leevi.toutiao.model.QueryParam;
import cc.leevi.toutiao.model.QueryRoot;
import cc.leevi.toutiao.util.BeanUtils;
import cc.leevi.toutiao.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by jiang on 2017/5/3.
 */
public class DataTest {
//    @Test
//    public void analyseData(){
//        ExecutorService threadPool = Executors.newCachedThreadPool();
//        for(int i = 0;i<100;i++){
//            threadPool.execute(new Spider());
//        }
//    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for(int i = 0;i<100;i++){
            threadPool.execute(new Spider());
        }
    }

    static class Spider implements Runnable{
        private OkHttpClient client = new OkHttpClient();
        private String baseUrl = "https://it.snssdk.com/api/news/feed/v53/";

        @Override
        public void run() {
            for(int i = 0;i<100;i++){
                try{
                    Map<String,Object> param = BeanUtils.bean2map(new QueryParam());

                    String url = StringUtils.buildUrl(baseUrl,param,true);
//                    System.out.println(url);
                    Request request = new Request.Builder()
                            .url(url)
                            .build();

                    Response response = client.newCall(request).execute();
                    QueryRoot root = JSON.parseObject(response.body().bytes(), QueryRoot.class);
                    for(Data data : root.getData()){
                        String contentJson = data.getContent();
                        Content content = JSON.parseObject(contentJson,Content.class);
                        if("广告".equals(content.getLabel())){
                            System.out.println(content.getSource());
                        }

//                        System.out.println(content.getSource());
                    }
                }catch (Exception e){

                }

            }
        }
    }
}
