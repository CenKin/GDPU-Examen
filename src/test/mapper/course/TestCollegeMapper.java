package mapper.course;

import cn.examen.domain.course.College;
import cn.examen.mapper.course.CollegeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestCollegeMapper {

    @Autowired
    CollegeMapper collegeMapper;

    @Test/*completed*/
    public void testInsert(){
        College college = new College();
        college.setCollId("3");
        college.setCollName("【学院名称单元测试】");
        collegeMapper.insert(college);
    }

    @Test/*completed*/
    public void testDeleteById(){
        collegeMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        collegeMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        collegeMapper.deleteByProperty("coll_name", "【学院名称单元测试】");
    }

    @Test/*completed*/
    public void testUpdate(){
        College college = new College();
        college.setCollId("1");
        college.setCollName("【学院名称单元测试3】");
        collegeMapper.update(college);
    }

    @Test/*completed*/
    public void testFindOne(){
        College c = collegeMapper.findOne("coll_id", "1");
        System.out.println(c.toString());
    }

    @Test/*completed*/
    public void testFindAll(){
        List<College> list = collegeMapper.findAll("coll_id", "asc");
        if(list!=null) {
            for (College c : list){
                System.out.println(c.toString());
            }
        }
    }
}
