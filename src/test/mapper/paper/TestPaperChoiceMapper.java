package mapper.paper;

import cn.examen.domain.paper.PaperChoice;
import cn.examen.mapper.paper.PaperChoiceMapper;
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
public class TestPaperChoiceMapper {

    @Autowired
    PaperChoiceMapper paperChoiceMapper;

    @Test/*completed*/
    public void testInsert(){
        PaperChoice paperChoice = new PaperChoice();
        paperChoice.setPaperId("2");
        paperChoice.setChoiceId("2055CD6CD9AF46B790635D2B5C220DF3");
        paperChoiceMapper.insert(paperChoice);
    }

    @Test/*completed*/
    public void testDeleteById(){
        paperChoiceMapper.deleteById(3);
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add(5);
        list.add(2);
        paperChoiceMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        paperChoiceMapper.deleteByProperty("id", "4");
    }

    @Test/*completed*/
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("paperId", "1");
        map.put("choiceId", "1C7AEA4CCA814BC39F434C48644D68F5");
        paperChoiceMapper.deleteByCondition(map);
    }

    @Test/*completed*/
    public void testUpdate(){
        PaperChoice paperChoice = new PaperChoice();
        paperChoice.setId(6);
        paperChoice.setPaperId("1");
        paperChoice.setChoiceId("267F9C924C834C95BA35688FE202BD12");
        paperChoiceMapper.update(paperChoice);
    }

    @Test
    public void testFindOne(){
        PaperChoice paperChoice = new PaperChoice();
        paperChoice.setPaperId("1");
        paperChoice.setChoiceId("267F9C924C834C95BA35688FE202BD12");
        PaperChoice mc = paperChoiceMapper.findOne(paperChoice);
        System.out.println(mc.toString());
    }

    @Test
    public void testFindList(){
        List<PaperChoice> list = paperChoiceMapper.findList("choice_id", "267F9C924C834C95BA35688FE202BD12", null, null);
        if(list!=null) {
            for (PaperChoice c : list){
                System.out.println(c.toString());
            }
        }
    }
}
