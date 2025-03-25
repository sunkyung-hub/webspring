package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class macbook_controller {
	
	@Resource(name="macbook_member_DTO")
	public macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	public user_DAO dao;
	
	//전체 데이터 리스트 가져오기(회원정보)
	@GetMapping("/macbook_user.do")
	public String macbook_user() {
		System.out.println("test");
		
		List<macbook_member_DTO> all = this.dao.all_list();
		System.out.println(all.get(0).memail);
		
		return null;
	}
	
	//아이디 찾기 (체크박스: 체크된 경우Y, 체크가 안된경우 N
	/*@RequestParam: DTO에 없는 name을 처리할 때 주로 사용을 합니다.
	 *defaultValue 속성: null name값이 전송되었을 경우 발동 되는 속성
	 *required(true): 필수로 무조건 name을 처리하게 함
	 *required(false): name값을 Front-end에서 보내지 않아도 처리가 되도록 설정*/
	@PostMapping("/idsearch.do")
	public String idsearch(Model m, macbook_member_DTO dto, 
			@RequestParam(defaultValue = "N", required = false) String mcheck ) {
//		System.out.println(mcheck);
//		System.out.println(dto.memail);
		
		macbook_member_DTO data = this.dao.user_search(dto.mname, dto.memail);
//		System.out.println(data);
		String msg = ""; //결과 관련 사항
		if(data == null) {
			msg = "찾고자 하는 아이디가 확인되지 않습니다.";
		}
		else {
			msg = data.mid;
		}
		m.addAttribute("msg", msg);
		
		return "/WEB-INF/info/idsearch";
	}
	
	//MD5로 데이터를 변환하는 형태의 Controller
	@GetMapping("/macbook_login.do")
	public String macbook_login() {
		String pw = "a123456";
		
		
		return null;
	}
}
