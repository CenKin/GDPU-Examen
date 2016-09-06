package cn.examen.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.examen.domain.commons.Page;
import cn.examen.domain.paper.Paper;
import cn.examen.domain.question.Choice;
import cn.examen.domain.question.Discuss;
import cn.examen.domain.question.Essay;
import cn.examen.domain.question.Fillin;
import cn.examen.domain.user.User;
import cn.examen.exception.GlobolException;
import cn.examen.service.course.CourseService;
import cn.examen.service.paper.PaperService;
import cn.examen.service.question.ChoiceService;
import cn.examen.service.question.DiscussService;
import cn.examen.service.question.EssayService;
import cn.examen.service.question.FillinService;
import cn.examen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.commons.CommonUtils;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	ChoiceService choiceService;
	@Autowired
	DiscussService discussService;
	@Autowired
	EssayService essayService;
	@Autowired
	FillinService fillinService;
	@Autowired
	UserService userService;
	@Autowired
	PaperService paperService;
	@Autowired
	CourseService courseService;
	
	//获取题目的总记录数
	@RequestMapping("/getQuestionTotalRecord")
	public @ResponseBody Integer getQuestionTotalRecord(HttpSession session, String type) {
		
		Integer totalRecord = 0;
		String courseId;
		Paper paper = (Paper) session.getAttribute("paper");
		if(paper!=null && paper.getCourseId()!=null){
			courseId = paper.getCourseId();			
		} else throw new GlobolException("课程号未设置");
		switch (type) {
			case "choice":
				Choice c = new Choice();
				c.setCourseId(courseId);
				totalRecord = choiceService.getTotalRecordByParam(c);break;
			case "fillin":
				Fillin f = new Fillin();
				f.setCourseId(courseId);
				totalRecord = fillinService.getTotalRecordByParam(f);break;
			case "essay":
				Essay e = new Essay();
				e.setCourseId(courseId);
				totalRecord = essayService.getTotalRecordByParam(e);break;
			case "discuss":
				Discuss d = new Discuss();
				d.setCourseId(courseId);
				totalRecord = discussService.getTotalRecordByParam(d);break;
			default: totalRecord = 0; break;
		}
		return totalRecord;
	}
	
	//获取题目分页数据
	@RequestMapping("/getQuestionPage")
	public @ResponseBody Page<?> getQuestionPage(HttpSession session, String type,
                                                 @RequestParam(value="offset", required=false, defaultValue="1") int offset,
                                                 @RequestParam(value="rows", required=false, defaultValue="10") int rows) {
        String courseId;
		if(session.getAttribute("paper")!=null){
            Paper paper = (Paper) session.getAttribute("paper");
            if(paper.getCourseId()!=null){
                courseId = paper.getCourseId();
            } else {
                System.out.println("课程号未设置");
                return new Page(0,1,null);
            }
		} else {
            System.out.println("未知错误:TeacherController-->selectQuestion(..)");
            return new Page(0,1,null);
        }

		if(type.equals("choice")) {
			Choice choice = new Choice();
			choice.setCourseId(courseId);
            int totalRecord = choiceService.getTotalRecordByParam(choice);
			List<Choice> list = choiceService.getPageListByParam(choice, offset, rows);
            return new Page(totalRecord, rows, list);
		}
		if(type.equals("fillin")) {
			Fillin fillin = new Fillin();
			fillin.setCourseId(courseId);
            int totalRecord = fillinService.getTotalRecordByParam(fillin);
            List<Fillin> list = fillinService.getPageListByParam(fillin, offset, rows);
            return new Page(totalRecord, rows, list);
		}
		if(type.equals("essay")) {
            Essay essay = new Essay();
			essay.setCourseId(courseId);
            int totalRecord = essayService.getTotalRecordByParam(essay);
            List<Essay> list = essayService.getPageListByParam(essay, offset, rows);
            return new Page(totalRecord, rows, list);
		}
		if(type.equals("discuss")) {
			Discuss discuss = new Discuss();
			discuss.setCourseId(courseId);
            int totalRecord = discussService.getTotalRecordByParam(discuss);
            List<Discuss> list = discussService.getPageListByParam(discuss, offset, rows);
            return new Page(totalRecord, rows, list);
		}
        return new Page(0,1,null);
	}
	
	//添加题目进试卷中
	@RequestMapping("/joinIn")
	public @ResponseBody String joinIn(HttpSession session, String choiceId, String essayId, String fillinId, String discussId) {
		if(choiceId!=null) {
			List<String> choiceIds = (List<String>) session.getAttribute("choiceIds");
			if(choiceIds==null) {
				choiceIds = new ArrayList();
				choiceIds.add(choiceId);
			} else if(!choiceIds.contains(choiceId)){
				choiceIds.add(choiceId);
			}
			session.setAttribute("choiceIds", choiceIds);
		}
		if(essayId!=null) {
			List<String> essayIds = (List<String>) session.getAttribute("essayIds");
			if(essayIds==null) {
				essayIds = new ArrayList();
				essayIds.add(essayId);
			} else if(!essayIds.contains(essayId)){
				essayIds.add(essayId);
			}
			session.setAttribute("essayIds", essayIds);
		}
		if(fillinId!=null) {
			List<String> fillinIds = (List<String>) session.getAttribute("fillinIds");
			if(fillinIds==null) {
				fillinIds = new ArrayList();
				fillinIds.add(fillinId);
			} else if(!fillinIds.contains(fillinId)){
				fillinIds.add(fillinId);
			}
			session.setAttribute("fillinIds", fillinIds);
		}
		if(discussId!=null) {
			List<String> discussIds = (List<String>) session.getAttribute("discussIds");
			if(discussIds==null) {
				discussIds = new ArrayList();
				discussIds.add(discussId);
			} else if(!discussIds.contains(discussId)){
				discussIds.add(discussId);
			}
			session.setAttribute("discussIds", discussIds);
		}
		return null;
	}
	
	//从试卷中删除题目
	@RequestMapping("/deleteId")
	public @ResponseBody String deleteId(HttpSession session, String choiceId, String essayId, String fillinId, String discussId) {

		if(choiceId!=null) {
			List<String> choiceIds = (List<String>) session.getAttribute("choiceIds");
			if(choiceIds!=null && choiceIds.size()>0) {
				choiceIds.remove(choiceId);
				session.setAttribute("choiceIds", choiceIds);
			}
		}
		if(essayId!=null) {
			List<String> essayIds = (List<String>) session.getAttribute("essayIds");
			if(essayIds!=null && essayIds.size()>0) {
				essayIds.remove(essayId);
				session.setAttribute("essayIds", essayIds);
			}
		}
		if(fillinId!=null) {
			List<String> fillinIds = (List<String>) session.getAttribute("fillinIds");
			if(fillinIds!=null && fillinIds.size()>0) {
				fillinIds.remove(fillinId);
				session.setAttribute("fillinIds", fillinIds);
			}
		}
		if(discussId!=null) {
			List<String> discussIds = (List<String>) session.getAttribute("discussIds");
			if(discussIds!=null && discussIds.size()>0) {
				discussIds.remove(discussId);
				session.setAttribute("discussIds", discussIds);
			}
		}
		return null;
	}
	
	//获取已选题号，供前台js使用
	@RequestMapping("/getSelectQuestionIds")
	public @ResponseBody List<String> getSelectQuestionIds(HttpSession session, String type){
		Paper paper = (Paper) session.getAttribute("paper");
		//从数据库中获取已选的题号
		List<String> ids_1 = paperService.getQuestionIds(paper.getPaperId(), type);
		//从session中获取已选题号
		List<String> ids_2 = (List<String>) session.getAttribute(type+"Ids");
		
		//两个集合合并
		if(ids_1!=null && ids_2!=null){
            for(String id : ids_2) {
                if (!ids_1.contains(id))
                    ids_1.add(id);
            }
		} else if(ids_1==null) {
			/*来到此处，表示1、2至少有一个为空
			如果ids_1为空，则以ids_2值为准
			如果ids_1不为空，则以ids_1值为准
			如果都为空，则两者任选*/
			ids_1 = ids_2;
		}
		//把已选题号set到session中
		session.setAttribute(type+"Ids", ids_1);
		return ids_1;
	}

	/**
	 * 功能：清空某题型选择的题目记录
	 * @param type 题型
	 * @return Ajax中，标准JSON字符串执行success方法，非标准JSON字符串执行error方法
	 */
	@RequestMapping("/resetQuestion")
	public @ResponseBody String resetQuestion(HttpSession session, String type){
		try{
			List<String> ids = new ArrayList();
			session.setAttribute(type+"Ids", ids);
			return "{\"\":\"\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 功能：修改某题型的题目分值
	 * @param type 题型
	 * @return Ajax中，标准JSON字符串执行success方法，非标准JSON字符串执行error方法
	 */
	@RequestMapping("/changeQuestionScore")
	public @ResponseBody String changeQuestionScore(HttpSession session, String type, Integer score){
		try{
			Paper paper = (Paper) session.getAttribute("paper");
			if(type.equalsIgnoreCase("choice")){
				paper.setChoiceScore(score);
			}
			if(type.equalsIgnoreCase("essay")){
				paper.setEssayScore(score);
			}
			if(type.equalsIgnoreCase("discuss")){
				paper.setDiscussScore(score);
			}
			if(type.equalsIgnoreCase("fillin")){
				paper.setFillinScore(score);
			}
			session.setAttribute("paper", paper);
			return "{\"\":\"\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 功能：清空试卷选择的所有题型以及相关信息
	 * @return Ajax中，标准JSON字符串执行success方法，非标准JSON字符串执行error方法
	 */
	@RequestMapping("/distroyPaper")
	public @ResponseBody String distroyPaper(HttpSession session){
		session.removeAttribute("paper");
		session.removeAttribute("choiceIds");
		session.removeAttribute("essayIds");
		session.removeAttribute("fillinIds");
		session.removeAttribute("discussIds");
		return "{\"\":\"\"}";
	}
	
	/**
	 * 功能：提交试卷信息到数据库
	 * @return 提交一个标准的JSON字符串("{\"\":\"\"}")给ajax执行success方法，非标准JSON字符串("")会执行error方法，不过前提是dataType设置为"json"
	 */
	@RequestMapping("/submitPaper")
	public @ResponseBody String submitPaper(HttpSession session, HttpServletRequest request){
		try{
			//获取在session中的paper对象
			User user = (User) session.getAttribute("user");
			Paper paper = (Paper) session.getAttribute("paper");
			
			//设置试卷的信息：设置为未提交审核，更新最后一次修改的时间
			paper.setCreatetime(new Date());
			paper.setVetted(1);
			paper.setUserRealname(user.getRealname());
			String courseName = courseService.getCourseNameById(paper.getCourseId());
			paper.setCourseName(courseName);

			//检查分值
			if(paper.getChoiceScore()==null){ paper.setChoiceScore(0); }
			if(paper.getFillinScore()==null){ paper.setFillinScore(0); }
			if(paper.getEssayScore()==null){ paper.setEssayScore(0); }
			if(paper.getDiscussScore()==null){ paper.setDiscussScore(0); }
			
			//获得已选题号
			List<String> choiceIds = (List<String>) session.getAttribute("choiceIds");
			List<String> discussIds = (List<String>) session.getAttribute("discussIds");
			List<String> fillinIds = (List<String>) session.getAttribute("fillinIds");
			List<String> essayIds = (List<String>) session.getAttribute("essayIds");
			
			//检查试卷对象是否已经存在id，是：代表更新已有的试卷；否：表示增加试卷记录
			if(paper!=null && paper.getPaperId()!=null){
				paperService.updatePaper(paper, choiceIds, fillinIds, essayIds, discussIds);
			} else {
				//设置试卷编号
				paper.setPaperId(CommonUtils.uuid());
				paperService.addPaper(paper, choiceIds, fillinIds, essayIds, discussIds);			
			}
			
			//提交完成后，清空表现层的试卷对象
			session.removeAttribute("paper");
			session.removeAttribute("choiceIds");
			session.removeAttribute("essayIds");
			session.removeAttribute("fillinIds");
			session.removeAttribute("discussIds");
			
			//提交一个标准的JSON字符串给ajax执行success方法，非标准JSON字符串会执行error方法，不过前提是dataType设置为"json"
			return "{\"\":\"\"}";
		} catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
}