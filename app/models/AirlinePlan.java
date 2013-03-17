package models;

import javax.persistence.*;

import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

import java.sql.Timestamp;
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
    public Timestamp LeaveTime;

    @Required
    @Column(name = "FlyTime",nullable = false)
    public int FlyTime;

    @Column(name = "Repeat",nullable = false)
    @Required
    @Max(value = 255)
    public String Repeat;

    @Required
    @OneToOne()
    @JoinColumn(name = "LeavePlace", nullable = false)
    public Airport LeavePlace;

    @Required
    @OneToOne()
    @JoinColumn(name = "ArrivePlace",nullable = false)
    public Airport ArrivePlace;

    @Required
    @OneToMany()
    public List<Airport> StopoverPlaces;
}
