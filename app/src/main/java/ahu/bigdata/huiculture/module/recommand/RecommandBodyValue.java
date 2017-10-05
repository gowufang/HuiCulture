package ahu.bigdata.huiculture.module.recommand;

import com.youdu.module.monitor.Monitor;
import com.youdu.module.monitor.emevent.EMEvent;

import java.util.ArrayList;

import ahu.bigdata.huiculture.module.BaseModel;

/**
 * Created by YCH on 2017/10/5.
 * Function:
 */
public class RecommandBodyValue extends BaseModel{

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    //视频专用
    public String thumb;
    public String resource;
    public String resourceID;
    public String adid;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;

}
