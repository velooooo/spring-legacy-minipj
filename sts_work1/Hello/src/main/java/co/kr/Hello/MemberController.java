package co.kr.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;//Mybatis
import java.util.*;//HeshMap사용할 예정
import co.kr.Hello.dto.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.naming.NamingException;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession; // 클래스는 대문자 변수는 소문자 시작

	@RequestMapping("main")
	public String mm() {
		return "main";// view return // views/main.jsp
	}

	// 회원가입FORM
	@RequestMapping("insertForm")
	public String insertForm() {
		return "/member/insertForm";// views/member/insertForm.jsp
	}

	// 아이디 중복체크
	@RequestMapping(value = "confirmID", method = RequestMethod.POST)
	public String idCheck(String id, Model model) {
		int check = -1;
		MemberDTO memberDTO = sqlSession.selectOne("member.selectOne", id);
		if (memberDTO == null) {
			check = 1;// 사용가능한 아이디
		} else {
			check = -1;// 사용중인 아이디
		}
		model.addAttribute("check", check);
		return "member/confirmID";// view return confirmID.jsp
	}

	// 회원가입
	@RequestMapping(value = "insertPro", method = RequestMethod.POST)
	public String insertPro(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request)
			throws IOException, NamingException {
		// email받기
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + email2;// 합치기
		memberDTO.setEmail(email);// set

		// tel받기
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String tel = tel1 + tel2 + tel3;// 합치기
		memberDTO.setTel(tel);// set

		// sql
		sqlSession.insert("member.insertMember", memberDTO); // namespace.id, DTO Parameter

		return "main";
	}

	// Login Form
	@RequestMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";// views/member/loginForm.jsp : View return
	}

	// Login 처리 로직
	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String loginPro(String id, String pw, Model model) {
		// Map, HashMap 사동가능
		HashMap<String, String> map = new HashMap<String, String>();// 객체생성
		map.put("id", id);
		map.put("pw", pw);

		MemberDTO mdto = sqlSession.selectOne("member.selectLogin", map);

		if (mdto == null) {// 로그인 실패시
			model.addAttribute("msg", "로그인 실패");
			return "member/loginForm";// loginForm.jsp View return
		}

		// 로그인 성공시
		model.addAttribute("mdto", mdto);
		return "member/loginSucess";// loginSucess.jsp View return
	}

	// LogOut
	@RequestMapping("logOut")
	public String logOut() {
		return "member/logOut";// logOut.jsp View return
	}

	// 내 정보 수정 폼
	@RequestMapping(value = "memUpdateForm", method = RequestMethod.POST)
	public String updateForm(String id, Model model) {
		MemberDTO mdto = sqlSession.selectOne("member.selectOne", id);
		// email 나누기
		// ex)emailaddr@naver.com
		String email = mdto.getEmail();// email가져와서
		int idx = email.indexOf("@");// @위치를 찾아서
		String email1 = email.substring(0, idx);// 처음부터 idx까지(emailaddr)
		String email2 = email.substring(idx);// idx부터 끝까지(@naver.com)

		model.addAttribute("email1", email1);
		model.addAttribute("email2", email2);

		// tel 나누기
		// ex)01012345678
		String tel = mdto.getTel();// tel가져와서
		String tel1 = tel.substring(0, 3);// 010(3 직전까지)
		String tel2 = tel.substring(3, 7);// 1234(7 직전까지)
		String tel3 = tel.substring(7);// 5678(7부터 끝까지)

		model.addAttribute("tel1", tel1);
		model.addAttribute("tel2", tel2);
		model.addAttribute("tel3", tel3);

		model.addAttribute("mdto", mdto);

		return "member/updateForm"; // updateForm.jsp View return
	}

	// 내 정보 수정 DB처리
	@RequestMapping(value = "memIpdatePro", method = RequestMethod.POST)
	public String memIpdatePro(@ModelAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request)
			throws IOException, NamingException {

		//email1, email2 를 email로 합치기
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1 + email2;//합치기
		memberDTO.setEmail(email);//set
		
		//tel들을 받아서 하나로 합치기
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String tel = tel1+tel2+tel3;//합치기
		memberDTO.setTel(tel);//set
		
		sqlSession.update("member.memberUpdate", memberDTO);
		
		return "main"; // main.jsp View return
	}
	
	//회원탈퇴
	@RequestMapping("deleteMember")
	public String deleteMember(String id) {
		sqlSession.delete("member.memberDelete", id);
		
		return "main";//main.jsp View return
	}

}// class-end
