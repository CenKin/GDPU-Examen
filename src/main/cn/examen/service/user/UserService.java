package cn.examen.service.user;


import cn.examen.domain.user.User;
import cn.examen.service.BaseService;

public interface UserService extends BaseService<User> {

	User login(String username, String password);

}
