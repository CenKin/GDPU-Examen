package mapper.paper;

import cn.examen.domain.paper.PaperFillin;
import cn.examen.mapper.paper.PaperFillinMapper;
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
public class TestPaperFillinMapper {

    @Autowired
    PaperFillinMapper paperFillinMapper;

    @Test/*completed*/
    public void testInsert(){
        PaperFillin paperFillin = new PaperFillin();
        paperFillin.setPaperId("1");
        paperFillin.setFillinId("1CD88DEA2CDC455ABD8FEAFBC7C514EE");
        paperFillinMapper.insert(paperFillin);
    }

    @Test/*completed*/
    public void testDeleteById(){
        paperFillinMapper.deleteById(3);
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        paperFillinMapper.deleteByIds(list);
    }

    @Test
    public void testDeleteByProperty(){
        paperFillinMapper.deleteByProperty("id", "4");
    }

    @Test
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("paperId", "1");
        map.put("fillinId", "1C7AEA4CCA814BC39F434C48644D68F5");
        paperFillinMapper.deleteByCondition(map);
    }

    @Test
    public void testUpdate(){
        PaperFillin paperFillin = new PaperFillin();
        paperFillin.setId(6);
        paperFillin.setPaperId("1");
        paperFillin.setFillinId("267F9C924C834C95BA35688FE202BD12");
        paperFillinMapper.update(paperFillin);
    }

    @Test
    public void testFindOne(){
        PaperFillin paperFillin = new PaperFillin();
        paperFillin.setPaperId("1");
        paperFillin.setFillinId("267F9C924C834C95BA35688FE202BD12");
        PaperFillin mc = paperFillinMapper.findOne(paperFillin);
        System.out.println(mc.toString());
    }

    @Test
    public void testFindList(){
        List<PaperFillin> list = paperFillinMapper.findList("fillin_id", "267F9C924C834C95BA35688FE202BD12", null, null);
        if(list!=null) {
            for (PaperFillin c : list){
                System.out.println(c.toString());
            }
        }
    }
}
