package mapper.paper;

import cn.examen.domain.paper.PaperDiscuss;
import cn.examen.mapper.paper.PaperDiscussMapper;
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
public class TestPaperDiscussMapper {

    @Autowired
    PaperDiscussMapper paperDiscussMapper;

    @Test/*completed*/
    public void testInsert(){
        PaperDiscuss paperDiscuss = new PaperDiscuss();
        paperDiscuss.setPaperId("2");
        paperDiscuss.setDiscussId("2055CD6CD9AF46B790635D2B5C220DF3");
        paperDiscussMapper.insert(paperDiscuss);
    }

    @Test/*completed*/
    public void testDeleteById(){
        paperDiscussMapper.deleteById(3);
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add(5);
        list.add(2);
        paperDiscussMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        paperDiscussMapper.deleteByProperty("id", "4");
    }

    @Test/*completed*/
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("paperId", "1");
        map.put("discussId", "1C7AEA4CCA814BC39F434C48644D68F5");
        paperDiscussMapper.deleteByCondition(map);
    }

    @Test/*completed*/
    public void testUpdate(){
        PaperDiscuss paperDiscuss = new PaperDiscuss();
        paperDiscuss.setId(6);
        paperDiscuss.setPaperId("1");
        paperDiscuss.setDiscussId("267F9C924C834C95BA35688FE202BD12");
        paperDiscussMapper.update(paperDiscuss);
    }

    @Test
    public void testFindOne(){
        PaperDiscuss paperDiscuss = new PaperDiscuss();
        paperDiscuss.setPaperId("1");
        paperDiscuss.setDiscussId("267F9C924C834C95BA35688FE202BD12");
        PaperDiscuss mc = paperDiscussMapper.findOne(paperDiscuss);
        System.out.println(mc.toString());
    }

    @Test
    public void testFindList(){
        List<PaperDiscuss> list = paperDiscussMapper.findList("discuss_id", "267F9C924C834C95BA35688FE202BD12", null, null);
        if(list!=null) {
            for (PaperDiscuss c : list){
                System.out.println(c.toString());
            }
        }
    }
}
