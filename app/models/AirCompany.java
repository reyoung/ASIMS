package models;

import play.db.jpa.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/18/13
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AirCompany extends Model {
    @Column(name = "Name", nullable = false, unique = true, length = 255)
    public String Name;
}
