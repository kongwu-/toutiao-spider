package cc.leevi.toutiao.process;

import cc.leevi.toutiao.model.Content;
import cc.leevi.toutiao.model.Data;
import cc.leevi.toutiao.model.QueryParam;
import cc.leevi.toutiao.model.QueryRoot;
import cc.leevi.toutiao.util.BeanUtils;
import cc.leevi.toutiao.util.StringUtils;
import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by jiang on 2017/5/4.
 */
public class Spider implements Runnable{

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * 要抓取的广告来源名称
     */
    private String source;

    private Integer counter = 0;

    /**
     * 关键词
     */
    private List<String> wordList;
//    private OkHttpClient client = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",1080))).build();
    private OkHttpClient client = new OkHttpClient();
    /**
     * 停止标识
     */
    private boolean stopped = false;

    /**
     * 广告label默认名称
     */
    private static final String adLabel = "广告";
    /**
     * 抓取地址前缀
     */
    private String baseUrl = "https://it.snssdk.com/api/news/feed/v53/";

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void run() {
        LOGGER.debug("来源：【{}】，关键词【{}】，当前线程：【{}】开始运行",source,StringUtils.join(wordList,","),Thread.currentThread().getName());
        LOGGER.debug(Thread.currentThread().getName());
        QueryParam queryParam = null;
        while (!stopped){
            try {
                if(counter%20==0){
                    counter = 0;
                    queryParam = new QueryParam();
                }else{
                    queryParam.setLoc_time(System.currentTimeMillis()/1000);
                    queryParam.setMin_behot_time(System.currentTimeMillis()/1000);
                    queryParam.set_rticket(System.currentTimeMillis());
                }

                Map<String,Object> param = BeanUtils.bean2map(new QueryParam());
                LOGGER.debug("当前线程：【{}】，正在抓取中",Thread.currentThread().getName());
                String url = StringUtils.buildUrl(baseUrl,param,true);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                QueryRoot root = JSON.parseObject(response.body().bytes(), QueryRoot.class);
                for(Data data : root.getData()){
                    String contentJson = data.getContent();
                    Content content = JSON.parseObject(contentJson,Content.class);
                    if(adLabel.equals(content.getLabel())){
                        if(StringUtils.isNotEmpty(source)&&source.equals(content.getSource())){
                            LOGGER.info(content.getTitle());
                            LOGGER.info("根据来源匹配到【来源({}){}】",content.getSource(),content.getTitle());
                            //处理
                        }else if(contains(content.getTitle())){
                            LOGGER.info("根据关键词匹配到【来源({}){}】",content.getSource(),content.getTitle());
                            //处理
                        }
                    }
                }
                counter++;
            }catch (Exception e){
                LOGGER.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    /**
     * 匹配关键词
     * @param search
     * @return
     */
    private boolean contains(String search){
        if(StringUtils.isNotEmpty(search)){
            search = search.trim();
            for(String word : wordList){
                if(search.contains(word)){
                    return true;
                }
            }
        }
        return false;
    }

    public Spider(String source,String keywords) {
        this.source = source;
        if(StringUtils.isNotEmpty(keywords)){
            this.wordList = Arrays.asList(StringUtils.split(keywords,","));
        }else{
            this.wordList = new ArrayList<>();
        }
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public static String getAdLabel() {
        return adLabel;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }
}
