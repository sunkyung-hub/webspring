package spring_learning;

import org.springframework.stereotype.Repository;

//pw를 자동으로 변환시키는 모델
//사용자 비밀번호 및 게시판 글 등록시 사용 또는 비회원에 사용하는 md5
@Repository("md5_pass")
public class md5_pass {
	
	public String md5_make(String pw) {
		System.out.println(pw);
		
		return null;
	}
}
