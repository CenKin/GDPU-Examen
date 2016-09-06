package cn.examen.controller;


import cn.examen.domain.commons.Page;
import cn.examen.domain.paper.Paper;
import cn.examen.domain.paper.PaperEx;
import cn.examen.domain.user.User;
import cn.examen.service.course.CollegeService;
import cn.examen.service.course.MajorService;
import cn.examen.service.paper.PaperService;
import cn.examen.service.user.UserService;
import cn.examen.utils.GolbalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	PaperService paperService;
	@Autowired
	CollegeService collegeService;
	@Autowired
	MajorService majorService;
	
	/**
	 * 功能：返回格式化的paper对象
	 * @retrun 格式化的paper对象
	 */
	@RequestMapping("/getPaperJson")
	public @ResponseBody Paper getPaperJson(HttpSession session){
		Paper paper = (Paper) session.getAttribute("paper");
		return paper;
	}
	
	/**
	 * 功能：录入试卷的基本信息
	 * 描述：将userId、courseId等信息设置到session中的paper对象中
	 * @Param session session对象
	 * @Param paperEx 数据的载体
	 * @retrun 跳转到选题界面
	 */
	@RequestMapping("/inputPaperInfo")
	public String inputPaperInfo(HttpSession session, Paper paper){
		//这里可以接收到的对象有：courseId、paperType、fromYear、toYear、useTerm、useClasses
		//EX扩展中存放：majorId、collId
		//session中有userId
		User user = (User)session.getAttribute("user");
		paper.setUserId(user.getUserId());
		
		//如果session中有paper对象，需要设置paperId、Score
		//如果没有，代表是第一次出卷
		Paper paperFromSession = (Paper)session.getAttribute("paper");
		if(paperFromSession!=null){
			String paperId = paperFromSession.getPaperId();
			Integer choiceScore = paperFromSession.getChoiceScore();
			Integer discussScore = paperFromSession.getDiscussScore();
			Integer essayScore = paperFromSession.getEssayScore();
			Integer fillinScore = paperFromSession.getFillinScore();
			paper.setPaperId(paperId==null ? null : paperId);
			paper.setChoiceScore(choiceScore==null ? 0 : choiceScore);
			paper.setDiscussScore(discussScore==null ? 0 : discussScore);
			paper.setEssayScore(essayScore==null ? 0 : essayScore);
			paper.setFillinScore(fillinScore==null ? 0 : fillinScore);
		}
		
		/*此时session中的paper对象已经存放好的数据有：
			majorId、collId、useClass、courseId、
			paperType、fromYear、toYear、useTerm、userId、
			paperId（仅修改试卷的情况下）
		*/
		session.setAttribute("paper", paper);
		return "forward:/WEB-INF/jsp/teacher/paperDetail.jsp";
	}
	
	/**
	 * 功能：获取试卷的分页信息
	 * 描述：如果是教师用户，则获取他自己编辑过的试卷列表
	 *      如果是系主任，则获取他自己学院提交上来的试卷列表
	 * 		如果是管理员，则获取所有的试卷列表
	 * @param session session对象
	 */
	@RequestMapping("/getPaperPage")
	public @ResponseBody Page<PaperEx> getPaperPage(HttpSession session, Paper paper,
                                                    @RequestParam(value = "offset", required = false, defaultValue = "1") int offset,
                                                    @RequestParam(value = "rows", required = false, defaultValue = "10") int rows){
		User user = (User) session.getAttribute("user");
        Paper param = new Paper();
        if(user!=null){
            Integer userType = user.getUserType();
            if(userType!=null && userType==GolbalCode.MANAGER_ROLE){
                //设置审核状态参数
                param.setVetted(paper.getVetted()==null ? 2 : paper.getVetted());
                //设置对应学院参数
                param.setCollId(user.getCollId());
            } else if(userType!=null && userType==GolbalCode.TEACHER_ROLE){
                //设置对应用户id参数
                param.setUserId(user.getUserId());
            } else if(userType!=null && userType==GolbalCode.ADMINISTRATOR_ROLE){
                param = paper;
            } else return new Page(0, rows, null);
        } else return new Page(0, rows, null);

        int totalRecord = paperService.getTotalRecordByParam(param);
        List<Paper> list = paperService.getPageListByParam(param, offset, rows);
        List<PaperEx> listVo = new ArrayList();
        for(Paper p : list) {
            PaperEx pe = new PaperEx(p, user);
            listVo.add(pe);
        }
        Page<PaperEx> page = new Page(totalRecord, rows, listVo);
        return page;
	}

	/**
	 * 功能：从数据库中删除试卷
	 * @param paperId 要删除的试卷的ID号
	 */
	@RequestMapping("/delete")
	public @ResponseBody String delete(String paperId)  {
		paperService.deletePaper(paperId);
		return null;
	}
	
	/**
	 * 功能：把数据库中的试卷信息设置到session中
	 * 描述：从数据库中获取某试卷信息，将这些信息设置到session中共前台编辑
	 * @param paperId 要查找的试卷的ID号
	 */
	@RequestMapping("/getPaperInfoFromDb")
	public String getPaperInfoFromDb(HttpSession session, String paperId){
		
		Paper paper = paperService.findPaperById(paperId);
		
		if(paper!=null){
			List<String> choiceIds = paperService.getQuestionIds(paper.getPaperId(), "choice");
			session.setAttribute("choiceIds", choiceIds);
			List<String> fillinIds = paperService.getQuestionIds(paper.getPaperId(), "fillin");
			session.setAttribute("fillinIds", fillinIds);
			List<String> essayIds = paperService.getQuestionIds(paper.getPaperId(), "essay");
			session.setAttribute("essayIds", essayIds);
			List<String> discussIds = paperService.getQuestionIds(paper.getPaperId(), "discuss");
			session.setAttribute("discussIds", discussIds);
			
			session.setAttribute("paper", paper);
		}
		return "forward:/WEB-INF/jsp/teacher/paperInfo.jsp";
	}
	
	/**
	 * 功能：提交试卷给系主任审核
	 * 描述：修改数据库中vetted的状态（1 to 2）
	 * @param paperId 提交的试卷的ID
	 * @return 提交一个标准的JSON字符串("{\"\":\"\"}")给ajax执行success方法
	 */
	@RequestMapping("/check")
	public @ResponseBody String check(String paperId){
		paperService.check(paperId);
		return "{\"\":\"\"}";
	}
	
	/**
	 * 功能：使某张试卷审核状态为不通过
	 * 描述：系主任用户使用
	 * @param paperId 试卷号
	 * @return 标准JSON字符串
	 */
	@RequestMapping("/fail")
	public @ResponseBody String fail(HttpSession session, String paperId){
		User user = (User) session.getAttribute("user");
		paperService.failPaper(paperId, user.getUserId());
		return "{\"\":\"\"}";
	}
	
	/**
	 * 功能：使某张试卷审核状态为通过
	 * 描述：系主任用户使用
	 * @param paperId 试卷号
	 * @return 标准JSON字符串
	 */
	@RequestMapping("/pass")
	public @ResponseBody String pass(HttpSession session, String paperId){
		User user = (User) session.getAttribute("user");
		paperService.passPaper(paperId, user.getUserId());
		return null;
	}
}
