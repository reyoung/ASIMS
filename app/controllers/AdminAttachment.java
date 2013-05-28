package controllers;

import models.Attachment;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-28
 * Time: 下午3:06
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminAttachment extends BaseAdminController{
    public static void upload(@Required File Filedata){
        if(Validation.hasErrors()){
            badRequest();
        }
        Attachment att = new Attachment(Filedata);
        renderJSON(att.id);
    }
    public static void delete(@Required Long id){
        if(Validation.hasErrors()){
            badRequest();
        }
        Attachment attch= Attachment.findById(id);
        if(attch!=null){
            attch.delete();
            renderJSON(1);
        }else
            renderJSON(false);
    }
}
