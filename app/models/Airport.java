package models;

import play.data.validation.MaxSize;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Column;
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
    @Column(name="Name",nullable = false)
    public String Name;

    @Required
    @Column(name="CityId",nullable = false)
    public int CityId;

    @Required
    @Column(name="CountryId",nullable = false)
    public int CountryId;

    static public String[][] CountryCityTable = {
            {"中国","天津","上海"},
            {"美国","纽约","新奥尔良","休斯顿","华盛顿"}
    };

    public String getCity(){
        return CountryCityTable[this.CountryId][this.CityId+1];
    }

    static public boolean isValidCountryCity(int CountryId, int CityId){
        return (CountryId<CountryCityTable.length)&&(CountryId>=0)
                &&(CityId>=0)&& (CityId+1 < CountryCityTable[CountryId].length);
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

    public Airport(String name, int countryId,int cityId) {
        Name = name;
        CityId = cityId;
        CountryId = countryId;
    }

    @Override
    public String toString(){
        return String.format("%s %s %s机场",this.getCountry(),this.getCity(),this.Name);
    }

    public String simpleToString(){
        return String.format("%s %s",this.getCountry(),this.getCity());
    }
}
