package controllers;

import models.AirlinePlan;
import models.Airport;
import play.mvc.Controller;

import java.sql.Timestamp;
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

    public static void debug(){
        List<Airport> aps = Airport.all().fetch(2);
        AirlinePlan alp  = new AirlinePlan();
        alp.FlyTime = 60;
        alp.Repeat="N";
        alp.LeaveTime = new Timestamp(2012,12,21,10,10,10,10);
        alp.LeavePlace = aps.get(0);
        alp.ArrivePlace = aps.get(1);
        alp.Number = "CZ300212";
        alp.save();
    }
}
