package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Max;
import play.data.validation.Required;

@Entity
public class Department {
	@Column(name = "Name", nullable = false, length = 255)
	public String Name;

}
