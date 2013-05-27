package controllers;

import models.*;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-27
 * Time: 下午2:53
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class Security extends  Secure.Security {
    static boolean authenticate(String username, String password) {
        return User.Login(username,password)!=null;
    }
    static boolean check(String profile) {
        Logger.debug("Check Profile %s",profile);
        User user = User.find("LoginName = ?", Security.connected()).first();
        if("AirCompany+W".equals(profile)) {
            return user.UserRole.getAirCompanyTablePrivilege().Writable;
        } else if ("AirCompany+R".equals(profile)){
            return user.UserRole.getAirCompanyTablePrivilege().Readable;
        } else if ("AirCompany+RW".equals(profile)){
            return user.UserRole.getAirCompanyTablePrivilege().Readable&&
                    user.UserRole.getAirCompanyTablePrivilege().Writable;
        }
        return false;
    }
}
