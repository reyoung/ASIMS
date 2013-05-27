package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Columns;
import play.Logger;
import play.data.validation.Email;
import play.data.validation.Phone;
import play.data.validation.Unique;
import play.db.jpa.Model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: reyoung
 * Date: 13-5-27
 * Time: 下午2:33
 * EMail: reyoung@126.com
 * Blog: www.reyoung.me
 */
@Entity(name = "User")
public class User extends Model {
    @Column(name = "LoginName", unique = true, nullable = false, length = 255)
    @Unique
    public String LoginName;

    @Column(name = "Password", nullable = false,length = 255)
    public String Password;

    @Column(name = "Email",nullable = false,unique = true,length = 255)
    @Unique
    @Email
    public String Email;

    @Column(name = "Telephone",nullable = true, length = 64)
    @Phone
    public String Telephone;

    @Column(name = "Mobile", nullable = true,length = 64)
    @Phone
    public String Mobile;

    @Column(name = "IsMail", nullable = false)
    public  boolean IsMale;

    @Column(name = "Department",nullable = true)
    public String Department;

    @Column(name = "UserNumber",nullable = false,unique = true)
    @Unique
    public String UserNumber;

    @JoinColumn(name = "RoleType", nullable = false)
    @OneToOne
    public Role UserRole;


    public static User Login(String uname, String pwd){
        Logger.debug("On Login With %s,%s",uname,pwd);
        try {
            MessageDigest  md5 = MessageDigest.getInstance("MD5");
            byte [] result = md5.digest(md5.digest(pwd.getBytes()));
            BigInteger bigInt = new BigInteger(1,result);
            String hash = bigInt.toString(16).toUpperCase();
            Logger.debug("After MD5 Password is %s",hash);
            return User.find("LoginName = ? and Password = ?",uname,hash).first();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
