package cc.leevi.toutiao;

import cc.leevi.toutiao.process.Spider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by jiang on 2017/5/5.
 */
public class ThreadPoolExecutor {
    private List<Spider> workers = new ArrayList<>();

    public void execute(Spider commad){
        new Thread(commad).start();
        workers.add(commad);
    }

    /**
     * 关闭所有线程
     */
    public void shutdown(){
        for(Spider commad : workers){
            commad.setStopped(true);
        }
    }

}
