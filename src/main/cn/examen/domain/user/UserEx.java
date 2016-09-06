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
        this.setLoginTimeVo(loginTime != null ? sdf.format(loginTime) : null);

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

        this.setCollIdName(coll.getCollName());
    }

    public String getCollIdName() {
        return collIdName;
    }

    public void setCollIdName(String collIdName) {
        this.collIdName = collIdName;
    }

    public String getUserTypeVo() {
        return userTypeVo;
    }

    public void setUserTypeVo(String userTypeVo) {
        this.userTypeVo = userTypeVo;
    }

    public String getLoginTimeVo() {
        return loginTimeVo;
    }

    public void setLoginTimeVo(String loginTimeVo) {
        this.loginTimeVo = loginTimeVo;
    }
}
