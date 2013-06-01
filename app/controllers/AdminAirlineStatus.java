package controllers;

import models.AirlineStatus;
import models.Page;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;

import java.lang.Integer;
import java.lang.Long;
import java.util.List;
import java.util.Date;
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

    public static final int UPDATE_ACTION_FLY = 0;
    public static final int UPDATE_ACTION_ARRIVED = 1;
    public static final int UPDATE_ACTION_CANCEL = 2;

    public static void updateByID (@Required Long id,@Required Integer action){
        if(Validation.hasErrors())badRequest();
        AirlineStatus st = null;
        switch (action.intValue()){
            case UPDATE_ACTION_FLY:
                st = AirlineStatus.findById(id);
                if(st!=null && st.Status /100 == 1){
                    st.Status = AirlineStatus.AS_FLYING;
                    st.save();
                    renderJSON(true);
                }else {
                    renderJSON(false);
                }
            case UPDATE_ACTION_ARRIVED:
                st = AirlineStatus.findById(id);
                if(st!=null && st.Status == AirlineStatus.AS_FLYING){
                    st.Status = AirlineStatus.AS_DONE;
                    Date current = new Date();
                    long ms = current.getTime() - st.LeaveTime.getTime();
                    st.FlyTime = (int)(ms/1000);
                    st.save();
                    renderJSON(true);
                } else {
                    renderJSON(false);
                }
            case UPDATE_ACTION_CANCEL:
                st = AirlineStatus.findById(id);
                if(st!=null&&st.Status/100 ==1){
                    st.Status = AirlineStatus.AS_CANCEL;
                    st.save();
                    renderJSON(true);
                } else {
                    renderJSON(false);
                }
            default:
                renderJSON(false);
        }
    }

}
