import cc.leevi.toutiao.model.Content;
import cc.leevi.toutiao.model.Data;
import cc.leevi.toutiao.model.QueryParam;
import cc.leevi.toutiao.model.QueryRoot;
import cc.leevi.toutiao.util.BeanUtils;
import cc.leevi.toutiao.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public static void main(String[] args) throws InterruptedException, IOException {
//        Document doc = Jsoup.connect("http://anything.leevi.cc/city.html").get();
//        Elements elements = doc.getElementsByClass("content").get(0).select("a");
//        for(int i = 0;i<elements.size();i++){
//            String city = elements.get(i).text();
//            if(!city.contains("(")){
//                System.out.print("\""+city+"\",");
//            }
//        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Spider spider = new Spider();
        for(int i = 0;i<10;i++){
            threadPool.execute(spider);
        }

    }

    static class Spider implements Runnable{
//        private OkHttpClient client = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",1080))).build();
        private OkHttpClient client = new OkHttpClient();

        private Set<String> sources = Collections.synchronizedSet(new HashSet<String>());
        private String baseUrl = "https://it.snssdk.com/api/news/feed/v53/";

        public OkHttpClient getClient() {
            return client;
        }

        public void setClient(OkHttpClient client) {
            this.client = client;
        }

        public Set<String> getSources() {
            return sources;
        }

        public void setSources(Set<String> sources) {
            this.sources = sources;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        @Override
        public void run() {
            Map<String,Object> param = BeanUtils.bean2map(new QueryParam());
            for(int i = 0;i<1000;i++){
                try{
                    String url = StringUtils.buildUrl(baseUrl,param,true);
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
//
                    Response response = client.newCall(request).execute();
                    QueryRoot root = JSON.parseObject(response.body().bytes(), QueryRoot.class);
                    for(Data data : root.getData()){
                        String contentJson = data.getContent();
                        System.out.println(contentJson);
                        Content content = JSON.parseObject(contentJson,Content.class);
                        if("广告".equals(content.getLabel())){
                            sources.add(content.getSource());
                            System.out.println("【广告来源】"+content.getSource()+"-----"+content.getTitle());
                        }else{
//                            System.out.println("【标题】"+content.getTitle());
                        }

//                        System.out.println(content.getSource());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }
        }
    }
}
