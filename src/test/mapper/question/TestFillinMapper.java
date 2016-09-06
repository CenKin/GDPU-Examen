package mapper.question;

import cn.examen.domain.question.Fillin;
import cn.examen.mapper.question.FillinMapper;
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
public class TestFillinMapper {

    @Autowired
    FillinMapper fillinMapper;

    @Test/*completed*/
    public void testInsert(){
        Fillin fillin = new Fillin();
        fillin.setId("1");
        fillin.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        fillin.setContent("123");
        fillin.setSection("1");
        fillin.setDiff(4);
        fillinMapper.insert(fillin);
    }

    @Test/*completed*/
    public void testDeleteById(){
        fillinMapper.deleteById("3");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        fillinMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testUpdate(){
        Fillin fillin = new Fillin();
        fillin.setId("1");
        fillin.setContent("456");
        fillin.setSection("2");
        fillin.setDiff(6);
        fillinMapper.update(fillin);
    }

    @Test/*completed*/
    public void testFindList(){
        List<Fillin> list = fillinMapper.findList("fillin_section", "第六章 树", "diff", "asc");
        if(list!=null) {
            for (Fillin c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindALL(){
        List<Fillin> list = fillinMapper.findAll("diff", "asc");
        if(list!=null) {
            for (Fillin c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testCount(){
        Fillin f = new Fillin();
        f.setSection("第六章 树");
        f.setDiff(4);
        Integer count = fillinMapper.count(f);
        System.out.println(count);
    }

    @Test/*completed*/
    public void testQueryPage(){
        Map<String, Object> map = new HashMap();
        map.put("fillinSection", "第七章 图");
        List<Fillin> list = fillinMapper.queryPage(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Fillin c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testQueryList(){
        Map<String, Object> map = new HashMap();
        map.put("diff", 4);
        List<Fillin> list = fillinMapper.queryList(map, "diff", "desc");
        if(list!=null) {
            for (Fillin c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testLike(){
        Map<String, Object> map = new HashMap();
        map.put("fillinSection", "第七章");
        map.put("diff", 4);
        List<Fillin> list = fillinMapper.like(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Fillin c : list){
                System.out.println(c.toString());
            }
        }
    }

}
