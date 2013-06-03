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
        Logger.debug("Check Profile %s for user %s",profile,Security.connected());
        User user = User.find("LoginName = ?", Security.connected()).first();

        String[] checkParams = profile.split(",");
        boolean retv = true;
        for(String param: checkParams){
            if(param.isEmpty()) continue;
            String[] strs = param.split("\\+");
            assert (strs.length==2);
            Role.PrivilegeType pt = null;
            if(strs[0].equals("AirCompany")){
                pt = user.UserRole.getAirCompanyTablePrivilege();
            } else if(strs[0].equals("AirlinePlan")){
                pt = user.UserRole.getAirlinePlanTablePrivilege();
            } else if(strs[0].equals("Airport")){
                pt = user.UserRole.getAirportTablePrivilege();
            } else if(strs[0].equals("AirportResource")){
                pt = user.UserRole.getAirportResourceTablePrivilege();
            } else if(strs[0].equals("Role")){
                pt = user.UserRole.getRoleTablePrivilege();
            } else if(strs[0].equals("PropertyResource")){
                pt = user.UserRole.getPropertyResourceTablePrivilege();
            } else if(strs[0].equals("User")){
                pt = user.UserRole.getUserTablePrivilege();
            } else if(strs[0].equals("AirlineStatus")){
                pt = user.UserRole.getAirlineStatusTablePrivilege();
            }

            if(strs[1].toUpperCase().contains("R")){
                retv =retv && pt !=null && pt.Readable;
            }
            if(strs[1].toUpperCase().contains("W")){
                retv =retv && pt!=null && pt.Writable;
            }
        }
        return retv;
    }
}
