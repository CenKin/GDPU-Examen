package mapper.course;

import cn.examen.domain.course.Course;
import cn.examen.mapper.course.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestCourseMapper {

    @Autowired
    CourseMapper courseMapper;

    @Test/*completed*/
    public void testInsert(){
        Course course = new Course();
        course.setCourseId("2");
        course.setCourseName("【课程名称单元测试2】");
        courseMapper.insert(course);
    }

    @Test/*completed*/
    public void testDeleteById(){
        courseMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        courseMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        courseMapper.deleteByProperty("course_name", "【课程名称单元测试3】");
    }

    @Test/*completed*/
    public void testUpdate(){
        Course course = new Course();
        course.setCourseId("1");
        course.setCourseName("【课程名称单元测试3】");
        courseMapper.update(course);
    }

    @Test/*completed*/
    public void testFindOne(){
        Course c = courseMapper.findOne("course_id", "1");
        System.out.println(c.toString());
    }

    @Test/*completed*/
    public void testFindAll(){
        List<Course> list = courseMapper.findAll("course_id", "asc");
        if(list!=null) {
            for (Course c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindListByMajorId(){
        List<Course> list = courseMapper.findListByMajorId("998173CA6B6A44ACB9D957A32B71934C", "course_id", "asc");
        if(list!=null) {
            for (Course c : list){
                System.out.println(c.toString());
            }
        }
    }

}
