package co.kr.test;

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

import co.kr.test.dto.BoardDTO;

@Controller
public class BoardController {

	@Autowired
	private SqlSession sqlSession;// ����, setter �ڵ�����

	// �۾��� ��, ��۾���
	@RequestMapping("writeForm")
	public String writeForm(Model model, String no, String ref, String re_step, String re_level, String pageNo) {

		if (no == null) {// no�� ������ �� ����
			no = "0"; // �� ��ȣ
			ref = "1"; // �� �׷�
			re_step = "0"; // �� ����
			re_level = "0";// �� ����
		} // if-end
			// ���, ���� ��� �־���.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		model.addAttribute("ref", new Integer(ref));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_level", new Integer(re_level));
		//
		return "board/writeForm";// View return writeForm.jsp
	}// writeForm-end

	// DB�� ����
	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNo = 0;// �ִ� �� ��ȣ ���� ����

		if (sqlSession.selectOne("board.noMax") != null) {// �۹�ȣ�� ������
			// �� ��ȣ�� ���� -> ���� ����
			maxNo = sqlSession.selectOne("board.noMax");// �ִ� �۹�ȣ maxno�� �Ҵ�
		}

		if (maxNo != 0) {// ���� �̹� ������
			maxNo = maxNo + 1;// ref �� �׷쿡 ����.
		} else {// ó�� ���̸�
			maxNo = 1;// ref �� �׷쿡 1�� ����.(1�� ��ȣ)
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:list";// redirect:list.jsp
	}// writePro-end

	// ����Ʈ
	@RequestMapping("list")
	public String listBoard(Model model, String pageNo) {
		if (pageNo == null) {// ������ ��ȣ ������
			pageNo = "1";
		}
		int pageSize = 10;// �������� �� 10���� ������
		int currentPage = Integer.parseInt(pageNo);// ���� ������

		int startRow = (currentPage - 1) * pageSize + 1;// �������� ù ��° �� ���� : (����������-1)*10+1
		int endRow = currentPage * pageSize;// �������� ������ ��
		// startRow 1 11 21
		// endRow 10 20 30
		int count = 0;// �� �� ���� ���� ����
		int pageBlock = 10;// �� �� ��������ȣ 10��

		count = sqlSession.selectOne("board.countDao");// �� ����
		int number = count - (currentPage - 1) * pageSize;// �� ��ȣ (���� 37���� ���, 37 36 35 : �������� ����)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow - 1);// ���� ��ȣ
		map.put("cnt", pageSize);// �� ����

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);// �� ������ �� ���ϱ�
		// �� ���������ڵ� ������0 : ������ 1 ����
		//
		int startPage = (currentPage / pageBlock) * 10 + 1;// �� ����������
		int endPage = startPage + pageBlock - 1;// ���� �� ������
		// --------------------------------------------------------------------
		List<BoardDTO> list = sqlSession.selectList("board.listDao", map);

		model.addAttribute("pageNo", pageNo);// ��������ȣ
		model.addAttribute("currentPage", currentPage);// ���� ������
		model.addAttribute("startRow", startRow);// �������� ù ��° ��
		model.addAttribute("endRow", endRow);// �������� ������ ��
		model.addAttribute("pageBlock", pageBlock);// �� �� ��������ȣ 10��
		model.addAttribute("pageCount", pageCount);// �� ������ ��
		model.addAttribute("startPage", startPage);// �� ����������
		model.addAttribute("endPage", endPage);// ���� �� ������
		model.addAttribute("count", count);// ��ü �� ����
		model.addAttribute("pageSize", pageSize);// �������� �� ����
		model.addAttribute("number", number);// �� ��ȣ
		model.addAttribute("list", list);//

		return "board/list"; // View return list.jsp
	}
	
	//�� ���� ����
	@RequestMapping("content")
	public String content(Model model, String no, String pageNo) {
		
		int no1 = Integer.parseInt(no);
		sqlSession.update("board.hitDao", no1);//��ȸ������
		
		BoardDTO bdto=sqlSession.selectOne("board.getBoard", no1);

		String content=bdto.getContent();
		content=content.replace("\n", "<br/>");
		
		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// ��������ȣ
		model.addAttribute("no", no1);
		model.addAttribute("bdto", bdto);
		
		return "board/content"; //view return content.jsp
	}
	
	//���� ��
	@RequestMapping("updateForm")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		BoardDTO bdto=sqlSession.selectOne("board.getBoard",no1);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("bdto", bdto);
		mv.setViewName("board/updateForm");//updateForm.jsp
		
		return mv;
	}
	
	//DB�� ����
	@RequestMapping(value="updatePro", method=RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNo) {
		sqlSession.update("board.updateDao", boardDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:list");//
		
		return mv;
	}
	
	//�� ����
	@RequestMapping("delete")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("board.deleteDao", new Integer(no));
		
		model.addAttribute("pageNo", pageNo);
		return "redirect:list";
	}
	
}
