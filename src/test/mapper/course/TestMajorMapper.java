package mapper.course;

import cn.examen.domain.course.Major;
import cn.examen.mapper.course.MajorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestMajorMapper {

    @Autowired
    MajorMapper majorMapper;

    @Test/*completed*/
    public void testInsert(){
        Major major = new Major();
        major.setMajorId("1");
        major.setMajorName("【专业名称单元测试】1");
        major.setCollId("7CEDEE8E0B324474B1F0393DD3DE1B66");
        majorMapper.insert(major);
    }

    @Test/*completed*/
    public void testDeleteById(){
        majorMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("3");
        list.add("4");
        majorMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        majorMapper.deleteByProperty("major_name", "【专业名称单元测试】");
    }

    @Test/*completed*/
    public void testUpdate(){
        Major major = new Major();
        major.setMajorId("2");
        major.setMajorName("【专业名称单元测试】");
        major.setCollId("3D69C6CB97FF4DE2A55DA4678B3F711C");
        majorMapper.update(major);
    }

    @Test/*completed*/
    public void testFindOne(){
        Major c = majorMapper.findOne("major_id", "3");
        System.out.println(c.toString());
    }

    @Test/*completed*/
    public void testFindList(){
        List<Major> list = majorMapper.findList("coll_id", "7CEDEE8E0B324474B1F0393DD3DE1B66", null, null);
        if(list!=null) {
            for (Major c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindAll(){
        List<Major> list = majorMapper.findAll("major_id", "asc");
        if(list!=null) {
            for (Major c : list){
                System.out.println(c.toString());
            }
        }
    }
}