package com.dempe.analysis.manger.system.model;

import com.alibaba.fastjson.JSONObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.NotSaved;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
@Entity(value = "user", noClassnameStored = true)
public class User {

    @Id
    private String uid = new ObjectId().toString();

    private String name;

    private String email;

    private String pwd;

    private int roleId;

    @NotSaved
    private String roleName;

    private String profile;

    private long createAt;

    public static enum RoleType {
        ROLE_USER(1),
        ROLE_ADMIN(2),
        ROLE_MANGER(3);
        int role;

        private RoleType(int role) {
            this.role = role;
        }

        public int value() {
            return role;
        }
    }

    public String toJSONString() {
        return JSONObject.toJSONString(this);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", profile='" + profile + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
