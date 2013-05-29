package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-29
 * Time: 下午2:40
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
@Entity(name = "AirlineStatus")
public class AirlineStatus  extends Model {

    public final static int AS_PLANING = 100;
    public final static int AS_PLAN_ON_TIME = AS_PLANING+1;
    public final static int AS_PLAN_DELAY = AS_PLANING+2;
    public final static int AS_DONE = 200;
    public final static int AS_DONE_ON_TIME = AS_DONE+1;
    public final static int AS_DONE_DELAY= AS_DONE+2;
    public final static int AS_CANCEL = 300;


    @ManyToOne
    @JoinColumn(nullable = false)
    public AirlinePlan Plan;

    @Column(nullable = false,name = "LeaveTime")
    public Date LeaveTime;

    @Column(name = "FlyTime",nullable = false)
    public int FlyTime=0;

    @JoinColumn(nullable = false)
    @ManyToOne
    public Facility BoardPort;

    @Column(nullable = false)
    public int Status = AS_PLAN_ON_TIME;
}
