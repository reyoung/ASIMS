package controllers;

import models.AirlineStatus;
import models.Page;
import play.db.jpa.GenericModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-29
 * Time: 下午2:58
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminAirlineStatus extends BaseAdminController {
    public static void list(Integer page, Integer pageSize,String num, Integer type){
        if(page==null||page<1)
            page = 1;
        if(pageSize==null||pageSize<1)
            pageSize = 10;
        if(type ==null){
            type = 0;
        }
        if(num == null){
            num = "";
        }
        num = "%"+num+"%";

        GenericModel.JPAQuery query;
        long count;
        if(type !=0){
            query = AirlineStatus.find("Status >= ? and Status < ? and Plan.Number like ?",type*100,
                    (type+1)*100,num);
            count = AirlineStatus.count("Status >= ? and Status < ? and Plan.Number like ?",type*100,
                    (type+1)*100,num);
        } else {
            query = AirlineStatus.find("Plan.Number like ?",num);
            count = AirlineStatus.count("Plan.Number like ?",num);
        }

        List<AirlineStatus> data = query.fetch(page, pageSize);
        Page<AirlineStatus> pages = new Page<AirlineStatus>(data,
                page,
                pageSize,
                count);
        render(pages);
    }
}
