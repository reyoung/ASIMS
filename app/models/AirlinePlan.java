package models;

import javax.persistence.*;

import play.Logger;
import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

    public boolean IsStatusContained(Date leavetime){
        AirlineStatus status = AirlineStatus.find("Plan = ? order by LeaveTime desc",this).first();
        return status != null && status.LeaveTime.getDate() == leavetime.getDate() && status.LeaveTime.getMonth() == leavetime.getMonth() && status.LeaveTime.getYear() == leavetime.getYear();
    }

    public void toStatus(){
        Date neastDate = null;
        switch(Repeat.charAt(0)){
            case 'N':
                neastDate = this.LeaveTime;
                break;
            case 'W':
            {
                Date dt = getDateByWeekAndRepeat();
                neastDate = dt;
                break;
            }
            case 'M':{
                Date dt = getDateByMonth();
                neastDate = dt;
                break;
            }
            default:
                break;
        }
        if(neastDate!=null){

            if(!this.IsStatusContained(neastDate)){
                AirlineStatus st = new AirlineStatus();
                st.Plan = this;
                st.LeaveTime = neastDate;
                st.FlyTime = this.FlyTime;
                st.BoardPort = Facility.getRandBoardPort();
                st.save();
            }
        }
    }

    private Date getDateByMonth() {
        Date dt = (Date) this.LeaveTime.clone();
        Date cur = new Date();
        int curDate = cur.getDate();
        int d = Integer.parseInt(Repeat.substring(1, Repeat.length()),10);
        dt.setDate(d);
        if(d < curDate){
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.MONTH,1);
            dt = c.getTime();
        }
        return dt;
    }

    private Date getDateByWeekAndRepeat() {
        List<Integer> weekdays = new ArrayList<Integer>();
        for (int i=1;i<Repeat.length();++i){
            int wod = Repeat.charAt(i)-'0';
            if(wod ==7){
                wod = 1;
            } else {
                wod +=1;
            }
            weekdays.add(wod);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        int pass_day = 0;
        for(pass_day=0;pass_day<7;++pass_day){
            if(weekdays.contains(day)){
                break;
            }
        }
        cal.add(Calendar.DATE,pass_day);
        Date dt = cal.getTime();
        dt.setHours(this.LeaveTime.getHours());
        dt.setMinutes(this.LeaveTime.getMinutes());
        dt.setSeconds(this.LeaveTime.getSeconds());
        return dt;
    }

}
