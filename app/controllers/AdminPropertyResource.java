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
 * Date: 13-3-27
 * Time: 下午3:41
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminPropertyResource extends Controller {
    public static void create(){
        render();
    }

    public static void list(Integer page, Integer pageSize, String filter){
        if(page==null || page <1){
            page = 1;
        }
        if(pageSize==null || pageSize<1){
            pageSize = 10;
        }
        if(filter == null) {
            filter = "";
        }
        filter = "%"+filter+"%";
        List<Facility> data = Facility.find("Type >=? and Name like ?",Facility.PropertyResourceType,filter)
                                .fetch(page, pageSize);
        Page<Facility> pages =
                new Page<Facility>(data,page,pageSize,
                Facility.count("Type >=? and Name like ?",Facility.PropertyResourceType,filter));
        Logger.debug(""+data.size());
        render(pages);
    }


    public static void handleCreate(@Required String Name,
                                    @Required String Position,
                                    @Required Integer Amount,
                                    @Required String Telephone,
                                    String Comment){
        if(Validation.hasErrors()) badRequest();
        Facility fc = new Facility();
        fc.Name = Name;
        fc.Position = Position;
        fc.Amount = Amount;
        fc.Telephone = Telephone;
        fc.Comment = Comment;
        fc.Type = Facility.PropertyResourceType;
        boolean  ok = fc.validateAndCreate();
        if (!ok){
            badRequest();
        } else {
            list(null,null,null);
        }
    }
}
