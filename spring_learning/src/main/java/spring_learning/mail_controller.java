package spring_learning;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mail_controller {

		
	@PostMapping("/contactusok.do")
	public String contatctusok(@RequestParam String subject, 
			@RequestParam String mname,
			@RequestParam String memail,
			@RequestParam String mtext)throws Exception {
		System.out.println("TEST");
		
		//Map -> Properties(.ini) : 환경설정 파일 형태의 배열
		//smtp, imap, pop
		Properties props = new Properties(); //배열 형태의 구성을 가짐, 데이터를 파일로 읽어들임
		props.put("mail.smtp.host", "smtp.mail.nate.com"); //메일 발송서버
		props.put("mail.smtp.port", "587"); //매일 발송 포트 번호
		props.put("mail.smtp.auth", "true"); //매일 발송 서버의인증(아이디, 패스워드) 
		props.put("mail.smtp.starttls.enable", "true"); //메일서버의 TLS를 연결
		props.put("mail.smtp.ssl.trust", "smtp.mail.nate.com"); //매일 서버의 ssl 기능 사용
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		
		//메일 발송에 대한 로그인(메일서버 로그인 정보) 사항을 Session등록 시킴
		Session session = Session.getInstance(props, new Authenticator() {
//		Session session = Session.getDefaultInstance(props, new Authenticator()	
		//로그인할 ID와 패스워드 정보를 입력
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("magic000716@nate.com", "gozldgkwlak18!");
				}
		});
		
		//Mime: 이메일 전송을 위한 인터넷 표준 포맷
		try { //메일 내용을 발송하는 상황
			Message msg = new MimeMessage(session);
			System.out.println(subject);
			System.out.println(mtext);
			System.out.println(mname);
			//보내는 사람 메일 주소 + 보낸이
			msg.setFrom(new InternetAddress("magic000716@nate.com",mname,"utf-8"));
			//받는 사람
			msg.addRecipient(Message.RecipientType.TO,new InternetAddress("magic000716@nate.com"));
			msg.setSubject(subject); //메일 제목
			msg.setContent(mtext, "text/html;charset=utf-8"); //메일 내용
			Transport.send(msg); //메일 발송에 대한 메소드
			
		}
		catch(Exception e) { //메일 발송에 대한 서버 접근 오류 발생시 출력 코드
			System.out.println(e);
			e.printStackTrace();
		}
		
		return null;
	}
}
