package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class test implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = new ModelAndView(); 
		
		
		String search = request.getParameter("search");
		System.out.println(search);
		System.out.println("연습 spring Controller");

		//같은 위치에 있기 때문에 ./떼고 파일명만 작성해도 됨
		mv.setViewName("./search.jsp");
//		mv.setViewName("test.html"); //load안됨
		
		return mv;
	}
}
