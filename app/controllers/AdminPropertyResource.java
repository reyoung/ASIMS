package controllers;

import models.Facility;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

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
        boolean  ok = fc.validateAndCreate();
        if (!ok){
            badRequest();
        } else {
            list(null,null,null);
        }
    }
}
