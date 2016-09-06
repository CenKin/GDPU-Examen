package cn.examen.service.impl.paper;

import cn.examen.domain.paper.*;
import cn.examen.domain.user.User;
import cn.examen.mapper.course.CourseMapper;
import cn.examen.mapper.paper.*;
import cn.examen.mapper.user.UserMapper;
import cn.examen.service.paper.PaperService;
import cn.examen.utils.BeanUtils2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("paperService")
public class PaperServiceImpl implements PaperService {

	@Autowired
	PaperMapper paperMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	CourseMapper courseMapper;
	@Autowired
	PaperChoiceMapper paperChoiceMapper;
	@Autowired
	PaperFillinMapper paperFillinMapper;
	@Autowired
	PaperEssayMapper paperEssayMapper;
	@Autowired
	PaperDiscussMapper paperDiscussMapper;
	
	@Override
	public void addPaper(Paper paper, List<String> choiceIds, List<String> fillinIds, List<String> essayIds, List<String> discussIds) {
		
		paperMapper.insert(paper);
		
		if(choiceIds!=null){
			for(String cid : choiceIds){
				PaperChoice pc = new PaperChoice();
				pc.setPaperId(paper.getPaperId());
				pc.setChoiceId(cid);
				paperChoiceMapper.insert(pc);
			}
		}
		if(fillinIds!=null){
			for(String fid : fillinIds){
				PaperFillin pf = new PaperFillin();
				pf.setPaperId(paper.getPaperId());
				pf.setFillinId(fid);
				paperFillinMapper.insert(pf);
			}
		}
		if(essayIds!=null){
			for(String eid : essayIds){
				PaperEssay pe = new PaperEssay();
				pe.setPaperId(paper.getPaperId());
				pe.setEssayId(eid);
				paperEssayMapper.insert(pe);
			}
		}
		if(discussIds!=null){
			for(String did : discussIds){
				PaperDiscuss pd = new PaperDiscuss();
				pd.setPaperId(paper.getPaperId());
				pd.setDiscussId(did);
				paperDiscussMapper.insert(pd);
			}
		}
	}

	@Override
	public List<Paper> getPageListByParam(Paper paper, int offset, int rows) {

        Map<String, Object> condition = BeanUtils2.transBean2Map(paper);
        List<Paper> list = paperMapper.queryPage(condition, (offset-1)*rows, rows, "createtime", "desc");
		return list;
	}

	@Override
	public void deletePaper(String paperId) {
		
		paperChoiceMapper.deleteByProperty("paperId", paperId);
		paperEssayMapper.deleteByProperty("paperId", paperId);
		paperDiscussMapper.deleteByProperty("paperId", paperId);
		paperFillinMapper.deleteByProperty("paperId", paperId);
		
		paperMapper.deleteById(paperId);
		System.out.println("---------->deletePaper method run");
	}

	@Override
	public Paper findPaperById(String id) {
		return paperMapper.findById(id);
	}

	@Override
	public void updatePaper(Paper paper, List<String> choiceIds, List<String> fillinIds, List<String> essayIds, List<String> discussIds) {

		paperMapper.update(paper);
		String paperId = paper.getPaperId();
		
		if(choiceIds!=null){
			paperChoiceMapper.deleteByProperty("paperId", paperId);
			for(String id : choiceIds){
				PaperChoice p = new PaperChoice();
				p.setPaperId(paper.getPaperId());
				p.setChoiceId(id);
				paperChoiceMapper.insert(p);
			}
		}
		if(fillinIds!=null){
			paperFillinMapper.deleteByProperty("paperId", paperId);
			for(String id : fillinIds){
				PaperFillin p = new PaperFillin();
				p.setPaperId(paper.getPaperId());
				p.setFillinId(id);
				paperFillinMapper.insert(p);
			}
		}
		if(essayIds!=null){
			paperEssayMapper.deleteByProperty("paperId", paperId);
			for(String id : essayIds){
				PaperEssay p = new PaperEssay();
				p.setPaperId(paper.getPaperId());
				p.setEssayId(id);
				paperEssayMapper.insert(p);
			}
		}
		if(discussIds!=null){
			paperDiscussMapper.deleteByProperty("paperId", paperId);
			for(String id : discussIds){
				PaperDiscuss p = new PaperDiscuss();
				p.setPaperId(paper.getPaperId());
				p.setDiscussId(id);
				paperDiscussMapper.insert(p);
			}
		}
	}

	@Override
	public void check(String id) {
		Paper paper = new Paper();
		paper.setPaperId(id);
		paper.setVetted(2);
		paperMapper.update(paper);
	}

	@Override
	public List<Paper> getPassPaperIds() {
		return paperMapper.findList("vetted", 3, null, null);
	}

	@Override
	public List<String> getQuestionIds(String paperId, String type) {
		List<String> result = new ArrayList();

		if(type.equals("choice")) {
			List<PaperChoice> ps = paperChoiceMapper.findList("paperId", paperId, null, null);
			if(ps!=null) {
				for(PaperChoice p : ps) {
					result.add(p.getChoiceId());
				}
			}
		}
		if(type.equals("fillin")) {
			List<PaperFillin> ps = paperFillinMapper.findList("paperId", paperId, null, null);
			if(ps!=null) {
				for(PaperFillin p : ps) {
					result.add(p.getFillinId());
				}
			}
		}
		if(type.equals("essay")) {
			List<PaperEssay> ps = paperEssayMapper.findList("paperId", paperId, null, null);
			if(ps!=null) {
				for(PaperEssay p : ps) {
					result.add(p.getEssayId());
				}
			}
		}
		if(type.equals("discuss")) {
			List<PaperDiscuss> ps = paperDiscussMapper.findList("paperId", paperId, null, null);
			if(ps!=null) {
				for(PaperDiscuss p : ps) {
					result.add(p.getDiscussId());
				}
			}
		}
		return result;
	}

	@Override
	public void passPaper(String paperId, String assessorId) {
		Paper p = new Paper();
		p.setPaperId(paperId);
		p.setVetted(3);
		User user = userMapper.findById(assessorId);
		p.setAssessorId(assessorId);
		p.setAssessorName(user.getRealname());
		paperMapper.update(p);
	}

	@Override
	public void failPaper(String paperId, String assessorId) {
		Paper p = new Paper();
		p.setPaperId(paperId);
		p.setVetted(4);
		User user = userMapper.findById(assessorId);
		p.setAssessorId(assessorId);
		p.setAssessorName(user.getRealname());
		paperMapper.update(p);
	}

    @Override
	public Integer getTotalRecordByParam(Paper param) {
        Integer totalRecord = paperMapper.count(param);
        return totalRecord==null? 0 : totalRecord;
	}

    @Override
	public Paper getById(String id) {
		return paperMapper.findById(id);
	}
}
