package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-28
 * Time: 下午1:55
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
@Entity(name = "News")
public class News extends Model {
    public static final int NT_AirportDescription=0;
    public static final int NT_AirlineInformation=1;
    public static final int NT_AirportResource=2;
    public static final int NT_PropertyResource=3;


    @Column(name = "Title",nullable = false, length = 255)
    @Required
    public String Title;

    @Column(name = "Type", nullable = false)
    @Required
    public int Type;

    @Column(name = "Content",nullable = false,columnDefinition = "text")
    @Required
    public String Content;

    @JoinColumn(name = "AuthorId",nullable = false)
    @Required
    @OneToOne
    public User Author;


    @Column(name = "Date", nullable = false, updatable = false)
    @Required
    public Date CreateDate;


    public String getTypeDescription(){
        switch (this.Type){
            case NT_AirlineInformation:
                return "航班信息";
            case NT_AirportDescription:
                return "机场描述";
            case NT_AirportResource:
                return "机场设施";
            case NT_PropertyResource:
                return "物业设施";
            default:
                return "";
        }
    }

    public List<Attachment> getAttachments(){
        return Attachment.find("BelongNews",this).fetch();
    }

    @PreRemove
    public void removeAllAttachments(){
        List<Attachment> attachments = getAttachments();
        for(Attachment a: attachments){
            a.delete();
        }
    }

    @PrePersist
    public void beforePersistent(){
        if(CreateDate==null){
            CreateDate = new Date();
        }
    }
}
