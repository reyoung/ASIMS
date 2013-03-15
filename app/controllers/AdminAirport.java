package controllers;

import com.google.gson.GsonBuilder;
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
    public static void list(Integer page,Integer pageSize, String filter,String status) {
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
        render(pages,status);
    }

    public static void create(){
        String[] countries = Airport.getAllCountry();
        AdminAirport.renderArgs.put("countries",countries);
        String tableJson= new GsonBuilder().create().toJson(Airport.CountryCityTable);
        AdminAirport.renderArgs.put("tableJson",tableJson);
        Logger.debug("Table Json Is "+tableJson);
        render();
    }
    public static void handleCreate(@Required String Name,
                                    @Required Integer CountryId,
                                    @Required Integer CityId){
        Logger.debug("Name %s , CountryID %d, CityID %d",Name,CountryId,CityId);
        if(Name == null || CountryId==null || CityId==null||!Airport.isValidCountryCity(CountryId,CityId)){
            badRequest();
        }

        boolean OK = new Airport(Name,CountryId,CityId).validateAndCreate();
//        renderArgs.put("status","created");
        list(1,10,"%","created");
    }
}
