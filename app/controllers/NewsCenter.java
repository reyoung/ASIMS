package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.News;
import models.Page;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.GenericModel;
import play.mvc.Router;
import viecili.jrss.generator.RSSFeedGenerator;
import viecili.jrss.generator.RSSFeedGeneratorFactory;
import viecili.jrss.generator.elem.Channel;
import viecili.jrss.generator.elem.RSS;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-30
 * Time: 下午4:21
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
public class NewsCenter extends BaseNormalController {
    public static void list(Integer page,
                Integer pageSize,
                @Required String type,
                String titleFilter,
                String authorFilter,
                String fromTimeFilter
                ) throws ParseException {
        if(page==null || page <1)
            page = 1;
        if(pageSize==null||pageSize<1)
            pageSize = 10;

        int typeCode=0;
        if(type.equals("airport_description")){
            typeCode = News.NT_AirportDescription;
        } else if(type.equals("airline_info")){
            typeCode = News.NT_AirlineInformation;
        } else if(type.equals("airport_resource")){
            typeCode = News.NT_AirportResource;
        } else if(type.equals("property_resource")){
            typeCode = News.NT_PropertyResource;
        } else {
            notFound();
        }
        if (titleFilter == null){
            titleFilter = "";
        }
        titleFilter = "%"+titleFilter+"%";
        if(authorFilter == null){
            authorFilter = "";
        }
        authorFilter = "%"+authorFilter+"%";
        if(fromTimeFilter==null){
            fromTimeFilter = "";
        }
        GenericModel.JPAQuery query = null;
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy/MM/dd");
        long count;
        if(fromTimeFilter.isEmpty()){
            query = News.find("Title like ? and (Author.RealName like ? or Author.LoginName like ?) and Type = ?",
                    titleFilter,authorFilter,authorFilter,typeCode);
            count = News.count("Title like ? and (Author.RealName like ? or Author.LoginName like ?) and Type = ?",
                    titleFilter,authorFilter,authorFilter,typeCode);
        } else {
            Date filter = sdf.parse(fromTimeFilter);
            query = News.find("Title like ? and (Author.RealName like ? or Author.LoginName like ?) and Type = ? and CreateDate >= ?",
                    titleFilter,authorFilter,authorFilter,typeCode,filter);
            count = News.count("Title like ? and (Author.RealName like ? or Author.LoginName like ?) and Type = ? and CreateDate >= ?",
                    titleFilter,authorFilter,authorFilter,typeCode,filter);
        }
        List<News> news = query.fetch(page,pageSize);
        News n = News.all().first();
        Logger.debug("News Type %d",n.Type);
        Page<News> pages = new Page<News>(news,page,pageSize,count);
        render(pages,type);
    }

    public static void show(@Required Long id, @Required String type){
        if(Validation.hasErrors()){
            badRequest();
        }
        News news = News.findById(id);
        if(news==null){
            badRequest();
        }
        render(news,type);
    }
    public static void rss(String filter) throws IOException {
        if(filter==null||filter.isEmpty()){
            filter = "[0,1,2,3]";
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Integer>>(){}.getType();
        List<Integer> filter_type = gson.fromJson(filter,listType);
        String querystr="";
        for (Integer l : filter_type) {
            if (!querystr.isEmpty()) {
                querystr += " or ";
            }
            querystr += "Type = ?";
        }

        GenericModel.JPAQuery query = News.find(querystr,filter_type.toArray());
        List<News> news = query.fetch(20);

        RSSFeedGenerator gen = RSSFeedGeneratorFactory.getDefault();
        RSS rss = new RSS();
        for(News n : news){
            Map<String,Object> args = new HashMap<String, Object>();
            args.put("id",n.id);
            args.put("type","rss");
            String url = Router.getFullUrl("NewsCenter.show",args);
            Channel ch = new Channel(n.Title, url,
                    n.Content.substring(0,n.Content.length()>100? 100:n.Content.length()));
            ch.setPubDate(n.CreateDate);
            rss.addChannel(ch);
        }
        String result = gen.generateAsString(rss);
        renderXml(result);
    }

    public static void subscribe(){
        render();
    }
}
