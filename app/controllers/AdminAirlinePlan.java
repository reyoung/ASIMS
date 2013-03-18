package controllers;

import models.AirlinePlan;
import models.Airport;
import models.Page;
import play.mvc.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
//import fj.data.List;
/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/17/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminAirlinePlan  extends Controller{
    public static void create(){
        List<Airport> airports=Airport.findAll();
        render(airports);
    }

    public static void handleCreate(String Number,String LeaveTime,Integer FlyTime,
                                    Long LeavePlace,Long ArrivePlace,String Stopovers,
                                    String Repeat
                                    ) {
        badRequest();
    }
    public static void debug(){
        List<Airport> aps = Airport.all().fetch(2);
        AirlinePlan alp  = new AirlinePlan();
        alp.FlyTime = 60;
        alp.Repeat="N";

        alp.LeaveTime = new Timestamp(new Date().getTime());
        alp.LeavePlace = aps.get(0);
        alp.ArrivePlace = aps.get(1);
        alp.Number = "CZ300212";
        alp.save();
    }


    public static void delete(Long id){
        /**
         * @todo
         */
        renderJSON(false);
    }

    public static void list(Integer page, Integer pageSize){
        if(page==null||page<1){
            page = 1;
        }
        if(pageSize==null||pageSize<1){
            pageSize = 10;
        }
        List<AirlinePlan> airlinePlans = AirlinePlan.all().fetch(page,pageSize);
        Page<AirlinePlan> pages = new Page<AirlinePlan>(airlinePlans,page,pageSize,AirlinePlan.count());
        render(pages);
    }

    public static void edit(Long id) {
        try {
            AirlinePlan alp = AirlinePlan.findById(id);
            renderArgs.put("model",alp);
            renderArgs.put("airports",Airport.findAll());
        } catch (Throwable e) {
            badRequest();
        }
        render("AdminAirlinePlan/create.html");
    }

    public static void handleEdit(String Number,String LeaveTime,Integer FlyTime,
                                    Long LeavePlace,Long ArrivePlace,String Stopovers,
                                    String Repeat,Long editId
    ) {
        badRequest();
    }
}
