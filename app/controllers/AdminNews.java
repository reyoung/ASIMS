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

        List<Long> array = getAttachmentIDS(files_id);

        n.Author = (User) renderArgs.get("user");

        n.save();
        UpdateAttachmentsNews(n, array);
        list(null,null);
    }

    private static void UpdateAttachmentsNews(News n, List<Long> array) {
        for (Long id: array){
            Attachment att = Attachment.findById(id);
            if(att!=null){
                att.BelongNews = n;
                att.save();
            }
        }
    }

    private static List<Long> getAttachmentIDS(String files_id) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Long>>(){}.getType();

        return gson.fromJson(files_id, listType);
    }

    public static void edit(@Required Long id){
        if(Validation.hasErrors()){
            badRequest();
        }
        News model = News.findById(id);
        if(model==null){
            badRequest();
        }
        render("AdminNews/create.html", model);
    }

    public static void handleEdit(@Required Long id, @Required News n, @Required String files_id){
        if(Validation.hasErrors()){
            badRequest();
        }

        News news = News.findById(id);
        if(news==null){
            badRequest();
        }
        assert news != null;
        news.Title  = n.Title;
        news.Author =  (User) renderArgs.get("user");
        news.Content = n.Content;
        news.Type = n.Type;
        news.save();
        UpdateAttachmentsNews(news, getAttachmentIDS(files_id));
        list(null,null);
    }
}
