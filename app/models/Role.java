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
	@Column(name = "Privilege", nullable = false, length = 255)
	public String Privilege;
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPrivilege() {
		return Privilege;
	}
	public void setPrivilege(String privilege) {
		Privilege = privilege;
	}
	

}
