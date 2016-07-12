package com.androidbelieve.drawerwithswipetabs;

/**
 * Created by kgupta1 on 5/18/2016.
 */
public class User {

    int id;
    String firstName, lastName, email, password, usertype, phonenumber, address;

    public void setId(int id){
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setUserType(String usertype)
    {
        this.usertype = usertype;
    }
    public String getUsertype()
    {
        return this.usertype;
    }

    public void setPhonenumber(String phonenumber)
    {
        this.phonenumber = phonenumber;
    }
    public String getPhonenumber()
    {
        return this.phonenumber;
    }

    public void setAdress(String address)
    {
        this.address = address;
    }
    public String getAddress()
    {
        return this.address;
    }


}
