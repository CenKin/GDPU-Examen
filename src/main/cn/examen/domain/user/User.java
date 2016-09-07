package cn.examen.domain.user;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

public class User {
    private String userId;
    private String realname;
    private String username;
    private String password;
    private String phone;
    private Integer userType;
    private String collId;
    private Date loginTime;

    public User() {
        super();
    }

    public User(User user) {
        this.setUserId(user.getUserId());
        this.setRealname(user.getRealname());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setUserType(user.getUserType());
        this.setCollId(user.getCollId());
        this.setLoginTime(user.getLoginTime());
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        if(userId!=null){
            if(userId.trim().length() > 0)
                this.userId = userId;
            else
                this.userId = null;
        } else this.userId = null;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        if(realname!=null){
            if(realname.trim().length() > 0)
                this.realname = realname;
            else
                this.realname = null;
        } else this.realname = null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username!=null){
            if(username.trim().length() > 0)
                this.username = username;
            else
                this.username = null;
        } else this.username = null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password!=null){
            if(password.trim().length() > 0)
                this.password = password;
            else
                this.password = null;
        } else this.password = null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(phone!=null){
            if(phone.trim().length() > 0)
                this.phone = phone;
            else
                this.phone = null;
        } else this.phone = null;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        if(userType!=null){
            if(userType > 0 && userType < 4)
                this.userType = userType;
            else
                this.userType = null;
        } else this.userType = null;
    }

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        if(collId!=null){
            if(collId.trim().length() > 0)
                this.collId = collId;
            else
                this.collId = null;
        } else this.collId = null;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId",getUserId())
                .append("realname",getRealname())
                .append("username",getUsername())
                .append("password",getPassword())
                .append("phone",getPhone())
                .append("userType",getUserType())
                .append("collId",getCollId())
                .append("loginTime",getLoginTime())
                .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getUserId())
                .append(getRealname())
                .append(getUsername())
                .append(getPassword())
                .append(getPhone())
                .append(getUserType())
                .append(getCollId())
                .append(getLoginTime())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User == false) return false;
        if(this == obj) return true;
        User other = (User)obj;
        return new EqualsBuilder()
                .append(getUserId(), other.getUserId())
                .isEquals();
    }
}