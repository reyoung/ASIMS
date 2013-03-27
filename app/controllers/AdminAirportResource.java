package controllers;

import models.Facility;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

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
            list(null,null);
        }
    }
    public static void list(Integer page, Integer pageSize){
        render();
    }
}
