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

    @ManyToMany//(targetEntity = AirlinePlan.class)
    @JoinTable(name = "FK_TBL_STOPOVERS",
        joinColumns = @JoinColumn(name = "AirlinePlanID"),
        inverseJoinColumns = @JoinColumn(name = "AirportID")
    )
    public List<Airport> StopoverPlaces;

    public List<AirlineStatus> getStatus(int max){
        return AirlineStatus.find("Plan.id = ? order by LeaveTime desc",this.id).fetch(max);
    }

    public String switchNumber(String cha){
    	if(cha.equals("1")){
    		cha="一";
    	}else if(cha.equals("2")){
    		cha="二";
    	}else if(cha.equals("2")){
    		cha="二";
    	}else if(cha.equals("3")){
    		cha="三";
    	}else if(cha.equals("4")){
    		cha="四";
    	}else if(cha.equals("5")){
    		cha="五";
    	}else if(cha.equals("6")){
    		cha="六";
    	}else if(cha.equals("7")){
    		cha="日";
    	}
		return cha;
    }

    public String getReadableLeaveTime() {
        String strLeaveTime="";
    	SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        if(this.Repeat.subSequence(0, 1).equals("N")){
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        	strLeaveTime=sdf.format(this.LeaveTime)+"起飞";
        }else if(this.Repeat.subSequence(0, 1).equals("W")){
        	if(this.Repeat.equals("W1234567")){
        		strLeaveTime="每天"+df.format(this.LeaveTime);
        	}else{
        		strLeaveTime="每周";
            	for(int i=1;i<Repeat.length();i++){
            		String cha=Repeat.substring(i,i+1);
            		strLeaveTime+=switchNumber(cha)+",";
            		}
                strLeaveTime=strLeaveTime.subSequence(0, strLeaveTime.length()-1)+df.format(this.LeaveTime);
        	}
        }else if(this.Repeat.subSequence(0, 1).equals("M")){
    		strLeaveTime="每月"+Repeat.subSequence(1, Repeat.length())+"号"+df.format(this.LeaveTime);
        }
        return strLeaveTime;

        
      
    }
    public String getReadableFlyTime(){
    	int day=FlyTime/1440;
    	int hour=(FlyTime/60)%24;
    	int minute=FlyTime%60;
    	String strFlyTime="";
    	if(day!=0){
    		strFlyTime+=day+"天";
    	}if(hour!=0){
    		strFlyTime+=hour+"小时";
    	}if(minute!=0){
    		strFlyTime+=minute+"分钟";
    	}
        return strFlyTime;
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

    public static List<Airport> getAirportByType(int type){
        switch(type){
            case 0:   //! China In
                return Airport.find("select distinct plan.LeavePlace from AirlinePlan plan " +
                        "where plan.ArrivePlace.id = ? and plan.LeavePlace.CountryId = 0"
                        ,Airport.getCurrentAirportID()).fetch();
            case 1:  //! China Out
                return Airport.find("select distinct plan.ArrivePlace from AirlinePlan plan " +
                    "where plan.LeavePlace.id = ? and plan.ArrivePlace.CountryId = 0"
                    ,Airport.getCurrentAirportID()).fetch();
            case 2: //! Inter In
                return Airport.find("select distinct plan.LeavePlace from AirlinePlan plan " +
                        "where plan.ArrivePlace.id = ? and plan.LeavePlace.CountryId <> 0"
                        ,Airport.getCurrentAirportID()).fetch();
            case 3:
                return Airport.find("select distinct plan.ArrivePlace from AirlinePlan plan " +
                        "where plan.LeavePlace.id = ? and plan.ArrivePlace.CountryId <> 0"
                        ,Airport.getCurrentAirportID()).fetch();
            default:
                return null;
        }
    }
}
