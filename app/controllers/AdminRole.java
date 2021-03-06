package controllers;

import java.util.List;

import models.Page;
import models.Role;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

/**
 * User:Guosheng Date:2013-3-22 Time:下午07:01:07
 */

public class AdminRole extends BaseAdminController {
	
	//反解析create.html的uri
    @Check("Role+W")
	public static void create(){
		render();
	}
    @Check("Role+W")
	public static void handleCreate(@Required String Name,
			@Required int Privilege) {
		if(Validation.hasErrors()){
			badRequest();
		}
		Role role = new Role();
		role.Name = Name;
		role.Privilege = Privilege;
		try {
			if(!role.validateAndCreate()){
				badRequest();
			}
		} catch (Throwable ex) {
			// TODO: handle exception
			badRequest();
		}
		list(null,null);
		
	}
    @Check("Role+RW")
	public static void edit(Long id) {
		try {
			Role role = Role.findById(id);
			renderArgs.put("model", role);
		} catch (Throwable ex) {
			badRequest();
		}
		render("AdminRole/create.html");
	}
    @Check("Role+RW")
	public static void handleEdit(@Required Long id,@Required String Name,
			@Required int Privilege) {
		if(Validation.hasErrors()){
			badRequest();
		}
		try {
			Role role = Role.findById(id);
			role.Name = Name;
			role.Privilege = Privilege;
			if(!role.validateAndSave()){
				badRequest();
			}
		} catch (Throwable ex) {
			// TODO: handle exception
			badRequest();
		}
		list(null,null);
		
	}
    @Check("Role+W")
	public static void delete(@Required Long id) {
		if (Validation.hasErrors()){
            renderJSON(false);
        }
		int rows = 0;
		try {
			rows = Role.delete("Id", id);
		} catch (Throwable ex) {
			renderJSON(false);
		}
		renderJSON(rows);
	}
    @Check("Role+R")
	public static void list(Integer page, Integer pageSize) {
		if(page == null || page < 1) {
			page = 1;
		}
		if(pageSize == null || pageSize < 1) {
			pageSize = 10;
		}
		List<Role> lstRole = Role.all().fetch(page, pageSize);
		Page<Role> pages = new Page<Role>(lstRole,page,pageSize,Role.count());
		render(pages);
	}
}
