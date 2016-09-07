package cn.examen.domain.user;

import cn.examen.domain.course.College;
import cn.examen.utils.GolbalCode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/5.
 */
public class UserEx extends User {
    private String collIdName;
    private String userTypeVo;
    private String loginTimeVo;

    public UserEx (User user, College coll) {
        super(user);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date loginTime = user.getLoginTime();
        this.setLoginTimeVo(loginTime != null ? sdf.format(loginTime) : "未曾登录");

        Integer userType = user.getUserType();
        if (userType==null){
            this.setUserTypeVo(null);
        }else if(userType== GolbalCode.ADMINISTRATOR_ROLE){
            this.setUserTypeVo("管理员");
        } else if(userType== GolbalCode.TEACHER_ROLE){
            this.setUserTypeVo("教师");
        } else if(userType== GolbalCode.MANAGER_ROLE){
            this.setUserTypeVo("系主任");
        } else this.setUserTypeVo(null);

        if(coll!=null) {
            this.setCollIdName(coll.getCollName());
        } else this.setCollIdName("系统管理员");
    }

    public String getCollIdName() {
        return collIdName;
    }

    public void setCollIdName(String collIdName) {
        if(collIdName!=null){
            if(collIdName.trim().length() > 0)
                this.collIdName = collIdName;
            else
                this.collIdName = null;
        } else this.collIdName = null;
    }

    public String getUserTypeVo() {
        return userTypeVo;
    }

    public void setUserTypeVo(String userTypeVo) {
        if(userTypeVo!=null){
            if(userTypeVo.trim().length() > 0)
                this.userTypeVo = userTypeVo;
            else
                this.userTypeVo = null;
        } else this.userTypeVo = null;
    }

    public String getLoginTimeVo() {
        return loginTimeVo;
    }

    public void setLoginTimeVo(String loginTimeVo) {
        if(loginTimeVo!=null){
            if(loginTimeVo.trim().length() > 0)
                this.loginTimeVo = loginTimeVo;
            else
                this.loginTimeVo = null;
        } else this.loginTimeVo = null;
    }
}
