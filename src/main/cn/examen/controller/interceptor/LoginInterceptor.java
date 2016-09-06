package cn.examen.controller.interceptor;


import cn.examen.domain.user.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取请求的url地址
		String url = request.getRequestURL().toString();
		
		//判断地址是否是首页（即公开地址），是则放行
		if(url.contains("index") || url.contains("login.action")){
			return true;
		}
		
		//否则检查session中是否有用户登录信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null){
			return true;
		}
		
		//如果用户不存在，则跳回首页（登陆界面）
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		return false;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
