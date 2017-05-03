import cc.leevi.toutiao.model.QueryParam;
import cc.leevi.toutiao.util.BeanUtils;
import com.github.kevinsawicki.http.HttpRequest;
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
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jiang on 2017/5/3.
 */
public class DataTest {
//    @Test
//    public void analyseData(){
//        String base = "https://it.snssdk.com/api/news/feed/v53/";
//        for(int i = 0;i<50;i++){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for(int i = 0;i<100;i++){
//                        Map<String,Object> param = BeanUtils.bean2map(new QueryParam());
//                        String body = HttpRequest.get(base,param,true).body();
//                        System.out.println(body);
//                    }
//                }
//            }).start();
//        }
//    }
static int counter = 0;
    public static void main(String[] args) {
        String base = "https://it.snssdk.com/api/news/feed/v53/";

        for(int i = 0;i<10;i++){
            new Thread(new Spider()).start();
        }
    }
    static class Spider implements Runnable{
        OkHttpClient client = new OkHttpClient();
        String base = "https://it.snssdk.com/api/news/feed/v53/";

        @Override
        public void run() {
            for(int i = 0;i<100;i++){
                try{
                    long cur = System.currentTimeMillis();
                    Map<String,Object> param = BeanUtils.bean2map(new QueryParam());



                    Request request = new Request.Builder()
                            .url("https://is.snssdk.com/api/news/feed/v53/?refer=1&count=20&min_behot_time=1493799445&last_refresh_sub_entrance_interval=1493799464&loc_mode=7&loc_time=1493797904&latitude=39.9409236288067&longitude=116.49989316176371&city=%E5%8C%97%E4%BA%AC%E5%B8%82&tt_from=pull&lac=4313&cid=58497&cp=5f920d9e93228q1&iid=9690476840&device_id=35407490883&ac=wifi&channel=huawei&aid=13&app_name=news_article&version_code=611&version_name=6.1.1&device_platform=android&ab_version=123182%2C112577%2C122834%2C119377%2C113607%2C123190%2C123187%2C121886%2C113608%2C122674%2C123600%2C123489%2C123564%2C123021%2C104325%2C123177%2C112578%2C122591%2C120226%2C122948%2C123125%2C31647%2C121768%2C123572%2C121005%2C122997%2C114338&ab_client=a1%2Cc4%2Ce1%2Cf2%2Cg2%2Cf7&ab_feature=102749%2C94563&abflag=3&ssmix=a&device_type=KNT-UL10&device_brand=HONOR&language=zh&os_api=24&os_version=7.0&uuid=869394027918041&openudid=7c22e8012094037d&manifest_version_code=611&resolution=1080*1832&dpi=480&update_version_code=6115&_rticket=1493799465000")
                            .build();

                    Response response = client.newCall(request).execute();

                    if(response.body().string().startsWith("{")){
                        counter++;
                        System.out.println(counter);
                    }else {
                        System.out.println("fail!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        /**
         * 向指定URL发送GET方法的请求
         *
         * @param url
         *            发送请求的URL
         * @param param
         *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
         * @return URL 所代表远程资源的响应结果
         */
        public String sendGet(String url) {
            String result = "";
            BufferedReader in = null;
            try {
                URL realUrl = new URL(url);
                // 打开和URL之间的连接
                URLConnection connection = realUrl.openConnection();
                // 设置通用的请求属性
                connection.setRequestProperty("accept", "*/*");
                connection.setRequestProperty("connection", "Keep-Alive");
                connection.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                // 建立实际的连接
                connection.connect();
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("发送GET请求出现异常！" + e);
                e.printStackTrace();
            }
            // 使用finally块来关闭输入流
            finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return result;
        }
    }
}
