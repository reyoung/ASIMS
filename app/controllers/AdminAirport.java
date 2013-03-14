package controllers;

import models.Airport;
import models.Page;
import play.Logger;
import play.data.validation.Required;
import play.mvc.Controller;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/14/13
 * Time: 11:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdminAirport extends Controller {
    public static void list(Integer page,Integer pageSize, String filter) {
        if (page == null){
            page = 1;
        }
        if (pageSize==null){
            pageSize = 10;
        }
        if (filter == null){
            filter = "%";
        }
        List<Airport> airports= Airport.find("byNameLike",filter).fetch(page,pageSize);
        long all = Airport.count();
        Page<Airport> pages = new Page<Airport>(airports,page,pageSize,all);
        render(pages);
    }
}
