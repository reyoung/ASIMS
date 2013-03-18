package controllers;

import models.AirCompany;
import play.mvc.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/18/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminAirCompany extends Controller{
    static public void create(){
        render();
    }

    public static void list(Integer page,Integer pageSize){

    }

    public static void handleCreate(String Name){
        AirCompany com = new AirCompany();
        com.Name = Name;
        boolean ok = com.validateAndCreate();
        if (ok){
            list(null,null);
        } else {
            badRequest();
        }
    }
}
