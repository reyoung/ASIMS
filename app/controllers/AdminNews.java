package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Attachment;
import models.News;
import models.Page;
import models.User;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-28
 * Time: 下午2:07
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class AdminNews extends BaseAdminController {
    static public void list(Integer page, Integer pageSize){
        if(page==null||page<1){
            page = 1;
        }
        if(pageSize ==null||pageSize<1)
            pageSize = 10;
        List<News> news = News.all().fetch(page,pageSize);
        Page<News> pages = new Page<News>(
                news,
                page,
                pageSize,
                News.count()
        );
        render(pages);
    }


    static public void create(){
        render();
    }

    static public void handleCreate(@Required News n, @Required String files_id){
        if(Validation.hasErrors()){
            badRequest();
        }

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Long>>(){}.getType();

        List<Long> array = gson.fromJson(files_id, listType);

        n.Author = (User) renderArgs.get("user");

        n.save();
        for (Long id: array){
            Attachment att = Attachment.findById(id);
            if(att!=null){
                att.BelongNews = n;
                att.save();
            }
        }
        Logger.debug("Attachments Count %d",n.getAttachments().size());
        ok();
    }
}
