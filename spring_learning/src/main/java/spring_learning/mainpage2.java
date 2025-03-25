package spring_learning;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import org.apche.

@Controller
public class mainpage2 {
	//WEB-INF: Controller, Model이 접근할 수 있는 디렉토리
	//return 사용시 WEB-INF/디렉토리명/파일명 형태로 구성하게 됨
	
	/*DTO로 Front-end의 값을 받을 수 있음.(lombok library덕에)
	 *별도의 값을 받아서 처리해야 할 경우는 Servlet 형태의 request로 받으면 됨
	 ****Front의 name값과 동일하게 DTO가 작성되어야 함 
	 *DTO활용: Front-end값 이관, Model에 값을 이관, DB에서 사용함*/
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest res, Model m) {
		String ck = res.getParameter("mcheck");
		System.out.println(ck);
		//Model로 해당 jsp에 변수를 이관함, 출력은 jstl 변수선언으로 출력

//		System.out.println(dto.getMid());
//		System.out.println(dto.getMpass());
//		System.out.println(dto.getMemail());
		m.addAttribute("mid", dto.getMid());
		return "WEB-INF/view/login"; //경로를 이렇게 잡아놨기 때문에 lostid.html은 값이 안날아가는게 맞음
	}
	
	@Autowired 
	//java에서 사용하는 class, interface의 값을 xml에 있는 id 기준값으로 대체하는 형태(의존성 주입)
	BasicDataSource dbinfo;
	
	//DB Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//DB + XML + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest res) {
		
		try {
			//db_config.xml에 있는 정보를 Connection으로 이관
			this.con = this.dbinfo.getConnection();
			
			System.out.println(this.con);
			String sql = "select * from event order by eidx desc";
			this.ps = this.con.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			res.setAttribute("rs", this.rs); //ResultSet을 JSP로 전송
			//단점: this.ps, this.rs를 close하지 못함 -> close시 view 작동 안함
		}
		catch(Exception e) {
			
		}
		return null;
	}
	
	/*RequestMapping은 get, post를 둘 다 받을 수 있으나, 보안이 약하다는 단점이 있기 때문에 
	 * 사용하기 위해서는 value="/event_infook.do", method=RequestMethod.POST 이렇게 사용하면 된다
	 * 아래의 작성한 것은 POST만 받겠다는 의미*/
	//RequestMapping: GET, POST, PUT... 모든 통신을 다 받을 수 있음(기본) -> 보안 약함
	/* value 속성: 가상의 경로(=do)
	 * method 속성: 통신방법(=데이터 전달방법)*/
	
//	static Logger log = org.mybatis.logging.Logger.get //model에서 사용
	@RequestMapping(value="/event_infook.do", method=RequestMethod.POST)
	public String eventok(event_DTO dto) {
//		System.out.println(dto.getEmail());
//		System.out.println(dto.getEtel());
		
		try {
			this.con = this.dbinfo.getConnection();
			String sql = "insert into event values('0', ?, ?, ?, ?, ?, ?, now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname()); //dto에 선언한 것을 가져옴 -> 값 들어감
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
		}
		catch(Exception e) {
			
		}
		finally {
			try {
				this.ps.close();
			}
			catch(Exception e) {
				
			}
		}
		
		return null;
	}
}
