package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

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
    public static int NT_AirportDescription=0;
    public static int NT_AirlineInformation=1;
    public static int NT_AirportResource=2;
    public static int NT_PropertyResource=3;


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


    @Column(name = "Date", nullable = false)
    @Required
    public Date CreateDate;

    @PrePersist
    public void beforePersistent(){
        if(CreateDate==null){
            CreateDate = new Date();
        }
    }
}
