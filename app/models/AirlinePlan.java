package models;

import javax.persistence.*;

import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/17/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AirlinePlan extends Model {
    @Column(name = "Number",nullable = false)
    @Required
    @Max(value = 255)
    public String Number;

    @Column(name = "LeaveTime",nullable = false)
    @Required
    public Date LeaveTime;

    @Required
    @Column(name = "FlyTime",nullable = false)
    public int FlyTime;

    @Column(name = "Repeat",nullable = false)
    @Required
    @Max(value = 255)
    public String Repeat;


    @OneToOne
    @JoinColumn(name = "Company",nullable = false)
    public AirCompany Company;

    @Required
    @OneToOne()
    @JoinColumn(name = "LeavePlace", nullable = false)
    public Airport LeavePlace;

    @Required
    @OneToOne()
    @JoinColumn(name = "ArrivePlace",nullable = false)
    public Airport ArrivePlace;

    @OneToMany()
    public List<Airport> StopoverPlaces;

    public String getReadableLeaveTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(this.LeaveTime);
    }
    public String getReadableFlyTime(){
        return String.valueOf(FlyTime);
    }
    public String getReadableStopovers(){
        if (StopoverPlaces.size()==0){
            return "无" ;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (Airport ap : StopoverPlaces){
                sb.append(ap.toString());
                sb.append(",");
            }
            sb.setCharAt(sb.length()-1,']');
            return sb.toString();
        }
    }
    public String getEditLeaveTime()  {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(this.LeaveTime);
    }
}
