package com.statestr.core.entity;


import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by ruantianbo on 2017/3/5.
 */
@Entity
@DiscriminatorValue("common")
public class UserEntity extends AdminUserEntity {


    @Column(name="weichat")
    private String weiChat;

    @Column(name="gender")
    @Type(type="yes_no")
    private Boolean isMale; //Y for male while N for female

    @Column(name="location")
    private String location;


    public UserEntity() {
    }

    public String getWeiChat() {
        return weiChat;
    }

    public void setWeiChat(String weiChat) {
        this.weiChat = weiChat;
    }

    public Boolean getMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
