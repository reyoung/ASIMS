package models;

import org.hibernate.event.PreDeleteEvent;
import play.Logger;
import play.db.jpa.Model;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-28
 * Time: 下午3:55
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
@Entity(name = "Attachment")
public class Attachment extends Model {

    @ManyToOne
    @JoinColumn(name = "NewsId", nullable = true)
    public News BelongNews;

    @Column(name = "Path",nullable = false,unique = true, updatable = false, length = 255)
    public String Path;

    @Column(name = "Title",nullable = false,length = 255)
    public String Title;

    public Attachment(){}

    @PreRemove
    public void preRemove(){
        File file = new File("upload/"+this.Path);
        Logger.debug("Remove File %s",file.getPath());
        boolean ok =file.delete();
        Logger.debug("OK? "+ok);
    }

    public Attachment(File f){
        this.Title = f.getName();
        this.Path = String.format("%d_%d_%s",f.length(), System.currentTimeMillis(),f.getName());
        File uploadPath = new File("upload/");
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        File newFile = new File("upload/"+this.Path);
        boolean ok =  f.renameTo(newFile);
        if(!ok){
            Logger.debug("WTF In Save File");
        }
        this.save();
    }
}
