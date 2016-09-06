package mapper.question;

import cn.examen.domain.question.Discuss;
import cn.examen.mapper.question.DiscussMapper;
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
public class TestDiscussMapper {

    @Autowired
    DiscussMapper discussMapper;

    @Test/*completed*/
    public void testInsert(){
        Discuss discuss = new Discuss();
        discuss.setId("2");
        discuss.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        discuss.setContent("123");
        discuss.setSection("1");
        discuss.setDiff(4);
        discussMapper.insert(discuss);
    }

    @Test/*completed*/
    public void testDeleteById(){
        discussMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        discussMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testUpdate(){
        Discuss discuss = new Discuss();
        discuss.setId("1");
        discuss.setContent("456");
        discuss.setSection("2");
        discuss.setDiff(6);
        discussMapper.update(discuss);
    }

    @Test/*completed*/
    public void testFindList(){
        List<Discuss> list = discussMapper.findList("discuss_section", "第六章 树", "diff", "asc");
        if(list!=null) {
            for (Discuss c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindALL(){
        List<Discuss> list = discussMapper.findAll("diff", "asc");
        if(list!=null) {
            for (Discuss c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testCount(){
        Discuss d = new Discuss();
        d.setSection("第六章 树");
        d.setDiff(4);
        Integer count = discussMapper.count(d);
        System.out.println(count);
    }

    @Test/*completed*/
    public void testQueryPage(){
        Map<String, Object> map = new HashMap();
        map.put("discussSection", "第七章 图");
        List<Discuss> list = discussMapper.queryPage(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Discuss c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testQueryList(){
        Map<String, Object> map = new HashMap();
        map.put("diff", 4);
        List<Discuss> list = discussMapper.queryList(map, "diff", "desc");
        if(list!=null) {
            for (Discuss c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testLike(){
        Map<String, Object> map = new HashMap();
        map.put("discussSection", "第七章");
        List<Discuss> list = discussMapper.like(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Discuss c : list){
                System.out.println(c.toString());
            }
        }
    }

}
