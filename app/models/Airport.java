package models;

import play.Play;
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

    static public Long getCurrentAirportID(){
        return Long.parseLong(Play.configuration.getProperty("current_airport", "1"), 10);
    }


    static public String[][] CountryCityTable = {
            {"中国","天津","上海","北京","沈阳","大连","哈尔滨","济南","青岛","南京","杭州","武汉","广州","深圳","香港","澳门","重庆","成都","西安"
             ,"石家庄","长春","呼和浩特","太原","郑州","合肥","无锡","苏州","宁波","福州","厦门","南昌","长沙","汕头","珠海","海口","三亚","南宁","贵阳","昆明","拉萨","兰州","西宁","银川","乌鲁木齐"
            },
            {"美国","纽约","新奥尔良","休斯顿","华盛顿","洛杉矶","芝加哥","费城","菲尼克斯",
            "圣安东尼奥","圣迭戈","达拉斯","圣荷西","底特律","杰克逊维尔","印第安纳波利斯",
                    "旧金山","哥伦布","奥斯汀","孟菲斯","福和市"},
            {"英国","伦敦","曼彻斯特","伯明翰","利兹","利物浦","设菲尔德","爱丁堡","格拉斯哥",
                    "纽卡斯尔","贝尔法斯特","南安普敦","布里斯托尔","阿伯丁","加的夫"},
            {"法国","施特拉斯堡","巴黎","里昂","尼斯","马赛","波尔多","图卢兹","巴斯蒂亚","贝阿里兹","布雷斯特","卡勒威","克莱蒙费朗","格勒诺布尔","里摩日","洛里昂","拉尼翁","梅兹南希","蒙彼利埃","米卢斯","南特","波（城）"
                    ,"佩皮尼昂","坎佩尔","雷恩","罗德兹","土伦","阿雅克修","阿内西","阿维尼瓮","施特拉斯堡"},
            {"德国","柏林", "法兰克福", "慕尼黑", "纽伦堡", "斯图加特", "莱比锡", "德雷斯顿", "不来梅", "科隆", "波恩", "汉诺威", "汉堡", "多特蒙德", "杜塞尔多夫", "杜伊斯堡", "比勒费尔德", "基尔", "埃森"},
            {"丹麦","哥本哈根"},
            {"捷克","布拉格"},
            {"斯洛伐克","布拉迪斯拉发"},
            {"匈牙利","布达佩斯"},
            {"奥地利","维也纳","萨尔茨堡","林茨"},
            {"瑞士","苏黎世","日内瓦","巴塞尔","伯尔尼"},
            {"白俄罗斯","明斯克"},
            {"俄罗斯","莫斯科","新西伯利亚","彼德堡"},
            {"意大利","罗马","米兰","都灵","佛罗伦萨","那不勒斯","热那亚","威尼斯","巴勒莫","卡塔尼亚","博洛尼亚","巴里"},
            {"西班牙","巴塞罗那","瓦伦西亚","马拉加","塞维莱","毕尔巴鄂","马德里","巴利亚多利德"},
            {"葡萄牙","里斯本","波尔图","圣港"},
            {"克罗地亚","克罗地亚"},
            {"日本","东京","千叶","爱知","兵库","大坂"}
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
