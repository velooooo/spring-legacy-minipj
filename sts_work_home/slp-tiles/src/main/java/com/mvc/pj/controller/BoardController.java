package com.mvc.pj.controller;

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

import com.mvc.pj.domain.BoardDTO;

@Controller
public class BoardController {
	@Autowired
	private SqlSession sqlSession;// ����, setter �ڵ�����

	// �۾��� ��, ��۾���
	@RequestMapping("writeForm")
	public String writeForm(Model model, String num, String re_group, String re_step, String re_depth, String pageNum) {

		if (num == null) {// num�� ������ �� ����
			num = "0"; // �� ��ȣ
			re_group = "1"; // �� �׷�
			re_step = "0"; // �� ����
			re_depth = "0";// �� ����
		} // if-end
			// ���, ���� ��� �־���.
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num", new Integer(num));
		model.addAttribute("re_group", new Integer(re_group));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_depth", new Integer(re_depth));
		//
		return ".main.board.writeForm";// View return writeForm.jsp
	}// writeForm-end

	// DB�� ����
	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNum = 0;// �ִ� �� ��ȣ ���� ����

		if (sqlSession.selectOne("board.numMax") != null) {// �۹�ȣ�� ������
			// �� ��ȣ�� ���� -> ���� ����
			maxNum = sqlSession.selectOne("board.numMax");// �ִ� �۹�ȣ maxNum�� �Ҵ�
		}

		if (maxNum != 0) {// ���� �̹� ������
			maxNum = maxNum + 1;// re_group �� �׷쿡 ����.
		} else {// ó�� ���̸�
			maxNum = 1;// re_group �� �׷쿡 1�� ����.(1�� ��ȣ)
		}

		String ip = request.getRemoteAddr();// ip���
		boardDTO.setIp(ip);

		// ����, ���
		if (boardDTO.getNum() != 0) {// ����̸�
			// ��� �����ֱ� ��ġ Ȯ��
			sqlSession.update("board.reStep", boardDTO);
			boardDTO.setRe_step(boardDTO.getRe_step() + 1);// �� ���� + 1
			boardDTO.setRe_depth(boardDTO.getRe_depth() + 1);// �� ���� + 1
		} else {// �����̸�
			boardDTO.setRe_group(new Integer(maxNum));// �� �׷�
			boardDTO.setRe_step(new Integer(0));// �� ����
			boardDTO.setRe_depth(new Integer(0));// �� ����
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:basic-list";// redirect:list.jsp
	}// writePro-end

	// ����Ʈ
	@RequestMapping("basic-list")
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
		int pageBlock = 10;// �� �� ��������ȣ 10��

		count = sqlSession.selectOne("board.countDao");// �� ����
		int number = count - (currentPage - 1) * pageSize;// �� ��ȣ (���� 37���� ���, 37 36 35 : �������� ����)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow + 1);// ���� ��ȣ
		map.put("cnt", pageSize);// �� ����

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);// �� ������ �� ���ϱ�
		// �� ���������ڵ� ������0 : ������ 1 ����
		//
		int startPage = (currentPage / pageBlock) * 10 + 1;// �� ����������
		int endPage = startPage + pageBlock - 1;// ���� �� ������
		// --------------------------------------------------------------------
		List<BoardDTO> list = sqlSession.selectList("board.listDao", map);

		model.addAttribute("pageNum", pageNum);// ��������ȣ
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

		return ".main.board.basic-list"; // View return list.jsp
	}

	// �� ���� ����
	@RequestMapping("content")
	public String content(Model model, String num, String pageNum) {

		int num1 = Integer.parseInt(num);
		sqlSession.update("board.readcountDao", num1);// ��ȸ������

		BoardDTO bdto = sqlSession.selectOne("board.getBoard", num1);

		String content = bdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNum", pageNum);// ��������ȣ
		model.addAttribute("num", num1);
		model.addAttribute("bdto", bdto);

		return ".main.board.content"; // view return content.jsp
	}

	// ���� ��
	@RequestMapping("updateForm")
	public ModelAndView updateForm(String num, String pageNum) {
		int num1 = Integer.parseInt(num);
		BoardDTO bdto = sqlSession.selectOne("board.getBoard", num1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("bdto", bdto);
		mv.setViewName(".main.board.updateForm");// updateForm.jsp

		return mv;
	}

	// DB�� ����
	@RequestMapping(value = "updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNum) {
		sqlSession.update("board.updateDao", boardDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("refirect:basic-list");//

		return mv;
	}
	
	//�� ����
	@RequestMapping("delete")
	public String delete(Model model, String num, String pageNum) {
		sqlSession.delete("board.deleteDao", new Integer(num));
		
		model.addAttribute("pageNum", pageNum);
		return "redirect:basic-list";
	}
}
