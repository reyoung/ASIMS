package controllers;

import models.AirCompany;
import models.Page;
import models.User;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;
import play.mvc.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/18/13
 * Time: 9:18 PM
 * To change this template use File | Settings | File Templates.
 */


public class AdminAirCompany extends BaseAdminController {
    @Check("AirCompany+W")
    static public void create(){
        render();
    }

    @Check("AirCompany+R")
    public static void list(Integer page,Integer pageSize){
        if(page==null||page<1){
            page = 1;
        }
        if(pageSize==null || pageSize<1){
            pageSize = 10;
        }
        List<AirCompany> companies = AirCompany.all().fetch(page,pageSize);
        Page<AirCompany> pages = new Page<AirCompany>(companies,page,pageSize,AirCompany.count());
        render(pages);
    }

    @Check("AirCompany+W")
    public static void edit(Long id) {
        try {
            AirCompany model = AirCompany.findById(id);
            renderArgs.put("model",model);
        } catch (Throwable ex){
            badRequest();
        }
        render("AdminAirCompany/create.html");
    }

    @Check("AirCompany+W")
    public static void delete(Long id){
        int rows = 0;
        try {
            rows = AirCompany.delete("Id",id);
        } catch (Throwable ex){
            renderJSON(false);
        }
        renderJSON(rows);
    }


    @Check("AirCompany+R")
    public static void getByName(@Required String Name){
        if (Validation.hasErrors()){
            badRequest();
        }
        AirCompany com = AirCompany.find("byName",Name).first();
        if(com!=null){
            renderJSON(com);
        }else {
            renderJSON(false);
        }
    }

    @Check("AirCompany+R")
    public static void getById(@Required Long id){
        if(Validation.hasErrors()){
            badRequest();
        }
        AirCompany com = AirCompany.findById(id);
        if(com!=null){
            renderJSON(com);
        } else {
            renderJSON(false);
        }
    }

    @Check("AirCompany+W")
    public static void handleEdit(@Required Long id,@Required String Name){
        if(Validation.hasErrors()){
            badRequest();
        }
        try {
            AirCompany com = AirCompany.findById(id);
            com.Name = Name;
            com.save();
        } catch (Throwable ex){
            badRequest();
        }
        list(null,null);
    }
    @Check("AirCompany+W")
    public static void handleCreate(@Required String Name){
        if(Validation.hasErrors()){
            badRequest();
        }
        AirCompany com = new AirCompany();
        com.Name = Name;
        try{
            boolean ok = com.validateAndCreate();
            if(!ok){
                badRequest();
            }
        } catch (Throwable ex){
            badRequest();
        }
        list(null,null);
    }
}
