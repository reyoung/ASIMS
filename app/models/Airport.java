package models;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 3/14/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity(name = "Airport")
public class Airport extends Model {
    @MinSize(2)
    @Required
    @MaxSize(255)
    public String Name;

    @Required
    public int CityId;

    @Required
    public int CountryId;

    static public String[][] CountryCityTable = {
            {"中国","天津","上海"},
            {"美国","纽约","新奥尔良","休斯顿"}
    };

    public String getCity(){
        return CountryCityTable[this.CountryId][this.CityId+1];
    }

    public String getCountry(){
        return CountryCityTable[this.CountryId][0];
    }

    static public String[] getAllCountry(){
        String [] ret_val = new String[CountryCityTable.length];
        for (int i=0;i<CountryCityTable.length;++i){
            ret_val [i] = CountryCityTable[i][0];
        }
        return ret_val;
    }

    public Airport(String name, int cityId, int countryId) {
        Name = name;
        CityId = cityId;
        CountryId = countryId;
    }
}
