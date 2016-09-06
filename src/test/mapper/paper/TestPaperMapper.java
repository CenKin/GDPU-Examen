package mapper.paper;

import cn.examen.domain.paper.Paper;
import cn.examen.mapper.paper.PaperMapper;
import cn.itcast.commons.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext-dao.xml"})
public class TestPaperMapper {

    @Autowired
    PaperMapper paperMapper;

    @Test/*completed*/
    public void testInsert(){
        Paper paper = new Paper();
        paper.setPaperId("4");
        paper.setUserId("2");
        paper.setUserRealname("1");
        paper.setCollId("1");
        paper.setMajorId("1");
        paper.setCourseId("1");
        paper.setCourseName("1");
        paper.setChoiceScore(1);
        paper.setFillinScore(2);
        paper.setEssayScore(3);
        paper.setDiscussScore(4);
        paper.setPaperType("1");
        paper.setUseClasses("1");
        paper.setUseTerm("1");
        paper.setFromYear(16);
        paper.setToYear(17);
        paper.setCreatetime(new Date());
        paper.setVetted(1);
        paper.setAssessorId("1");
        paper.setAssessorName("1");
        paperMapper.insert(paper);
    }

    @Test/*completed*/
    public void testDeleteById(){
        paperMapper.deleteById("1");
    }

    @Test/*completed*/
    public void testDeleteByIds(){
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        paperMapper.deleteByIds(list);
    }

    @Test/*completed*/
    public void testDeleteByCondition(){
        Map<String, Object> map = new HashMap();
        map.put("userId", "2");
        paperMapper.deleteByCondition(map);
    }

    @Test/*completed*/
    public void testUpdate(){
        Paper paper = new Paper();
        paper.setPaperId("1");
        paper.setUserId("2");
        paper.setUserRealname("2");
        paper.setCollId("2");
        paper.setMajorId("2");
        paper.setCourseId("2");
        paper.setCourseName("2");
        paper.setPaperType("2");
        paper.setUseClasses("2");
        paper.setUseTerm("2");
        paper.setFromYear(16);
        paper.setToYear(17);
        paper.setCreatetime(new Date());
        paper.setVetted(1);
        paper.setAssessorId("1");
        paper.setAssessorName("1");
        paperMapper.update(paper);
    }

    @Test/*completed*/
    public void testFindById(){
        Paper p = paperMapper.findById("1");
        System.out.println(p.toString());
    }

    @Test/*completed*/
    public void testFindList(){
        List<Paper> list = paperMapper.findList("user_id", "1", "createtime", "desc");
        if(list!=null) {
            for (Paper p : list){
                System.out.println(p.toString());
            }
        }
    }

    @Test/*completed*/
    public void testFindAll(){
        List<Paper> list = paperMapper.findAll("createtime", "desc");
        if(list!=null) {
            for (Paper p : list){
                System.out.println(p.toString());
            }
        }
    }

    @Test/*completed*/
    public void testCount(){
        Paper p = new Paper();
        p.setUserId("1");
        System.out.println(paperMapper.count(p));
    }

    @Test/*completed*/
    public void testQueryPage(){
        Map<String, Object> map = new HashMap();
        map.put("userId", "1");
        List<Paper> list =paperMapper.queryPage(map, 0, 3, "createtime", "desc");
        if(list!=null) {
            for (Paper p : list){
                System.out.println(p.toString());
            }
        }
    }

    @Test/*completed*/
    public void testQueryList(){
        Map<String, Object> map = new HashMap();
        map.put("userId", "1");
        List<Paper> list =paperMapper.queryList(map, "createtime", "desc");
        if(list!=null) {
            for (Paper p : list){
                System.out.println(p.toString());
            }
        }
    }

    @Test/*completed*/
    public void testLike(){
        Map<String, Object> map = new HashMap();
        map.put("paperId", "22");
        List<Paper> list =paperMapper.like(map, 0, 3, "createtime", "desc");
        if(list!=null) {
            for (Paper p : list){
                System.out.println(p.toString());
            }
        }
    }

}
