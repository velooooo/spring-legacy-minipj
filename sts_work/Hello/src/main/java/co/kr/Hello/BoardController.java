package co.kr.Hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.kr.Hello.dto.BoardDTO;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;// ����, setter �ڵ�����

	// �۾��� ��, ��۾���
	@RequestMapping("writeForm.do")
	public String writeForm(Model model, String num, String ref, String re_step, String re_level, String pageNum) {

		if (num == null) {// num�� ������ �� ����
			num = "0"; // �� ��ȣ
			ref = "1"; // �� �׷�
			re_step = "0"; // �� ����
			re_level = "0";// �� ����
		} // if-end
			// ���, ���� ��� �־���.
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num", new Integer(num));
		model.addAttribute("ref", new Integer(ref));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_level", new Integer(re_level));
		//
		return "board/writeForm";// View return writeForm.jsp
	}// writeForm-end

	// DB�� ����
	@RequestMapping(value = "writePro.do", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNum = 0;// �ִ� �� ��ȣ ���� ����

		if (sqlSession.selectOne("board.numMax") != null) {// �۹�ȣ�� ������
			// �� ��ȣ�� ���� -> ���� ����
			maxNum = sqlSession.selectOne("board.numMax");// �ִ� �۹�ȣ maxNum�� �Ҵ�
		}

		if (maxNum != 0) {// ���� �̹� ������
			maxNum = maxNum + 1;// ref �� �׷쿡 ����.
		} else {// ó�� ���̸�
			maxNum = 1;// ref �� �׷쿡 1�� ����.(1�� ��ȣ)
		}

		String ip = request.getRemoteAddr();// ip���
		boardDTO.setIp(ip);

		// ����, ���
		if (boardDTO.getNum() != 0) {// ����̸�
			// ��� �����ֱ� ��ġ Ȯ��
			sqlSession.update("board.reStep", boardDTO);
			boardDTO.setRe_step(boardDTO.getRe_step() + 1);// �� ���� + 1
			boardDTO.setRe_level(boardDTO.getRe_level() + 1);// �� ���� + 1
		} else {// �����̸�
			boardDTO.setRef(new Integer(maxNum));// �� �׷�
			boardDTO.setRe_step(new Integer(0));// �� ����
			boardDTO.setRe_level(new Integer(0));// �� ����
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:list.do";// redirect:list.jsp
	}// writePro-end

	// ����Ʈ
	@RequestMapping("list.do")
	public String listBoard(Model model, String pageNum) {
		if (pageNum == null) {// ������ ��ȣ ������
			pageNum = "1";
		}
		int pageSize = 10;// �������� �� 10���� ������
		int currentPage = Integer.parseInt(pageNum);// ���� ������

		int startRow = (currentPage - 1) * pageSize + 1;// �������� ù ��° �� ���� : (����������-1)*10+1
		int endRow = currentPage * pageSize;// �������� ������ ��
		// startRow 1 11 21
		// endRow 10 20 30
		int count = 0;// �� �� ���� ���� ����
		int pageBlock = 10;// ���� �� ��������ȣ 10��

		count = sqlSession.selectOne("board.countDao");// �� ����
		int number = count - (currentPage - 1) * pageSize;// �� ��ȣ (���� 37���� ���, 37 36 35 : �������� ����)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow - 1);// ���� ��ȣ
		map.put("cnt", pageSize);// �� ����

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);// �� ������ �� ���ϱ�
		// �� ���������ڵ� ������0 : ������ 1 ����
		//
		int startPage = (currentPage / pageBlock) * 10 + 1;// ���� ����������
		int endPage = startPage + pageBlock - 1;// ������ �� ������
		// --------------------------------------------------------------------
		List<BoardDTO> list = sqlSession.selectList("board.listDao", map);

		model.addAttribute("pageNum", pageNum);// ��������ȣ
		model.addAttribute("currentPage", currentPage);// ���� ������
		model.addAttribute("startRow", startRow);// �������� ù ��° ��
		model.addAttribute("endRow", endRow);// �������� ������ ��
		model.addAttribute("pageBlock", pageBlock);// ���� �� ��������ȣ 10��
		model.addAttribute("pageCount", pageCount);// �� ������ ��
		model.addAttribute("startPage", startPage);// ���� ����������
		model.addAttribute("endPage", endPage);// ������ �� ������
		model.addAttribute("count", count);// ��ü �� ����
		model.addAttribute("pageSize", pageSize);// �������� �� ����
		model.addAttribute("number", number);// �� ��ȣ
		model.addAttribute("list", list);//

		return "board/list"; // View return list.jsp
	}
	
	//�� ���� ����
	@RequestMapping("content.do")
	public String content(Model model, String num, String pageNum) {
		
		int num1 = Integer.parseInt(num);
		sqlSession.update("board.readcountDao", num1);//��ȸ������
		
		BoardDTO bdto=sqlSession.selectOne("board.getBoard", num1);

		String content=bdto.getContent();
		content=content.replace("\n", "<br/>");
		
		model.addAttribute("content", content);
		model.addAttribute("pageNum", pageNum);// ��������ȣ
		model.addAttribute("num", num1);
		model.addAttribute("bdto", bdto);
		
		return "board/content"; //view return content.jsp
	}
	
	//���� ��
	@RequestMapping("updateForm.do")
	public ModelAndView updateForm(String num, String pageNum) {
		int num1 = Integer.parseInt(num);
		BoardDTO bdto=sqlSession.selectOne("board.getBoard",num1);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("bdto", bdto);
		mv.setViewName("board/updateForm");//updateForm.jsp
		
		return mv;
	}
	
	//DB�� ����
	@RequestMapping(value="updatePro.do", method=RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNum) {
		sqlSession.update("board.updateDao", boardDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("refirect:list.do");//
		
		return mv;
	}
	
	//�� ����
	@RequestMapping("delete.do")
	public String delete(Model model, String num, String pageNum) {
		sqlSession.delete("board.deleteDao", new Integer(num));
		
		model.addAttribute("pageNum", pageNum);
		return "redirect:list.do";
	}
	
	
}// class-end