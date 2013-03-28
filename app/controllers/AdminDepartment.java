package controllers;

import java.util.List;

import models.Department;
import models.Page;
import models.Role;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

public class AdminDepartment extends Controller{
	public static void creat(){
		render();
	}
	public static void handleCreate(@Required String Name) {
		if(Validation.hasErrors()){
			badRequest();
		}
		Department department = new Department();
		department.Name = Name;
		try {
			if(!department.validateAndCreate()){
				badRequest();
			}
		} catch (Throwable ex) {
			// TODO: handle exception
			badRequest();
		}
		list(null,null);
	}
	public static void edit(Long id) {
		try {
			Department department = Department.findById(id);
			renderArgs.put("model", department);
		} catch (Throwable ex) {
			badRequest();
		}
		render("AdminDepartment/create.html");
	}
	public static void handleEdit(@Required Long id,@Required String Name) {
		if(Validation.hasErrors()){
			badRequest();
		}
		try {
			Department department = Department.findById(id);
			department.Name = Name;
			if(!department.validateAndSave()){
				badRequest();
			}
		} catch (Throwable ex) {
			// TODO: handle exception
			badRequest();
		}
		list(null,null);
		
	}
	 public static void delete(Long id){
	        int rows = 0;
	        try {
	            rows = Department.delete("Id",id);
	        } catch (Throwable ex){
	            renderJSON(false);
	        }
	        renderJSON(rows);
	    }

	    public static void getByName(@Required String Name){
	        if (Validation.hasErrors()){
	            badRequest();
	        }
	        Department com = Department.find("byName",Name).first();
	        if(com!=null){
	            renderJSON(com);
	        }else {
	            renderJSON(false);
	        }
	    }
	    public static void getById(@Required Long id){
	        if(Validation.hasErrors()){
	            badRequest();
	        }
	        Department department = Department.findById(id);
	        if(department!=null){
	            renderJSON(department);
	        } else {
	            renderJSON(false);
	        }
	    }
	public static void list(Integer page, Integer pageSize) {
		if(page == null || page < 1) {
			page = 1;
		}
		if(pageSize == null || pageSize < 1) {
			pageSize = 10;
		}
		List<Department> lstDepartment = Department.all().fetch(page, pageSize);
		Page<Department> pages = new Page<Department>(lstDepartment,page,pageSize,Department.count());
		render(pages);
	}
	

}
