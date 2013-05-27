package controllers;

import models.Page;
import models.Role;
import models.User;
import play.Logger;
import play.data.validation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-27
 * Time: 下午4:18
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminUser extends BaseAdminController {
    @Check("User+R")
    public static void list(Integer page, Integer pageSize){
        if(page == null || page <1){
            page = 1;
        }
        if(pageSize == null || pageSize<1){
            pageSize = 10;
        }
        List<User> users = User.all().fetch(page,pageSize);
        Page<User> pages = new Page<User>(users,page,pageSize,User.count());
        render(pages);
    }
    @Check("User+W,Role+R")
    public static void create(){
        List<Role> roles =
                Role.all().fetch();
        render(roles);
    }

    @Check("User+RW,Role+R")
    public static void edit(@Required Long id){
        List<Role> roles = Role.all().fetch();
        User model = User.findById(id);
        if(model==null)
            badRequest();
        else
            render("AdminUser/create.html",roles,model);
    }

    @Check("User+W")
    public  static void handleCreate(@Required User u,@Required Long RoleId){
        if(Validation.hasErrors()){
            Logger.debug("The Input Contains Errors");
            for (play.data.validation.Error e: Validation.errors()){
                Logger.debug(" %s",e.message());
            }
        }
        u.UserRole = Role.findById(RoleId);
        Logger.debug("Create User Name = %s, Password = %s",u.LoginName,u.Password);
        u.save();
        list(null,null);
    }
}
