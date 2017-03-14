package com.statestr.core.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by ruantianbo on 2017/3/5.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable{

    @Column(name="last_modify_time")
    private Date lastModifyTime;
    @Column(name="create_time")
    private Date createTime;

    @Override
    public String toString() {
        Field[] fields = {};
        fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        Field field = null;
        for (int i = 0; i < fields.length; i++) {
            try {
                field = fields[i];
                field.setAccessible(true);
                sb.append("[").append(fields[i].getName()).append(":").append(fields[i].get(this)).append("]");
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
