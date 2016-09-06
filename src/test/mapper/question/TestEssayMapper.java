package mapper.question;

import cn.examen.domain.question.Essay;
import cn.examen.mapper.question.EssayMapper;
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
public class TestEssayMapper {

    @Autowired
    EssayMapper essayMapper;

    @Test/*completed*/
    public void testInsert(){
        Essay essay = new Essay();
        essay.setId("3");
        essay.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        essay.setContent("123");
        essay.setSection("1");
        essay.setDiff(4);
        essayMapper.insert(essay);
    }

    @Test/*completed*/
    public void testDeleteById(){
        essayMapper.deleteById("3");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        essayMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testUpdate(){
        Essay essay = new Essay();
        essay.setId("1");
        essay.setContent("456");
        essay.setSection("2");
        essay.setDiff(6);
        essayMapper.update(essay);
    }

    @Test/*completed*/
    public void testFindList(){
        List<Essay> list = essayMapper.findList("essay_section", "第六章 树", "diff", "asc");
        if(list!=null) {
            for (Essay c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindALL(){
        List<Essay> list = essayMapper.findAll("diff", "asc");
        if(list!=null) {
            for (Essay c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testCount(){
        Essay e = new Essay();
        e.setSection("第六章 树");
        e.setDiff(4);
        Integer count = essayMapper.count(e);
        System.out.println(count);
    }

    @Test/*completed*/
    public void testQueryPage(){
        Map<String, Object> map = new HashMap();
        map.put("essaySection", "第七章 图");
        List<Essay> list = essayMapper.queryPage(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Essay c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testQueryList(){
        Map<String, Object> map = new HashMap();
        map.put("diff", 4);
        List<Essay> list = essayMapper.queryList(map, "diff", "desc");
        if(list!=null) {
            for (Essay c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testLike(){
        Map<String, Object> map = new HashMap();
        map.put("essaySection", "第七章");
        map.put("diff", 4);
        List<Essay> list = essayMapper.like(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Essay c : list){
                System.out.println(c.toString());
            }
        }
    }

}
