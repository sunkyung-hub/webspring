package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Spring, Spring-boot(CORS)
@CrossOrigin(origins="*", allowedHeaders = "*")

//API 전용 Controller
@RestController
public class api_controller {

	PrintWriter pw = null; //Front-end가 값을 가져갈 수 있도록 하기 위해
	
	@Resource(name="macbook_member_DTO")
	macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	user_DAO dao;
	
	//원래는 throws Exception이 아닌 try-cathc문 넣어야 함
	@GetMapping("/api_data1.do")
	public String api_data(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8"); //언어셋
		this.pw = res.getWriter(); 
		/*
		 * JSONArray: []
		 * JSONObject: {} //key를 생성
		 * 
		 * [데이터 입력]
		 * org.json.simple: add 사용
		 * org.json: put 사용*/
/*		
//		["a","b","c","d"]
	JSONArray ja = new JSONArray(); //json 배열 생성
//		//배열에 값 넣어줌
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		this.pw.print(ja);*/
		
//		{"data":["a","b","c","d"]}
		JSONArray ja = new JSONArray(); //json 배열 생성
		//배열에 값 넣어줌
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		this.pw.print(jo);
		this.pw.close();
		
		return null;
	}
	
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8"); //언어셋
		this.pw = res.getWriter();
		
		//[["홍길동","강감찬"]]
		/*JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONArray ja2 = new JSONArray();
		ja2.put(ja);
		
		this.pw.print(ja2);
		*/

		//[{"member":["홍길동","강감찬"]}]
		/*JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		JSONArray ja2 = new JSONArray();
		ja2.put(jo);
		
		this.pw.print(ja2); //맨 마지막 JSON객체 출력 */
		
		this.pw.close();
		return null;
	}
	
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8"); //언어셋
		this.pw = res.getWriter();
		
		String db[] = {"hong", "홍길동", "hong@nate.com", "서울", "010-1234-5678"};
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]);
		
		JSONArray ja = new JSONArray();
		ja.put(jo);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		this.pw.print(jo2);
		
		this.pw.close();
		return null;
	}
	
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse res) throws Exception{
		
		//servlet에서 사용 - CORS
//		res.addHeader("Access-Control-Allow-Origin", "*");
//		res.addHeader("Access-Control-Allow-", "tCredentials", "true");
		
		
		res.setContentType("text/html;charset=utf-8"); //언어셋
		this.pw = res.getWriter();
	
		/*//내코드
		JSONArray ja = new JSONArray(); 
		ja.put("NEW");
		ja.put("BEST");
		ja.put("NEW");
		JSONObject jo = new JSONObject();
		jo.put("product_ico", ja);
		this.pw.print(jo);
		
		JSONArray ja2 = new JSONArray();
		ja2.put("모니터");
		ja2.put("키보드");
		ja2.put("마우스");
		JSONObject jo2 = new JSONObject(); 
		jo2.put("product_name", ja2);
		this.pw.print(jo2);
		
		JSONObject jo3 = new JSONObject();
		jo3.put("products", jo);*/
		
		//선생님 코드 
		String data[][] = {
				{"모니터", "키보드", "마우스"},
				{"NEW", "BEST", "NEW"}
		};
		
		//value 반복문
		int w = 0;
		String keyname=""; //서브키 
		
		JSONObject alldata = new JSONObject(); //대표키
		JSONObject jb = new JSONObject(); //보조키
		while(w < data.length){
			
			JSONArray jo = new JSONArray(); //데이터 배열 만듦[] 
			for(int f=0;f<data[w].length;f++){
				//System.out.println(data[w][f]);
				jo.put(data[w][f]); //데이터 배열 생성
			}
			//key 생성
			//데이터 배열에 맞는 보조키를 적용하기 위함
			if(w==0){
				keyname = "product_name";
			}
			else {
				keyname = "product_ico";
			}
			jb.put(keyname, jo);
			w++;
		}
//			System.out.println(jb); //대표키 생성은 최종 반복문 다음에 코드를 작성
		this.pw.print(alldata); //Front-end가 출력
		this.pw.close();
		return null;
	}
	
	//MySQL DB를 가져와서 API로 생성
	@GetMapping("/api_data5.do")
	public String api_data5(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8"); //언어셋
		this.pw = res.getWriter();
		
		List<macbook_member_DTO> result = this.dao.all_list();

		//원시배열 형태가 없기 때문에 반복문을 돌며 초기화되고 누적이 되지 않음 -> 배열을 넣어주어야 함
		JSONObject alldata = new JSONObject();
		JSONArray ja = new JSONArray();
//		System.out.println(result);
		int w = 0;
		while(w<result.size()) {
			JSONObject jo = new JSONObject();
			jo.put("midx", result.get(w).midx);
			jo.put("mid", result.get(w).mid);
			jo.put("mname", result.get(w).mname);
			jo.put("memail", result.get(w).memail);
			ja.put(jo);
			w++;
		}
		alldata.put("member", ja);
		this.pw.print(alldata);
		this.pw.close();
		return null;
	}
}
