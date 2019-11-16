package entity;

import java.io.Serializable;


 /**
 Represents a movie goer
 @author SS3_Group4
 @version 1.0
 @since 2019-11-15
*/

public class MovieGoer implements Serializable{
    /** 
    * The name of this movie goer
    */
    private String name;
    /** 
    * The mobile number of this movie goer
    */
    private String mobileNumber;
    /** 
    * The email of this movie goer
    */
    private String email;

    /** 
    * Gets the name of this movie goer
    * @return The name of this movie goer
    */
    public String getName() {
        return name;
    }

    /**
    * Change the name for this movie goer
    * @param name The new name for this movie goer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * Gets the mobile number of this movie goer
    * @return The mobile number of this movie goer
    */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
    * Change the mobile number for this movie goer
    * @param mobileNumber The new mobile number for this movie goer
    */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
    * Gets the email of this movie goer
    * @return The email of this movie goer 
    */
    public String getEmail() {
        return email;
    }

    /** 
    * Change the email for this movie goer
    * @param email The new email for this movie goer
    */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    * Creates a movie goer with the given name, mobile number and email
    * @param name The name of this movie goer
    * @param mobileNumber The mobile number of this movie goer
    * @param email The email of this movie goer
    */
    public MovieGoer(String name, String mobileNumber, String email) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }
}
