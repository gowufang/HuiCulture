package ahu.bigdata.huiculture.module.recommand;

/**
 * Created by ych10 on 2017/10/3.
 * Function:最外层实体类
 */
public class BaseRecommandModel {
    //保证Json中与实际的Key完全一样，反射
    public String ecode;
    public String emsg;
    public RecommandModel data;
}
