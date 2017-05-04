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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

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

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Spider spider = new Spider();
        for(int i = 0;i<50;i++){
            threadPool.execute(spider);
        }
        threadPool.shutdown();
        while(true){
            if(threadPool.isTerminated()){
                Set sources = spider.getSources();
                System.out.println(sources);
                break;
            }
            Thread.sleep(1000);
        }
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.shutdown();
        String str = "京东 , 福鑫珠宝, 滴滴出行, 点评美团教育推荐, 帆图-专业运动护膝, 彬范刀锋鞋, 美团点评爱车推荐, 烈火荣耀, 淘宝精品, 狼弋精品休闲鞋, 助贷网, 世基投资, 点评美团丽人推荐, 古剑奇侠OL-安卓神作, 黄金搭档云商, 东方神盾, 悦鹰旗舰店, 点评美团购物精选, 憨小二加盟, 淘啊·新款休闲布鞋, 鹿竹服饰, 众泰汽车S系列, 泉洋舒适鞋, 苍月传奇, 贵颜鞋, 点评美团爱车推荐, GAEA智能蓝牙眼镜, 风行商贸望远镜, 大众点评休闲精选, 亚马逊海外购, 王府学校, 易得贷, 酷纪官方旗舰店, 美团点评丽人推荐, 华夏袋鼠龙皮衣工厂, 加拿大旅游局, 潮鹿桑蚕丝西裤, 赢领全框mid防滑系列, 融360-贷款, 铂斯帝旗舰店, 酷牧男士套装官方店, 雀舌-绿茶专柜, 我买家, 力帆汽车, 悦鹰光学望远镜系列, vipabc, 能率官方自营旗舰店, 环球MBA, 艺星医疗美容医院, 永泰·西山御园, 狼弋男士皮鞋店, 大众点评美食精选, 点评美团亲子精选, 彬范官方店, 晨茗茶杯定制, 智恩电子手表9号店, 佰加佰教育, 北京巨人教育咨询中心, 美团点评购物精选, 华夏袋鼠龙官方1号店, 龙威盛数码旗舰店, 中讯股票投资咨询机构, 大众点评丽人推荐, 平安银行官网, 融360-信用卡, 大众点评购物精选, 咖沙利旗舰店, 酷购贸易, 非想天男鞋旗舰店, 多美居墙面瓷砖, 大众点评教育推荐, 妙妆桑蚕丝西裤, 保利大都汇, 点评美团结婚精选, 勇者大作战, 北京劲松口腔医院, 浦发信用卡官网, 大众点评爱车推荐, 帮客来移动支付, 钓鱼王-超级鱼竿, 雀舌-特卖店, 团车, 厦门威登-商贸男装, 康爵工厂店, 点评美团休闲精选, viperade-望远镜, 融360低息贷款, 易钱庄, 花花公子, 淄博巴菲雷商贸男装, 德锐高清望远镜, XMM精品男鞋, 点评美团美食精选, 顽美猫旗舰店, 贷款宝-在线放贷, 一步户外, 霁蓝服装, 美团点评教育推荐, 星玩娱乐, 胜帅皮鞋, 爱商网, 金鲨银鲨, 景粼原著玉府, 永康口腔, 深圳万好科技, 尚尔眼镜, 宅三国手游, 1918精制, ANTPO官方科技, 艺卡通, 百倍客手电筒, 融360小额贷款, 澳都狮, 欧迪朗眼镜5.1特卖, 远致精选木桶金骏眉, 大众点评购物推荐, 粤淘电商, 松浦原汁机, 美团点评健身精选, 宅三国, BANBIDILUN纯棉T恤, SIMWOOD旗舰店, 百卡凡诺, 麦哲森, 智宸真皮休闲鞋, 点评美团结婚推荐, ucc国际洗衣, 大众点评结婚精选, 宾兽裤子专卖店, 陆风汽车, BOSMA高清望远镜, 马尔卡农男鞋专卖, 苍茂智能科技, 360借条免息借钱, 美团点评教育精选, belona 光学, 北京中诺口腔医院, 中信银行信用卡中心, 安溪铁观音, 奥陆尔盾短装系列, 始祖兽旗舰店, 娜帕蒂卡进口乳胶枕, 碧莲盛植发, 林肯中国, 季言T恤套装, 凡购旗舰店周年庆活动, 逸炫特惠大活动, 酷纪旗舰店, 酷牧男士套装旗舰店, 一汽大众奥迪, 广州鹰尚纯棉麻套装, 十大品牌格美, 莎莱茜品牌男鞋, 360借条手机贷, 洛风男款短袖, 贝壳屋, 耐瑞钟表, 远致, 一往, 北京美莱医院, 花花公子男士网鞋, 泽泰家政保洁, 欧迪朗驾车镜专卖店, 傲徒皮鞋, 上汽斯柯达, 恩迪内裤, 奥陆尔盾刀锋蓝鞋系列, 360借条贷款, 率土之滨, BANBIDILUN印花短袖, 美团点评美食推荐, 蒂迈欧精品男鞋, 广州鹰尚品牌服装, 倚天逍遥录-官方手游, 呷哺呷哺, 美团点评休闲推荐, 点评美团教育精选, 德邦花花公子, GAEA智能 蓝牙眼镜, 季言男装, 布拉泽男士增高鞋, 吉普盾专卖店, 芭贝乐, 融360-金融平台, ITLY运动鞋, 狼弋官方促销活动, 狼弋潮流男鞋专卖店, 悦鹰光学仪器, 微酒客, 泉洋休闲鞋, 银悦貔貅, 融360H5, LAODILAISI官方直营店, 美团点评结婚推荐, 工商银行, 食客领先黄焖鸡, 双木林硅藻泥, 平安车险, 太神记, 马尔卡农男鞋旗舰店, 布兰奇国际洗衣连锁, 克酸清风葵花茶, 点评美团健身精选, 北京杜亚电动窗帘, 钓鱼王碳素鱼竿, 沙驰-VIP专柜, 花花公子凯轩旗舰店, BOSMA单筒望远镜, 狼弋精品四季鞋, 宾兽裤子旗舰店, 贷呗, 蒙牛优益C, 泉州西美商贸有限公司, 随行付官网, SHGO官方, 花花公子贵宾旗舰店, 融360, 墨麦客男装精品, 人人车卖车, 东之胜, JUSTMODE-暗花遮阳伞, 长安CX70, 沙驰-西裤单品, 酷牧男裤专卖店, 木棉谷官方旗舰店, 腾势汽车官网, 杰圣移民, WeWork联合办公, 融360极速贷款, 领拓男鞋专卖店, 曼纽拉, velutluna-正品店, 欧迪朗太阳镜专卖, 龙傲天下, 杠岗香功夫煲仔, 帕达索男鞋官方店, 点评美团购物推荐, Cakeboss蛋糕老板, 点评美团美食推荐, 大众点评教育精选, 启德教育, 速贷之家, 香香宫煮, 淘啊·镂空款驾车鞋, 鹰尚棉麻套装专柜, 德国汉莎航空, 96商机网, 扬子, Pardvisi短袖, 藤田渔具专卖店, 尚赫印花T, 点评美团休闲推荐, 北斗丽天-活动策划, 汉东潮流工厂, 吉普盾鳄鱼面网鞋, 美团点评购物推荐, 吉顺号茶业, 帕罗堡官方旗舰店, 胡莱三国2, 沙驰免烫桑蚕丝西裤, 帕达索旗舰店, 平安贷款, 宏图伟业, 浦发青春贷, 彭保商业周刊, 橙秀, 彬范官方正品店, 瑞士嘉年华爆款表, 优衣库, 洛风男士服装店, 中讯证券研究所, SAGA电子专卖, 智课网, 明若-透气运动鞋, 天天电玩城, 威世特商城, 吉普盾牛仔裤专场, 娇兰芭莉, 共青城宏鑫, Zegcine旗舰店, 东亚尚院, 艺尚太阳镜, 美团点评爱车精选, 美团点评丽人精选, 美团点评亲子推荐, 国美电器, 屠龙霸业, 柏卡狮服饰, playboy潮流-温州汉东, 五行天, 瑞士嘉年华手表, 赛维洗衣, 美团点评健身推荐, 大众点评丽人精选, 彬范印花板鞋官方店, JSMIX官方促销, 藤田渔具专卖, JSMIX官方店, 卡登龙, 竞思注意力训练, 交通银行信用卡, ANTPO移动电源店, 全民玩捕鱼, 淄博巴菲雷-商贸T恤, 嘉年华旗舰店, 瓜子二手车, LEJI充电宝旗舰店, 花花公子·透气网鞋, 香至尊沉香, 森蒙商贸, 贵颜休闲运动鞋, 欧迪朗太阳镜专卖店, 比亚迪, 泉城烤薯, 海风教育, 花花公子中润专柜, 美团点评休闲精选, 古剑奇侠OL-4D, 美团点评美食精选, 花花公子希牛 旗舰店, 雅涛品牌衬衫, 无忌江湖, 北京罗马风情婚纱摄影, 格美十大品牌, SD敢达强袭战线, 今日头条, 融360-手机贷, 领拓-皮鞋店, 君享明前雀舌绿茶, 一德物资, 北京冠美口腔医院, 奥陆尔盾买一送一, 36O借条秒借, 雅其金融, 路亚大师鱼竿专卖店, COCASES太阳能充电宝, 督马男装旗舰店, MatchU-衬衫定制, 季言休闲短袖衬衫, 黑曜石吊坠, Aikinson 男士T恤, 景城, 瑞琴手表, 古宇纯官方店, 浦发信用卡, somic-智能蓝牙耳机, 杰卡逊男士运动鞋, 融360-信用贷, JSMIX-夏日休闲装, 狼弋官方店, 阔达尚居, 广州鹰尚品牌服饰店, 风行户外官方旗舰店, 花花公子经典皮鞋, 雷克萨斯, 尚品宅配家具定制, 天下信用, 颜肌, 融360-小额贷, POF休闲裤品牌店, 融360贷款推荐, 随行付MPOS, 宝骏汽车, 酷购工厂, 季言男鞋, 长安铃木, 广州骄驰棉麻套装, 臻好时加盟, 格润思集成墙板, 五级过滤净水机-希力, 九天牧云记, 育兰茗萃茶业·滇红茶, itly旗舰店, 品菋时光-奶茶加盟, 嘉年华官方旗舰店, 视动世纪, 法莱卡丹服饰-折扣店, 北京 顾云律师事务所, 睿励人生留学, 米艾尔官方旗舰店, 杜康越来越旺, 雪渡绒男装特卖, 大众点评结婚推荐, 百盈教育, 尚品宅配, vipabc学堂, 美团点评结婚精选, 融360-简单借款, 一锅两头牛, Aikinson 纯棉T恤, 莎莱茜官方首发户外鞋, 艾菲尼-智能家居, 传奇战域官方, 守望先锋, Aikinson官方抢购活动, 帕达索官方店, 停车汇, 凤舞九天丝路宝玺, 百洁帮家电清洗, 蒂迈欧2017新款男鞋, 真人捕鱼, T-NNC时尚男装旗舰店, 航天伟业, 大众点评美食推荐, 圆点贷款, 重庆德荣仪器, 藤田钓具专卖店, 泸州老窖, 万利达制衣, 茅台镇封坛原浆收藏酒, 巴啦嘴, 咖沙利短袖衬衫男装, 奥陆尔盾刀锋鞋, 棉言-透气速干内裤, 峰鸟服饰, 缘诚制衣, 华夏袋鼠龙男装, 蒂迈欧官方旗舰店, 和中移民, 才子服饰, 高宾, 大众点评休闲推荐, 上海雪渡绒男装店, 五芳斋";
        System.out.println(str.split(",").length);
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
                    continue;
                }

            }
        }
    }
}
