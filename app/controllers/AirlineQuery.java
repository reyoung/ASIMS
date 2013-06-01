package controllers;

import models.AirlinePlan;
import models.Airport;
import models.Page;
import play.Logger;
import play.Play;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-6-1
 * Time: 下午8:52
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AirlineQuery extends BaseNormalController {



    public static void list(Integer page,
                     Integer pageSize,
                     Integer type,
                     Integer airport_id,
                     String airlineNumber,
                     String airCompanyName){
        if(page==null||page<1)
            page = 1;
        if(pageSize==null||pageSize<1)
            pageSize = 5;

        if(airlineNumber==null){
            airlineNumber="";
        }
        if(airport_id!=null&&airport_id==0){
            airport_id = null;
        }

        airlineNumber = "%"+airlineNumber+"%";
        if(airCompanyName == null){
            airCompanyName = "";
        }
        airCompanyName = "%"+airCompanyName+"%";

        long count = 0;
        List<AirlinePlan> data = null;
        long current_airport = Long.parseLong(Play.configuration.getProperty("current_airport", "1"), 10);
        if(type==0){
            if(airport_id==null){
                data = AirlinePlan.find("ArrivePlace.id = ? and Company.Name like ? and Number like ? and LeavePlace.CountryId = 0"
                    ,current_airport,airCompanyName,airlineNumber).fetch(page,pageSize);
                count = AirlinePlan.count("ArrivePlace.id = ? and Company.Name like ? and Number like ? and LeavePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber);
            }
            else{
                data = AirlinePlan.find("ArrivePlace.id = ? and Company.Name like ? and Number like ? " +
                        "LeavePlace.id = ? and LeavePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id).fetch(page,pageSize);
                count = AirlinePlan.count("ArrivePlace.id = ? and Company.Name like ? and Number like ? " +
                        "LeavePlace.id = ? and LeavePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id);
            }
        } else if(type==1){
            if(airport_id==null){
                data = AirlinePlan.find("LeavePlace.id = ? and Company.Name like ? and Number like ? and ArrivePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber).fetch(page,pageSize);
                count = AirlinePlan.count("LeavePlace.id = ? and Company.Name like ? and Number like ? and ArrivePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber);
            } else {
                data = AirlinePlan.find("LeavePlace.id = ? and Company.Name like ? and Number like ? " +
                        "ArrivePlace.id = ? and ArrivePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id).fetch(page,pageSize);
                count = AirlinePlan.count("LeavePlace.id = ? and Company.Name like ? and Number like ? " +
                        "ArrivePlace.id = ? and ArrivePlace.CountryId = 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id);
            }
        } else if(type==2){
            if(airport_id==null){
                data = AirlinePlan.find("ArrivePlace.id = ? and Company.Name like ? and Number like ? and LeavePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber).fetch(page,pageSize);
                count = AirlinePlan.count("ArrivePlace.id = ? and Company.Name like ? and Number like ? and LeavePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber);
            }
            else{
                data = AirlinePlan.find("ArrivePlace.id = ? and Company.Name like ? and Number like ? " +
                        "LeavePlace.id = ? and LeavePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id).fetch(page,pageSize);
                count = AirlinePlan.count("ArrivePlace.id = ? and Company.Name like ? and Number like ? " +
                        "LeavePlace.id = ? and LeavePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id);
            }
        } else {
            if(airport_id==null){
                data = AirlinePlan.find("LeavePlace.id = ? and Company.Name like ? and Number like ? and ArrivePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber).fetch(page,pageSize);
                count = AirlinePlan.count("LeavePlace.id = ? and Company.Name like ? and Number like ? and ArrivePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber);
            } else {
                data = AirlinePlan.find("LeavePlace.id = ? and Company.Name like ? and Number like ? " +
                        "ArrivePlace.id = ? and ArrivePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id).fetch(page,pageSize);
                count = AirlinePlan.count("LeavePlace.id = ? and Company.Name like ? and Number like ? " +
                        "ArrivePlace.id = ? and ArrivePlace.CountryId <> 0"
                        ,current_airport,airCompanyName,airlineNumber,airport_id);
            }
        }
        Page<AirlinePlan> pages = new Page<AirlinePlan>(data,page,pageSize,count);
        Logger.debug("Count: %d",count);
        List<Airport> ports = AirlinePlan.getAirportByType(type);
        render(pages,type,ports);
    }
}
