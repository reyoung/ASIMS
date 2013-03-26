package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Columns;
import play.db.jpa.Model;
/**
 * 
 * @author Li
 *
 */
/**
 * @author Administrator
 *
 */
@Entity
public class Role extends Model{
	@Column(name = "Name", nullable = false, length = 255)
	public String Name;
	@Column(name = "Privilege", nullable = false)
	public int Privilege;
	
	public class PrivilegeType {
		public boolean Readable;
		public boolean Writable;
		public PrivilegeType(int privilege, int pos){
			//* Set Readable and Writable
			privilege >>= pos * 2;
			privilege &= 3;
			this.Readable = (privilege & 1)!=0;
			this.Writable = (privilege & 2)!=0;
		}
	}
	
	
	public static int USER_TABLE_POS = 0;
	public static int DEPARTMENT_TABLE_POS = 1;
	public static int ROLE_TABLE_POS = 2;
    public static int NEWS_TABLE_POS = 3;
	public static int ATTACHMENT_TABLE_POS = 4;
	public static int AIRLINEPLAN_TABLE_POS = 5;
	public static int AIRLINESTATUS_TABLE_POS = 6;
	public static int FACILITY_TABLE_POS = 7;
	public static int AIRCOMPANY_TABLE_POS = 8;
	public static int AIRPORT_TABLE_POS = 9;

	
	public PrivilegeType getUserTablePrivilege(){
		return new PrivilegeType(Privilege, USER_TABLE_POS);
	}
	public PrivilegeType getDepartmentTablePrivilege(){
		return new PrivilegeType(Privilege, DEPARTMENT_TABLE_POS);
	}
	public PrivilegeType getRoleTablePrivilege(){
		return new PrivilegeType(Privilege, ROLE_TABLE_POS);
	}
	public PrivilegeType getNewsTablePrivilege(){
		return new PrivilegeType(Privilege, NEWS_TABLE_POS);
	}
	public PrivilegeType getAttachmentTablePrivilege(){
		return new PrivilegeType(Privilege, ATTACHMENT_TABLE_POS);
	}
	public PrivilegeType getAirlinePlanTablePrivilege(){
		return new PrivilegeType(Privilege, AIRLINEPLAN_TABLE_POS);
	}
	public PrivilegeType getAirlineStatusTablePrivilege(){
		return new PrivilegeType(Privilege, AIRLINESTATUS_TABLE_POS);
	}
	public PrivilegeType getFacilityTablePrivilege(){
		return new PrivilegeType(Privilege, FACILITY_TABLE_POS);
	}
	public PrivilegeType getAirCompanyTablePrivilege(){
		return new PrivilegeType(Privilege, AIRCOMPANY_TABLE_POS);
	}
	public PrivilegeType getAirportTablePrivilege(){
		return new PrivilegeType(Privilege, AIRPORT_TABLE_POS);
	}

    public String getReadablePrivilege(){
        /**
         * @todo Complete This Method
         */
    	String strReadablePrivilege="";
    	if(getUserTablePrivilege().Readable||getUserTablePrivilege().Writable){
    		strReadablePrivilege+="用户表";
    	    if(getUserTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getUserTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getDepartmentTablePrivilege().Readable||getDepartmentTablePrivilege().Writable){
    		strReadablePrivilege+="部门表";
    	    if(getDepartmentTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getDepartmentTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getRoleTablePrivilege().Readable||getRoleTablePrivilege().Writable){
    		strReadablePrivilege+="角色表";
    	    if(getRoleTablePrivilege().Readable){strReadablePrivilege+="可读";}
            if(getRoleTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getNewsTablePrivilege().Readable||getNewsTablePrivilege().Writable){
    		strReadablePrivilege+="新闻表";
    	    if(getNewsTablePrivilege().Readable){strReadablePrivilege+="可读";}
            if(getNewsTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getAttachmentTablePrivilege().Readable||getAttachmentTablePrivilege().Writable){
    		strReadablePrivilege+="附件表";
            if(getAttachmentTablePrivilege().Readable){strReadablePrivilege+="可读";}
            if(getAttachmentTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getAirlinePlanTablePrivilege().Readable||getAirlinePlanTablePrivilege().Writable){
    		strReadablePrivilege+="航班计划表";
    	    if(getAirlinePlanTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getAirlinePlanTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getAirlineStatusTablePrivilege().Readable||getAirlineStatusTablePrivilege().Writable){
    		strReadablePrivilege+="航班状态表";
    	    if(getAirlineStatusTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getAirlineStatusTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getFacilityTablePrivilege().Readable||getFacilityTablePrivilege().Writable){
    		strReadablePrivilege+="机场设施表";
    	    if(getFacilityTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getFacilityTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getAirCompanyTablePrivilege().Readable||getAirCompanyTablePrivilege().Writable){
    		strReadablePrivilege+="航空公司表";
    	    if(getAirCompanyTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getAirCompanyTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(getAirportTablePrivilege().Readable||getAirportTablePrivilege().Writable){
    		strReadablePrivilege+="机场表";
    	    if(getAirportTablePrivilege().Readable){strReadablePrivilege+="可读";}
    	    if(getAirportTablePrivilege().Writable){strReadablePrivilege+="可写";}
    	    strReadablePrivilege+=",";
    	}
    	if(strReadablePrivilege.length()!=0){
    		strReadablePrivilege+=strReadablePrivilege.substring(0, strReadablePrivilege.length()-1);
    	}else{
    		strReadablePrivilege="你没有任何权限！";
    	}
        return strReadablePrivilege;
    }
}
