//macbook.java
package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook {
	/*@Autowired, @Inject: 의존성 주입
	 * xml에서 java로 사용할 수 있고, java에 있는 값을 xml로 전달할 수 있음*/
	
	//ibatis로 연결
//	@Inject
//	SqlSessionFactory sqlfact;
	
	/*@Resource: new 클래스명 호출과 동일하게 작동을 하며, repository의 이름(값)을 가져옴
	 * repository와 resource의 name은 같아야 함 
	 * DTO에 Repository를 넣고, Controller에서 Resource로 받으면 값을 바로 넣을 수 있음*/
	@Resource(name="macbook_DAO")  
	private macbook_DAO dao;
	
	@Resource(name="macbook_DTO")  
	public macbook_DTO macbook_DTO;
	
	PrintWriter pw = null;
	
	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		//List<macbook_DTO>: DTO 형태의 배열로 생성하여, JSP로 전달
		List<macbook_DTO> classList = this.dao.macbook_select(); 
//		System.out.println(classList.get(0).class_name);
		m.addAttribute("ea", classList.size());
		m.addAttribute("classList", classList);
		return null;
	}
	
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			int result = this.dao.macbook_insert(dto);
//			System.out.println(result);
			
			String msg = "";
			if(result > 0) {
				msg = "alert('과정 개설이 올바르게 생성되었습니다.');"
						+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg", msg); //jsp에서 js메시지가 출력됨
		}
		catch(Exception e) {
		}
		finally {
		}
		
//		System.out.println(this.sqlfact);
		
		return "load";
	}
	
	//과정 수정 페이지
	@PostMapping("/macbook_modify.do")
	public String macbook_modify(@RequestParam("midx") String midx, Model m) {
		//System.out.println(midx);
		//DTO의 setter에 값을 이관한 상황
		macbook_DTO onedata = this.dao.macbook_one(midx);
//		System.out.println(onedata.get(0).class_name); //DTO의 getter 메소드를 호출
//		System.out.println(onedata.class_name);
		m.addAttribute("onedata", onedata); //JSTL로 값을 이관함
		return null;
	}
	
	//과정 내용을 수정 처리하는 메소드
	@PostMapping("/macbook_modifyok.do")
	public String macbook_modifyok(macbook_DTO dto, Model m) {
		
		int result = this.dao.macbook_update(dto);
		System.out.println(result);
		String msg = "";
		if(result>0) {
			msg = "alert('정상적으로 데이터가 수정되었습니다.');"
					+ "location.href='./macbook_list.do';";
		}
		
//		int result = this.dao.macbook_update(dto);
//		System.out.println(result);
		
		return "load";
	}
	
	//과정 내용을 삭제처리하는 메소드
	//Model, HttpServletResponse은 함께 사용하지 못함. 두 개의 인터페이스 역할이 같으므로, 하나만 사용이 가능함
	@PostMapping("/macbook_delete.do")
	public String macbook_delete(@RequestParam("midx") String midx, Model m, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=utf-8");
		
		this.pw = res.getWriter();
		int result = this.dao.macbook_delete(Integer.parseInt(midx));
		if(result > 0) {
			this.pw.print("<script>"
					+ "alert('올바르게 해당 과정을 삭제하였습니다.');"
					+ "location.href='./macbook_list.do';"
					+ "</script>");
		}
		this.pw.close();
		
		return null;
		
	}
}
