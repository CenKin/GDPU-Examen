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
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getCollId() {
        return collId;
    }

    public void setCollId(String collId) {
        this.collId = collId;
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