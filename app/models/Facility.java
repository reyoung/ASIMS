package models;

import org.hibernate.annotations.Columns;
import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/18/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Facility extends Model {
    @Column(name = "Name", nullable = false, length = 255)
    public String Name;


    @Column(name = "Position", nullable = true, length = 255)
    public String Position;

    @Column(name = "Amount", nullable = true)
    public Integer Amount;

    @Column(name = "Comment", nullable = true,length = 255)
    public String Comment;

    @Column(name = "Type" , nullable = false)
    public int Type;

    @Column(name = "Telephone",nullable = true)
    public String Telephone;
}
