package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.Logger;
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
	public static int PROPERTYRESOURCE_TABLE_POS = 1;
	public static int ROLE_TABLE_POS = 2;
    public static int NEWS_TABLE_POS = 3;
	public static int ATTACHMENT_TABLE_POS = 4;
	public static int AIRLINEPLAN_TABLE_POS = 5;
	public static int AIRLINESTATUS_TABLE_POS = 6;
	public static int AIRPORTRESOURCE_TABLE_POS = 7;
	public static int AIRCOMPANY_TABLE_POS = 8;
	public static int AIRPORT_TABLE_POS = 9;

	
	public PrivilegeType getUserTablePrivilege(){
		return new PrivilegeType(Privilege, USER_TABLE_POS);
	}
	public PrivilegeType getPropertyResourceTablePrivilege(){
		return new PrivilegeType(Privilege, PROPERTYRESOURCE_TABLE_POS);
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
	public PrivilegeType getAirportResourceTablePrivilege(){
		return new PrivilegeType(Privilege, AIRPORTRESOURCE_TABLE_POS);
	}
	public PrivilegeType getAirCompanyTablePrivilege(){
		return new PrivilegeType(Privilege, AIRCOMPANY_TABLE_POS);
	}
	public PrivilegeType getAirportTablePrivilege(){
		return new PrivilegeType(Privilege, AIRPORT_TABLE_POS);
	}

    public String getReadablePrivilege(){
        Logger.debug("During getReadablePrivilege");
    	String strReadablePrivilege="";
    	if(getUserTablePrivilege().Writable){
    		strReadablePrivilege+="用户";
    	    strReadablePrivilege+=",";
    	}
    	if(getPropertyResourceTablePrivilege().Writable){
    		strReadablePrivilege+="物业设施";
    	    strReadablePrivilege+=",";
    	}
    	if(getRoleTablePrivilege().Writable){
    		strReadablePrivilege+="角色";
    	    strReadablePrivilege+=",";
    	}
    	if(getNewsTablePrivilege().Writable){
    		strReadablePrivilege+="新闻";
    	    strReadablePrivilege+=",";
    	}
    	if(getAttachmentTablePrivilege().Writable){
    		strReadablePrivilege+="附件";
            strReadablePrivilege+=",";
    	}
    	if(getAirlinePlanTablePrivilege().Writable){
    		strReadablePrivilege+="航班计划";
    	    strReadablePrivilege+=",";
    	}
    	if(getAirlineStatusTablePrivilege().Writable){
    		strReadablePrivilege+="航班状态";
    	    strReadablePrivilege+=",";
    	}
    	if( getAirportResourceTablePrivilege().Writable){
    		strReadablePrivilege+="机场设施";
    	    strReadablePrivilege+=",";
    	}
    	if(getAirCompanyTablePrivilege().Writable){
    		strReadablePrivilege+="航空公司";
    	    strReadablePrivilege+=",";
    	}
    	if(getAirportTablePrivilege().Writable){
    		strReadablePrivilege+="机场";
    	    strReadablePrivilege+=",";
    	}
    	if(strReadablePrivilege.length()!=0){
    		strReadablePrivilege=strReadablePrivilege.substring(0, strReadablePrivilege.length()-1);
    	}else{
    		strReadablePrivilege="你没有任何权限！";
    	}
        return strReadablePrivilege;
    }
}
