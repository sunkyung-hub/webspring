package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Spring Controller + View 기초

//@Controller란 해당 일반 class를 web에서 작동할 수 있는 컨트롤러로 변경하도록 함
@Controller
public class mainpage {
	
	PrintWriter pw = null;
	
	
	//@GetMapping = doGet, @PostMapping = doPost
	//@RequestMapping: doService(doGet, doPost를 모두 받음)

	//throws + HttpServletRequest + HttpServletResponse: Servlet 형태이지만, View를 사용하지 않음
	//View 필요 없음. abc.do그대로 작동됨
	//PrintWriter를 바로 사용
	@GetMapping("/abc.do") 
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html:charset=utf-8"); //설정해주지 않으면 글씨 다 깨짐
		this.pw = res.getWriter();
		this.pw.write("<script>"
				+ "alert('테스트 페이지 입니다.');"
				+ "</script>");
		this.pw.close();
//		System.out.println("abc페이지");
	}
	
	//View를 무조건 사용하는 메소드 - View로 전달, View 필요(ex. bbb.do실행시 bbb.jsp를 찾음)
	@PostMapping("/bbb.do") 
	public void bbb(HttpServletRequest req) {
		//Front에서 값이 넘어옴
		String pdnm = req.getParameter("pdnm");
		//View(bbb.jsp)로 값을 넘김
		req.setAttribute("pdnm", pdnm);
//		System.out.println("bbb페이지");
	}
	
	//return 형태의 메소드는 view 파일명을 다르게 사용할 수 있음
	//기본은 return null;(= do와 이름이 같은 jsp를 찾게 됨) 
	//return "";(=do와 다른 이름의 jsp를 찾게 됨)
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);
		return "/product_list"; //View를 사용할때 .jsp를 빼야함
	}
	
	//request로 view(jsp)에 전달하는 방식이 아님
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest req) {
		
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		//ModelAndView(Object): 배열같은 구조
		ModelAndView mv = new ModelAndView();
		//addObject: 키 배열 형태로 값을 저장
		mv.addObject("pdnm", pdnm);
		mv.addObject("pcode", pcode);
		mv.addObject("pmoney", pmoney);
		
		//setView: null은 Mapping이름과 동일한 jsp를 찾게됩니다.
		//mv.setView(null);
		
		//Mapping과 다른이름을 사용하고 싶을 경우
		mv.setViewName("bbb");
		
		return mv; //무조건 ModelAndView 객체명을 사용해야 함
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest req, Model m) {
		
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		//Model(interface)를 이용하여 JSP로 값을 전달(JSTL형태로 값 출력)
		m.addAttribute("pdnm", pdnm);
		m.addAttribute("pcode", pcode);
		m.addAttribute("pmoney", pmoney);

		return "/ddd";
	}
}
