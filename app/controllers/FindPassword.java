package controllers;

import models.User;
import notifiers.Mails;
import play.data.validation.Required;
import play.data.validation.Validation;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-6-3
 * Time: 下午8:42
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class FindPassword extends BaseNormalController {
    public static void index(){
        render();
    }

    public static void resetPassword(@Required String email){
        if(Validation.hasErrors())
            badRequest();
        User u = User.find("Email = ?",email).first();
        if(u!=null){
            Mails.lostPassword(u);
        }
        render(u);
    }
}
