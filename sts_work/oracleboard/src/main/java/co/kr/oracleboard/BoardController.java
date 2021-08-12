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
	
	//����Ʈ
	@RequestMapping("/list")//url�� ����� �̸�
	public String list(Model model) {                             //���ӽ����̽�.���̵�
		List<HashMap<String, String>> list = sqlSession.selectList("board.listDao");
		model.addAttribute("list",list);
		//�̵�
		return "/list"; //views/list.jsp//������ ���� �̸�
	}
	//���ۼ� ��
	@RequestMapping("/writeForm")
	public String writeForm() {
		//�̵�
		return "/writeForm"; //views/writeForm.jsp
	}
	//���ۼ� ����
	@RequestMapping("writePro")
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		sqlSession.insert("board.writeDao",boardDTO);
		//�̵�
		return "redirect:list";//view/list.jsp
	}
	//�� ���� �󼼺���, �� ���� ��
	@RequestMapping("contentView")
	public String contentView(HttpServletRequest request, Model model) {
		String bNum=request.getParameter("bNum");
		//��ȸ������
		sqlSession.update("board.hitDao",bNum);
		//�� ��������
		BoardDTO bdto=sqlSession.selectOne("board.contentDao",bNum);
		//JSP���� ����� ������
		model.addAttribute("bdto",bdto);
		//�̵�
		return "/contentView";//views/contentView.jsp
	}
	//�� ���� - Pro - ���μ���
	@RequestMapping(value="updatePro",method=RequestMethod.POST)
	public String updatePro(@ModelAttribute("boardDTO") BoardDTO boardDTO) {
		//�ۼ���
		System.out.println("bTitle:"+boardDTO.getbTitle());
		sqlSession.update("board.updateDao",boardDTO);
		//�̵�
		return "redirect:list";//views/list.jsp
	}
	//��� ��
	@RequestMapping("replyForm")
	public String replyForm(HttpServletRequest request,Model model) {
		//
		BoardDTO bdto=sqlSession.selectOne("board.replyDaoForm",request.getParameter("bNum"));
		//��
		model.addAttribute("bdto",bdto);
		//�̵�
		return "/replyForm";//views/replyForm.jsp
	}
	//��� ��ġȮ��
	public void replyShape(Map map) {
		//
		sqlSession.update("board.replyPos",map);
	}
	//��� ����(����)
	@RequestMapping("replyPro")
	public String replyPro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request, Model model) {
		//Map�� �����͵�
		String bGroup=request.getParameter("bGroup");
		String bStep=request.getParameter("bStep");
		//map
		Map<String,String> map=new HashMap();
		//put
		map.put("bGroup", bGroup);
		map.put("bStep", bStep);
		//replyShape(Map map) mothodȣ��
		replyShape(map);//��� ��ġȮ�� �޼��� ȣ��
		
		//����ۼ�
		sqlSession.insert("board.replyDao",boardDTO);
		//�̵�
		return "redirect:list";//views/list.jsp
	}
	//�� ����
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest request,Model model) {
		//�� ����
		sqlSession.delete("board.deleteDao",request.getParameter("bNum"));
		//�̵�
		return "redirect:list";//views/list.jsp
	}
	
}
