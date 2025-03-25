package etc_model;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

//pw를 자동으로 변환시키는 모델
//사용자 비밀번호 및 게시판 글 등록시 사용 또는 비회원에 사용하는 md5
@Repository("md5_pass")
public class md5_pass { 
/*같은 패키지에 있을 때는 abstract만 사용해도 되지만,
 * 서로 다른 패키지에 있을 때는 public을 붙여서 사용해야 함*/
//public abstract class md5_pass{
	
	public String md5_make(String pw) {
//		System.out.println(pw);
	
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(pw.getBytes());
			for(byte b : md.digest()) {
				sb.append(String.format("%x", b));
			}
		}
		catch(Exception e) {
			sb.append("null");
		}
		return sb.toString();
	}
}
