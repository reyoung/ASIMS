package controllers;

import models.Airport;
import play.mvc.Controller;

import java.util.List;

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

}
