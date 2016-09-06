package cn.examen.service.paper;


import cn.examen.domain.paper.Paper;
import cn.examen.domain.paper.PaperEx;
import cn.examen.service.BaseService;

import java.util.List;

public interface PaperService{

	void addPaper(Paper paper, List<String> choiceIds, List<String> fillinIds, List<String> essayIds, List<String> discussIds);
	
	void deletePaper(String paperId);
	
	Paper findPaperById(String paperId);

	void updatePaper(Paper paper, List<String> choiceIds, List<String> fillinIds, List<String> essayIds, List<String> discussIds);

	void check(String paperId);

	List<Paper> getPassPaperIds();

	List<String> getQuestionIds(String paperid, String type);
	
	void passPaper(String paperId, String assessor);
	
	void failPaper(String paperId, String assessor);

	List<Paper> getPageListByParam(Paper paper, int offset, int rows);

	Integer getTotalRecordByParam(Paper param);

	Paper getById(String id);
}