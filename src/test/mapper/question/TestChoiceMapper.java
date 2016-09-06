package mapper.question;

import cn.examen.domain.question.Choice;
import cn.examen.mapper.question.ChoiceMapper;
import cn.itcast.commons.CommonUtils;
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
public class TestChoiceMapper {

    @Autowired
    ChoiceMapper choiceMapper;

    @Test/*completed*/
    public void testInsert(){
        Choice choice = new Choice();
        choice.setId(CommonUtils.uuid());
        choice.setCourseId("59E6195BEFDF4586B9D109092CBC94B0");
        choice.setContent("123");
        choice.setSection("1");
        choice.setAnswer1("AAA");
        choice.setAnswer2("BBB");
        choice.setRightAnswer("A");
        choice.setDiff(4);
        choiceMapper.insert(choice);
    }

    @Test/*completed*/
    public void testDeleteById(){
        choiceMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        choiceMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testUpdate(){
        Choice choice = new Choice();
        choice.setId("1");
        choice.setContent("456");
        choice.setSection("2");
        choice.setAnswer1("A");
        choice.setAnswer2("B");
        choice.setAnswer3("C");
        choice.setAnswer4("D");
        choice.setRightAnswer("A");
        choice.setDiff(6);
        choiceMapper.update(choice);
    }

    @Test/*completed*/
    public void testFindList(){
        List<Choice> list = choiceMapper.findList("choice_section", "第六章   树", "diff", "asc");
        if(list!=null) {
            for (Choice c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindALL(){
        List<Choice> list = choiceMapper.findAll("diff", "asc");
        if(list!=null) {
            for (Choice c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testCount(){
        Choice c = new Choice();
        c.setSection("第六章   树");
        c.setDiff(4);
        Integer count = choiceMapper.count(c);
        System.out.println(count);
    }

    @Test/*completed*/
    public void testQueryPage(){
        Map<String, Object> map = new HashMap();
        map.put("choiceSection", "第六章   树");
        List<Choice> list = choiceMapper.queryPage(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Choice c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testQueryList(){
        Map<String, Object> map = new HashMap();
        map.put("diff", 4);
        List<Choice> list = choiceMapper.queryList(map, "diff", "desc");
        if(list!=null) {
            for (Choice c : list){
                System.out.println(c.toString());
            }
        }
    }

    @Test/*completed*/
    public void testLike(){
        Map<String, Object> map = new HashMap();
        map.put("choiceSection", "第六章");
        List<Choice> list = choiceMapper.like(map, 0, 5, "diff", "desc");
        if(list!=null) {
            for (Choice c : list){
                System.out.println(c.toString());
            }
        }
    }

}
