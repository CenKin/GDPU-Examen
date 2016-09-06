package mapper.user;

import cn.examen.domain.user.User;
import cn.examen.mapper.user.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestUserMapper {

    @Autowired
    UserMapper userMapper;

    @Test/*completed*/
    public void testInsert(){
        User user = new User();
        user.setUserId("1");
        user.setRealname("123");
        user.setUsername("123456");
        user.setPassword("654321");
        user.setPhone("123456");
        user.setUserType(1);
        user.setLoginTime(new Date());
        userMapper.insert(user);
    }

    @Test/*completed*/
    public void testDeleteById(){
        userMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("3");
        list.add("2");
        userMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        userMapper.deleteByProperty("phone", "123456");
    }

    @Test/*completed*/
    public void testUpdate(){
        User user = new User();
        user.setUserId("2");
        user.setRealname("123");
        user.setUsername("123456000");
        user.setPassword("654321");
        user.setPhone("123456");
        user.setUserType(1);
        user.setLoginTime(new Date());
        userMapper.update(user);
    }

    @Test/*completed*/
    public void testFindById(){
        User user = userMapper.findById("2");
        System.out.println(user.toString());
    }

    @Test/*completed*/
    public void testFindOne(){
        User user = userMapper.findOne("phone", "1234562");
        System.out.println(user.toString());
    }

    @Test/*completed*/
    public void testFindList(){
        List<User> list = userMapper.findList("user_type", "2", null, null);
        if(list!=null) {
            for (User c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindAll(){
        List<User> list = userMapper.findAll("login_time", "asc");
        if(list!=null) {
            for (User c : list){
                System.out.println(c.toString());
            }
        }
    }
}
