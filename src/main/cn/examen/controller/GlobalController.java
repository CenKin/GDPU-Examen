package cn.examen.controller;


import cn.examen.domain.paper.Paper;
import cn.examen.domain.user.User;
import cn.examen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GlobalController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpSession session, String username, String password) {
        User user = userService.login(username, password);
		if(user==null) {
			request.setAttribute("message", "登录失败，请检查用户名或密码");
			return "forward:/index.jsp";
		}
		session.setAttribute("user", user);
		
		//检查是否是管理员登录，是就跳转到后台管理页面
		if(user.getUserType()==1){
			return "admin/index2";
		}
		return "forward:/index.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/toGetPapers")
	public String toGetPapers(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user!=null && user.getUserType()!=null){
			Integer identity = user.getUserType();
			if(identity.intValue()==3){
				return "manager/paperPageForManager";
			} else {
				return "teacher/paperPageForTeacher";
			}
		} else return "forward:/error.jsp";	
	}
	
	@RequestMapping("/toPaperInfo")
	public String toPaperInfo(){
        return "teacher/paperInfo";
	}
	
	@RequestMapping("/toPaperDetail")
	public String toPaperDetail(){
		return "teacher/paperDetail";
	}
	
	@RequestMapping("/toVettedPapers")
	public String toVettedPapers(){
		return "manager/vettedPaper";
	}
	
	/**
	 * 功能：转发到选题界面
	 * @param type
	 * @return
	 */
	@RequestMapping("/toGetQuestionPage")
	public String toGetQuestionPage(HttpServletRequest request, String type) {

		HttpSession session = request.getSession();
		Paper paper = (Paper) session.getAttribute("paper");
		String str = request.getParameter(type+"Score");
		Integer score = Integer.parseInt(str);
		
		if(type.equalsIgnoreCase("choice")) { paper.setChoiceScore(score); }
		if(type.equalsIgnoreCase("discuss")) { paper.setDiscussScore(score); }
		if(type.equalsIgnoreCase("essay")) { paper.setEssayScore(score); }
		if(type.equalsIgnoreCase("fillin")) { paper.setFillinScore(score); }
			
		session.setAttribute("paper", paper);
		return "teacher/"+type+"Page";
	}

}
