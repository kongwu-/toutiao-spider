package cc.leevi.toutiao.model;

import cc.leevi.toutiao.util.StringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

/**
 * Created by jiang on 2017/5/3.
 */
public class QueryParam {
    private String refer = "1";
    private Integer count = 20;
    private Long min_behot_time;
    private Long last_refresh_sub_entrance_interval = System.currentTimeMillis() / 1000;
    private Integer loc_mode = 8;
    private Long loc_time = System.currentTimeMillis() / 1000;
    private Double latitude = StringUtils.randomLat();
    private Double longitude = StringUtils.randomLon();
    private String city = StringUtils.randomCity();
    private String tt_from = "pre_load_more";//pre_load_more
    private Integer lac = 1234;
    private String cid = String.valueOf(RandomUtils.nextInt(1000,9999));
    private String cp = String.valueOf(RandomUtils.nextInt(1000,9999));
    private String iid = String.valueOf(RandomUtils.nextLong(10000000,999999999));
    private String device_id = String.valueOf(RandomUtils.nextInt(100000,1000000));
    private String ac = "wifi";
    private String channel = "huawei";
    private String aid = "13";
    private String app_name = "news_article";
    private Integer version_code = 611;
    private String version_name = "6.1.1";
    private String device_platform = "ios";
    private String ab_version = "";
    private String ab_client = "a1,c4,e1,f2,g2,f7";
    private String ab_feature = "102749,94563";
    private Integer abflag = 3;
    private String ssmix = "a";
    private String device_type = "KNT-UL10";
    private String device_brand = "HONOR";
    private String language = "zh";
    private Integer os_api = 24;
    private String os_version = "7.0";
    private String uuid = UUID.randomUUID().toString().replace("-","");
    private String openudid = UUID.randomUUID().toString().replace("-","");
    private Integer manifest_version_code = 611;
    private String resolution = "1080*1832";
    private Integer dpi = 480;
    private Integer update_version_code = 6115;
    private Long _rticket = System.currentTimeMillis();

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getMin_behot_time() {
        return min_behot_time;
    }

    public void setMin_behot_time(Long min_behot_time) {
        this.min_behot_time = min_behot_time;
    }

    public Long getLast_refresh_sub_entrance_interval() {
        return last_refresh_sub_entrance_interval;
    }

    public void setLast_refresh_sub_entrance_interval(Long last_refresh_sub_entrance_interval) {
        this.last_refresh_sub_entrance_interval = last_refresh_sub_entrance_interval;
    }

    public Integer getLoc_mode() {
        return loc_mode;
    }

    public void setLoc_mode(Integer loc_mode) {
        this.loc_mode = loc_mode;
    }

    public Long getLoc_time() {
        return loc_time;
    }

    public void setLoc_time(Long loc_time) {
        this.loc_time = loc_time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTt_from() {
        return tt_from;
    }

    public void setTt_from(String tt_from) {
        this.tt_from = tt_from;
    }

    public Integer getLac() {
        return lac;
    }

    public void setLac(Integer lac) {
        this.lac = lac;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getAc() {
        return ac;
    }

    public void setAc(String ac) {
        this.ac = ac;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public Integer getVersion_code() {
        return version_code;
    }

    public void setVersion_code(Integer version_code) {
        this.version_code = version_code;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public void setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
    }

    public String getAb_version() {
        return ab_version;
    }

    public void setAb_version(String ab_version) {
        this.ab_version = ab_version;
    }

    public String getAb_client() {
        return ab_client;
    }

    public void setAb_client(String ab_client) {
        this.ab_client = ab_client;
    }

    public String getAb_feature() {
        return ab_feature;
    }

    public void setAb_feature(String ab_feature) {
        this.ab_feature = ab_feature;
    }

    public Integer getAbflag() {
        return abflag;
    }

    public void setAbflag(Integer abflag) {
        this.abflag = abflag;
    }

    public String getSsmix() {
        return ssmix;
    }

    public void setSsmix(String ssmix) {
        this.ssmix = ssmix;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_brand() {
        return device_brand;
    }

    public void setDevice_brand(String device_brand) {
        this.device_brand = device_brand;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getOs_api() {
        return os_api;
    }

    public void setOs_api(Integer os_api) {
        this.os_api = os_api;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOpenudid() {
        return openudid;
    }

    public void setOpenudid(String openudid) {
        this.openudid = openudid;
    }

    public Integer getManifest_version_code() {
        return manifest_version_code;
    }

    public void setManifest_version_code(Integer manifest_version_code) {
        this.manifest_version_code = manifest_version_code;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public Integer getUpdate_version_code() {
        return update_version_code;
    }

    public void setUpdate_version_code(Integer update_version_code) {
        this.update_version_code = update_version_code;
    }

    public Long get_rticket() {
        return _rticket;
    }

    public void set_rticket(Long _rticket) {
        this._rticket = _rticket;
    }

    public QueryParam() {
    }
}