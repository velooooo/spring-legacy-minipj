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
	@RequestMapping("/board/write")
	public String writeForm(Model model, String no, String re_group, String re_step, String re_depth, String pageNo) {

		if (no == null) {// no�� ������ �� ����
			no = "0"; // �� ��ȣ
			re_group = "1"; // �� �׷�
			re_step = "0"; // �� ����
			re_depth = "0";// �� ����
		} // if-end
			// ���, ���� ��� �־���.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		model.addAttribute("re_group", new Integer(re_group));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_depth", new Integer(re_depth));
		//
		return ".main.board.write";// View return writeForm.jsp
	}// writeForm-end

	// DB�� ����
	@RequestMapping(value = "/board/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNo = 0;// �ִ� �� ��ȣ ���� ����

		if (sqlSession.selectOne("board.noMax") != null) {// �۹�ȣ�� ������
			// �� ��ȣ�� ���� -> ���� ����
			maxNo = sqlSession.selectOne("board.noMax");// �ִ� �۹�ȣ maxNo�� �Ҵ�
		}

		if (maxNo != 0) {// ���� �̹� ������
			maxNo = maxNo + 1;// re_group �� �׷쿡 ����.
		} else {// ó�� ���̸�
			maxNo = 1;// re_group �� �׷쿡 1�� ����.(1�� ��ȣ)
		}

		String ip = request.getRemoteAddr();// ip���
		boardDTO.setIp(ip);

		// ����, ���
		if (boardDTO.getNo() != 0) {// ����̸�
			// ��� �����ֱ� ��ġ Ȯ��
			sqlSession.update("board.reStep", boardDTO);
			boardDTO.setRe_step(boardDTO.getRe_step() + 1);// �� ���� + 1
			boardDTO.setRe_depth(boardDTO.getRe_depth() + 1);// �� ���� + 1
		} else {// �����̸�
			boardDTO.setRe_group(new Integer(maxNo));// �� �׷�
			boardDTO.setRe_step(new Integer(0));// �� ����
			boardDTO.setRe_depth(new Integer(0));// �� ����
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:/board/list";// redirect:list.jsp
	}// writePro-end

	// ����Ʈ
	@RequestMapping("/board/list")
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

		return ".main.board.list"; // View return list.jsp
	}

	// �� ���� ����
	@RequestMapping("/board/view")
	public String content(Model model, String no, String pageNo) {

		int no1 = Integer.parseInt(no);
		sqlSession.update("board.readcountDao", no1);// ��ȸ������

		BoardDTO bdto = sqlSession.selectOne("board.getBoard", no1);

		String content = bdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// ��������ȣ
		model.addAttribute("no", no1);
		model.addAttribute("bdto", bdto);

		return ".main.board.view"; // view return content.jsp
	}

	// ���� ��
	@RequestMapping("/board/edit")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		BoardDTO bdto = sqlSession.selectOne("board.getBoard", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("bdto", bdto);
		mv.setViewName(".main.board.edit");// updateForm.jsp

		return mv;
	}

	// DB�� ����
	@RequestMapping(value = "/board/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNo) {
		sqlSession.update("board.updateDao", boardDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/board/list");//

		return mv;
	}
	
	//�� ����
	@RequestMapping("/board/del")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("board.deleteDao", new Integer(no));
		
		model.addAttribute("pageNo", pageNo);
		return "redirect:/board/list";
	}
}
