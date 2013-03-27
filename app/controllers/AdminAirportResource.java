package controllers;

import models.Facility;
import models.Page;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-3-26
 * Time: 下午8:51
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminAirportResource extends Controller {
    public static void create(){
        render();
    }
    public static void edit(@Required Long id){
        if (Validation.hasErrors()) badRequest();
        Facility model = Facility.findById(id);
        render("AdminAirportResource/create.html",model);
    }

    public static void delete(@Required Long id){
        if (Validation.hasErrors()){
            renderJSON(false);
        }
        int row = 0;
        try {
            row = Facility.delete("Id",id);
        } catch (Throwable e){
            renderJSON(false);
        }
        renderJSON(row);
    }
    public static void handleEdit(@Required Long id,
                                  @Required String Name,
                                  @Required String Position,
                                  @Required Integer Amount,
                                  String Comment){
        if (Validation.hasErrors()) badRequest();
        Facility fc = Facility.findById(id);
        fc.Name = Name;
        fc.Position = Position;
        fc.Amount = Amount;
        fc.save();
        list(null,null,null);
    }
    public static void handleCreate(@Required String Name,
                                    @Required String Position,
                                    @Required Integer Amount, String Comment){
        if (Validation.hasErrors()){
            badRequest();
        }
        Facility fc = new Facility();
        fc.Name = Name;
        fc.Position = Position;
        fc.Amount = Amount;
        fc.Comment = Comment;
        fc.Type = Facility.AirportResourceType;
        boolean ok = fc.validateAndCreate();
        if (!ok ){
            badRequest();
        } else {
            list(null,null,null);
        }
    }
    public static void list(Integer page, Integer pageSize, String filter){
        if (page == null || page <1){
            page = 1;
        }
        if (pageSize == null || pageSize<1){
            pageSize = 10;
        }
        if (filter == null){
            filter = "";
        }
        filter = "%"+filter+"%";

        List<Facility> model = Facility.find("Type >= ? and Type < ? and Name like ?" ,Facility.AirportResourceType,
                Facility.PropertyResourceType,filter)
                .fetch(page,pageSize);
        Page<Facility> pages = new Page<Facility>(model,page,pageSize,
                Facility.count("Type >= ? and Type < ? and Name like ?",Facility.AirportResourceType,
                        Facility.PropertyResourceType,filter)
                );
        render(pages);
    }
}
