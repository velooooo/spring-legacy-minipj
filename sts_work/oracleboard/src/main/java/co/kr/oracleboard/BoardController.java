package co.kr.oracleboard;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.kr.oracleboard.dto.BoardDTO;

@Controller
public class BoardController {
	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	@RequestMapping("/list")//url에 사용할 이름
	public String list(Model model) {                             //네임스페이스.아이디
		List<HashMap<String, String>> list = sqlSession.selectList("board.listDao");
		model.addAttribute("list",list);
		//이동
		return "/list"; //views/list.jsp//문서의 실제 이름
	}
	//글작성 폼
	@RequestMapping("/writeForm")
	public String writeForm() {
		//이동
		return "/writeForm"; //views/writeForm.jsp
	}
	//글작성 저장
	@RequestMapping("writePro")
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		sqlSession.insert("board.writeDao",boardDTO);
		//이동
		return "redirect:list";//view/list.jsp
	}
	//글 내용 상세보기, 글 수정 폼
	@RequestMapping("contentView")
	public String contentView(HttpServletRequest request, Model model) {
		String bNum=request.getParameter("bNum");
		//조회수증가
		sqlSession.update("board.hitDao",bNum);
		//글 가져오기
		BoardDTO bdto=sqlSession.selectOne("board.contentDao",bNum);
		//JSP에서 사용할 데이터
		model.addAttribute("bdto",bdto);
		//이동
		return "/contentView";//views/contentView.jsp
	}
	//글 수정 - Pro - 프로세스
	@RequestMapping(value="updatePro",method=RequestMethod.POST)
	public String updatePro(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		//글수정
		System.out.println("bTitle:"+boardDTO.getbTitle());
		sqlSession.update("board.updateDao",boardDTO);
		//이동
		return "redirect:list";//views/list.jsp
	}
	//답글 폼
	@RequestMapping("replyForm")
	public String replyForm(HttpServletRequest request,Model model) {
		//
		BoardDTO bdto=sqlSession.selectOne("board.replyDaoForm",request.getParameter("bNum"));
		//모델
		model.addAttribute("bdto",bdto);
		//이동
		return "/replyForm";//views/replyForm.jsp
	}
	//답글 위치확보
	public void replyShape(Map map) {
		//
		sqlSession.update("board.replyPos",map);
	}
	//답글 쓰기(저장)
	@RequestMapping("replyPro")
	public String replyPro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request, Model model) {
		//Map에 넣을것들
		String bGroup=request.getParameter("bGroup");
		String bStep=request.getParameter("bStep");
		//map
		Map<String,String> map=new HashMap();
		//put
		map.put("bGroup", bGroup);
		map.put("bStep", bStep);
		//replyShape(Map map) mothod호출
		replyShape(map);//답글 위치확보 메서드 호출
		
		//답글작성
		sqlSession.insert("board.replyDao",boardDTO);
		//이동
		return "redirect:list";//views/list.jsp
	}
	//글 삭제
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest request,Model model) {
		//글 삭제
		sqlSession.delete("board.deleteDao",request.getParameter("bNum"));
		//이동
		return "redirect:list";//views/list.jsp
	}
	
}
