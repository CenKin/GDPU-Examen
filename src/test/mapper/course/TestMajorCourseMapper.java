package mapper.course;

import cn.examen.domain.course.MajorCourse;
import cn.examen.mapper.course.MajorCourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestMajorCourseMapper {

    @Autowired
    MajorCourseMapper majorCourseMapper;

    @Test/*completed*/
    public void testInsert(){
        MajorCourse majorCourse = new MajorCourse();
        majorCourse.setMajorId("1A7EEF87107F403CAF24BDFE8D602091");
        majorCourse.setCourseId("D79D520CCE2048CDB2CF4562ED2C1FD8");
        majorCourseMapper.insert(majorCourse);
    }

    @Test/*completed*/
    public void testDeleteById(){
        majorCourseMapper.deleteById(5);
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add(6);
        list.add(7);
        majorCourseMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        majorCourseMapper.deleteByProperty("id", "8");
    }

    @Test/*completed*/
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("majorId", "1A7EEF87107F403CAF24BDFE8D602091");
        map.put("courseId", "D79D520CCE2048CDB2CF4562ED2C1FD8");
        majorCourseMapper.deleteByCondition(map);
    }

    @Test/*completed*/
    public void testUpdate(){
        MajorCourse majorCourse = new MajorCourse();
        majorCourse.setId(9);
        majorCourse.setMajorId("1A7EEF87107F403CAF24BDFE8D602091");
        majorCourse.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        majorCourseMapper.update(majorCourse);
    }

    @Test/*completed*/
    public void testFindOne(){
        MajorCourse majorCourse = new MajorCourse();
        majorCourse.setMajorId("1A7EEF87107F403CAF24BDFE8D602091");
        majorCourse.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        MajorCourse mc = majorCourseMapper.findOne(majorCourse);
        System.out.println(mc.toString());
    }

    @Test/*completed*/
    public void testFindList(){
        List<MajorCourse> list = majorCourseMapper.findList("course_id", "D79D520CCE2048CDB2CF4562ED2C1FD8", null, null);
        if(list!=null) {
            for (MajorCourse c : list){
                System.out.println(c.toString());
            }
        }
    }
}
