package mapper.paper;

import cn.examen.domain.paper.PaperEssay;
import cn.examen.mapper.paper.PaperEssayMapper;
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
public class TestPaperEssayMapper {

    @Autowired
    PaperEssayMapper paperEssayMapper;

    @Test/*completed*/
    public void testInsert(){
        PaperEssay paperEssay = new PaperEssay();
        paperEssay.setPaperId("2");
        paperEssay.setEssayId("2055CD6CD9AF46B790635D2B5C220DF3");
        paperEssayMapper.insert(paperEssay);
    }

    @Test/*completed*/
    public void testDeleteById(){
        paperEssayMapper.deleteById(3);
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add(5);
        list.add(2);
        paperEssayMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByProperty(){
        paperEssayMapper.deleteByProperty("id", "4");
    }

    @Test/*completed*/
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("paperId", "1");
        map.put("essayId", "1C7AEA4CCA814BC39F434C48644D68F5");
        paperEssayMapper.deleteByCondition(map);
    }

    @Test/*completed*/
    public void testUpdate(){
        PaperEssay paperEssay = new PaperEssay();
        paperEssay.setId(6);
        paperEssay.setPaperId("1");
        paperEssay.setEssayId("267F9C924C834C95BA35688FE202BD12");
        paperEssayMapper.update(paperEssay);
    }

    @Test
    public void testFindOne(){
        PaperEssay paperEssay = new PaperEssay();
        paperEssay.setPaperId("1");
        paperEssay.setEssayId("267F9C924C834C95BA35688FE202BD12");
        PaperEssay mc = paperEssayMapper.findOne(paperEssay);
        System.out.println(mc.toString());
    }

    @Test
    public void testFindList(){
        List<PaperEssay> list = paperEssayMapper.findList("essay_id", "267F9C924C834C95BA35688FE202BD12", null, null);
        if(list!=null) {
            for (PaperEssay c : list){
                System.out.println(c.toString());
            }
        }
    }
}
