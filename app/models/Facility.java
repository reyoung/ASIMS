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
    public static int AirportResourceType = 100;
    public static int AirportBroadPort = 101;
    public static int PropertyResourceType = 200;
//    public static String [] AirportResourceTypes = {"值机柜台","登机门","行李转盘","特种车"};

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


    public static Facility getRandBoardPort(){

        long ct = Facility.count("Type = ?",AirportBroadPort);
        if(ct==0){
            return null;
        }
        int page = (int) (Math.random()*ct);

        return (Facility) Facility.find("Type = ?",AirportBroadPort).fetch(page,1).get(0);
    }
}
