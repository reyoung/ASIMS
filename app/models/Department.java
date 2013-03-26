package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Max;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Department extends Model {
	@Column(name = "Name", nullable = false, length = 255)
	public String Name;

}
