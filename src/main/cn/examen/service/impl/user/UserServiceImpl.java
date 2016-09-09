package cn.examen.service.impl.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.examen.domain.user.User;
import cn.examen.mapper.user.UserMapper;
import cn.examen.service.user.UserService;
import cn.examen.utils.BeanUtils2;
import cn.examen.utils.MD5Utils;
import cn.examen.utils.StateCode;
import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(String username, String password) {
		User user = userMapper.findOne("username", username);
		if(user!=null){
			String str = MD5Utils.getMD5(password);
            if(str.equals(user.getPassword())) {
				user.setLoginTime(new Date());
				userMapper.update(user);
				return user;
			}
		}
		return null;
	}

	@Override
	public String addOne(User param, Object... objects) {

        User userFromDb = userMapper.findOne("username", param.getUsername());
        if(userFromDb!=null){
            return StateCode.USERNAME_IS_EXIST;
        }

		String password = MD5Utils.getMD5(param.getPassword());
		param.setPassword(password);
        param.setUserId(CommonUtils.uuid());
        int code = userMapper.insert(param);
        return code==1 ? StateCode.ADD_SUCCESS : StateCode.ADD_FAIL;
	}

	@Override
	public Integer getTotalRecordByParam(User param) {
        Integer totalRecord = userMapper.countLike(param);
        return totalRecord == null ? 0 : totalRecord;
	}

	@Override
	public User getById(String id) {
        return userMapper.findOne("userId", id);
	}

	@Override
	public List<User> getPageListByParam(User param, int offset, int rows) {
        Map<String, Object> condition = BeanUtils2.transBean2Map(param);
        return userMapper.like(condition, offset, (rows-1)*offset, "realname", "ASC");
	}

	@Override
	public String updateOne(User param) {
		int code = userMapper.update(param);
		return code==1 ? StateCode.UPDATE_SUCCESS : StateCode.UPDATE_FAIL;
	}

	@Override
	public String deleteById(String id) {
		int code = userMapper.deleteById(id);
		return code==1 ? StateCode.DELETE_SUCCESS : StateCode.DELETE_FAIL;
	}
}
