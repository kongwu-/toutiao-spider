package cc.leevi.toutiao.model;

import java.util.List;

/**
 * Created by jiang on 2017-05-03.
 */
public class QueryRoot {
    private int login_status;

    private int total_number;

    private boolean has_more;

    private String post_content_hint;

    private int show_et_status;

    private int feed_flag;

    private String message;

    private boolean has_more_to_refresh;

    private List<Data> data ;

    private Tips tips;

    public void setLogin_status(int login_status){
        this.login_status = login_status;
    }
    public int getLogin_status(){
        return this.login_status;
    }
    public void setTotal_number(int total_number){
        this.total_number = total_number;
    }
    public int getTotal_number(){
        return this.total_number;
    }
    public void setHas_more(boolean has_more){
        this.has_more = has_more;
    }
    public boolean getHas_more(){
        return this.has_more;
    }
    public void setPost_content_hint(String post_content_hint){
        this.post_content_hint = post_content_hint;
    }
    public String getPost_content_hint(){
        return this.post_content_hint;
    }
    public void setShow_et_status(int show_et_status){
        this.show_et_status = show_et_status;
    }
    public int getShow_et_status(){
        return this.show_et_status;
    }
    public void setFeed_flag(int feed_flag){
        this.feed_flag = feed_flag;
    }
    public int getFeed_flag(){
        return this.feed_flag;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setHas_more_to_refresh(boolean has_more_to_refresh){
        this.has_more_to_refresh = has_more_to_refresh;
    }
    public boolean getHas_more_to_refresh(){
        return this.has_more_to_refresh;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setTips(Tips tips){
        this.tips = tips;
    }
    public Tips getTips(){
        return this.tips;
    }
}
