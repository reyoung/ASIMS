package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-30
 * Time: 下午4:20
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class BaseNormalController extends Controller {
    @Before
    public static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("LoginName = ?", session.get("username")).first();
            renderArgs.put("user", user);
        }
    }
}
