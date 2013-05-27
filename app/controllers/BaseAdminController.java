package controllers;

import models.User;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-27
 * Time: 下午3:25
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
@With(Secure.class)
public class BaseAdminController extends Controller {
    @Before
    public static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("LoginName = ?", Security.connected()).first();
            renderArgs.put("user", user);
        }
    }
}
