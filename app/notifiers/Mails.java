package notifiers;

import models.User;
import play.mvc.Mailer;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-6-3
 * Time: 下午9:03
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class Mails extends Mailer {
    public static void lostPassword(User u){
        String tbl = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String new_password = "";
        for (int i=0;i<6;++i){
            int index = (int) (tbl.length()*Math.random());
            new_password += tbl.charAt(index);
        }
        addRecipient(u.Email);
        setFrom("ASIMS <I@reyoung.me>");
        u.Password = new_password;
        u.hashPassword();
        u.save();
        setSubject("[ASIMS]你的密码已经被重置");
        send(u,new_password);
    }
}
