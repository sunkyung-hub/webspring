package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {

	List<String> listdata = null; //DAO배열
	Map<String, String> mapdata = null; //DTO
	PrintWriter pw = null; //출력
	String result = null; //결과값
	int callback = 0;
	ModelAndView mv = null; //모델
	
	@Resource(name="banner_DTO")
	banner_DTO dto; //new랑 마찬가지
	
	@Resource(name="banner_DAO")
	banner_DAO dao;
	
	@Resource(name="file_rename")
	file_rename fname; 
	
	//field에 있는 dto와 매개변수에 있는 dto는 다른 형태를 가지고 있습니다. 
	//this.dto -> field에 있는 dto 지칭, dto -> 매개변수에 있는 dto
	//ModelAttribute: 1:1매칭 -> name과 DTO 자료형 변수가 같은것 있으면 무조건 값을 Setter발동
	
	@PostMapping("/banner/bannerok")
//	public String bannerok(@RequestParam(name="dto", required=false) banner_DTO dto){
	public String bannerok(@ModelAttribute(name="dto") banner_DTO dto, 
			MultipartFile bfile, HttpServletRequest req) throws Exception {	
//	System.out.println("b.name");
	System.out.println(dto.getBname());
//	System.out.println(bfile.getOriginalFilename());
		
		String file_new = null;
		
		if(bfile.getSize() > 0) { //파일 용량으로 체크
//		if(bfile.getOriginalFilename()!="") { //파일명으로 체크
//			System.out.println(url);
		
			file_new = this.fname.rename(bfile.getOriginalFilename());
			//웹 디렉토리 개발자가 생성한 파일명으로 저장하는 코드
			String url = req.getServletContext().getRealPath("/upload/");
			FileCopyUtils.copy(bfile.getBytes(), new File(url+file_new));
			
			dto.setFile_url("/upload/" + file_new); //웹 디렉토리 경로 및 파일명
			dto.setFile_new(file_new); //개발자가 원하는 형태로 파일명을 변경
			dto.setFile_ori(bfile.getOriginalFilename()); //사용자가 적용한 파일명
		}
		this.callback = this.dao.new_banner(dto);
		System.out.println(this.callback);
	return null;
	}
	
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m) {
		List<banner_DTO> all = this.dao.all_banner();
		m.addAttribute("all", all);
		return null;
	}
}
