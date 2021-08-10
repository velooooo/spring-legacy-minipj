package co.kr.memboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;//Mybatis
import java.util.*;//HashMap
import co.kr.memboard.dto.MemberDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.naming.NamingException;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;//����
	
	
	@RequestMapping("main.do")
	public String mm() {
		//return "main";//�� ����  //views/main.jsp
		return ".main.layout";// View return
	}
	
	//ȸ��������
	@RequestMapping("insertForm.do")
	public String insertForm() {
		//return "/member/insertForm";//views/member/insertForm.jsp
		return ".main.member.insertForm";// View return
	}
	
	//id�ߺ�üũ
	@RequestMapping(value="confirmID" ,method=RequestMethod.POST)
	public String idCheck(String id,Model model) {
		int check=-1;
		MemberDTO memberDTO=sqlSession.selectOne("member.selectOne",id);
		if(memberDTO==null) {
			check=1;//��밡���� id
		}else {
			check=-1;//��� ���� id
		}
		
		model.addAttribute("check",check);
		return "member/confirmID";//�� ����  confirmID.jsp
		//json�κ��̶� �ٲ��ʿ����
	}
	
	//ȸ������
	@RequestMapping(value="insertPro.do",method=RequestMethod.POST)
	public String insertPro(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request) throws IOException,NamingException{
		
		String email1=request.getParameter("email1");
		String email2=request.getParameter("email2");
		String email=email1+email2;
		memberDTO.setEmail(email);
		
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String tel3=request.getParameter("tel3");
		String tel=tel1+tel2+tel3;
		memberDTO.setTel(tel);
		
		sqlSession.insert("member.insertMember",memberDTO);
 		//return "main";//�� ����
 		return ".main.layout";//View return
	}
	
	//�α��� ��
	@RequestMapping("loginForm.do")
	public String loginForm() {
		//return "member/loginForm";//views/member/loginForm.jsp  �並 ����
		return ".main.member.loginForm";
	}
	
	//�α��� ó�� ����
	@RequestMapping(value="loginPro.do",method=RequestMethod.POST)
	public String loginPro(String id,String pw,Model model) {
		//Map, HashMap
		HashMap<String, String> map=new HashMap<String,String>();//��ü����
		map.put("id", id);
		map.put("pw", pw);
		
		MemberDTO mdto=sqlSession.selectOne("member.selectLogin",map);
		
		if(mdto==null) {//�α��� ����
			model.addAttribute("msg","�α��ν���");
			//return "/member/loginForm";//�� ���� loginForm.jsp
			return ".main.member.loginForm";//View return
		}//if-end
		
		//�α��� ����
		model.addAttribute("mdto",mdto);
		//return "member/loginSucess";//loginSucess.jsp �丮��
		return ".main.member.loginSucess";//View Return
	}
	
	//�α׾ƿ�
	@RequestMapping("logOut.do")
	public String logOut() {
		//return "/member/logOut";//�� ���� logOut.jsp
		return ".main.member.logOut";//View Return
	}
	
	//�� ���� ���� ��*******
	@RequestMapping(value="memUpdateForm.do",method=RequestMethod.POST)
	public String updateForm(String id,Model model) {
		
		MemberDTO mdto=sqlSession.selectOne("member.selectOne",id);
		
		//ppp@daum.net
		String email=mdto.getEmail();
		int idx=email.indexOf("@");
		String email1=email.substring(0,idx);//ppp
		String email2=email.substring(idx);//@daum.net
		
		model.addAttribute("email1",email1);
		model.addAttribute("email2",email2);
		
		//01023236767
		//0123456789012 : index
		String tel=mdto.getTel();
		String tel1=tel.substring(0,3);//010
		String tel2=tel.substring(3,7);//2323
		String tel3=tel.substring(7);//6767
		
		model.addAttribute("tel1",tel1);
		model.addAttribute("tel2",tel2);
		model.addAttribute("tel3",tel3);
		
		model.addAttribute("mdto",mdto);
		
		//return "/member/updateForm";//�丮��     updateForm.jsp
		return ".main.member.updateForm";//View Return
	}
	
	//�� ���� ���� DBó�� 
	@RequestMapping(value="memUpdatePro.do",method=RequestMethod.POST)
	public String memIpdatePro(@ModelAttribute("memberDTO") MemberDTO memberDTO,
			HttpServletRequest request) throws IOException,NamingException{
		
		String email1=request.getParameter("email1");
		String email2=request.getParameter("email2");
		String email=email1+email2;
		memberDTO.setEmail(email);
		
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String tel3=request.getParameter("tel3");
		String tel=tel1+tel2+tel3;
		memberDTO.setTel(tel);
		
		sqlSession.update("member.memberUpdate",memberDTO);
		
		//return "main";//�� ����
		return ".main.layout";//View Return
	}
	
	//ȸ�� Ż��
	@RequestMapping("deleteMember.do")
	public String deleteMember(String id) {
		sqlSession.delete("member.memberDelete",id);
		//return "main";//�� ����
		return ".main.layout";//View Return
	}
}//class-end
