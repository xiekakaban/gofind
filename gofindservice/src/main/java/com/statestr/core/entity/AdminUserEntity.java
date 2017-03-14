package com.statestr.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by ruantianbo on 2017/3/12.
 */
@Entity
@Table(name="tb_user")
@DiscriminatorColumn(name="role_type")
@DiscriminatorValue("admin")
public class AdminUserEntity extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "user_uuid")
    @GenericGenerator(name = "user_uuid", strategy = "uuid")
    private String id;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
