package controllers;

import models.News;
import models.Page;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import java.util.List;

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
                String fromTimeFilter,
                String toTimeFilter
                ){
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
        if(toTimeFilter == null){
            toTimeFilter = "";
        }
        GenericModel.JPAQuery query = null;
        long count;
        if(fromTimeFilter.isEmpty()||toTimeFilter.isEmpty()){
            query = News.find("Title like ? and Author.RealName like ? and Type = ?",
                    titleFilter,authorFilter,typeCode);
            count = News.count("Title like ? and Author.RealName like ? and Type = ?",
                    titleFilter,authorFilter,typeCode);
        } else {
            query = News.find("Title like ? and Author.RealName like ? and Type = ? and CreateDate Between ? and ?",
                    titleFilter,authorFilter,typeCode,fromTimeFilter,toTimeFilter);
            count = News.count("Title like ? and Author.RealName like ? and Type = ? and CreateDate Between ? and ?",
                    titleFilter,authorFilter,typeCode,fromTimeFilter,toTimeFilter);
        }
        List<News> news = query.fetch(page,pageSize);
        Page<News> pages = new Page<News>(news,page,pageSize,count);
        render(pages);
    }
}
