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
import com.mvc.pj.domain.FaqDTO;

@Controller
public class FaqController {
	@Autowired
	private SqlSession sqlSession;// ����

	// ���ۼ���
	@RequestMapping("/faq/write")
	public String write(Model model, String no, String pageNo) {

		if (no == null) {// no�� ������ �� ����
			no = "0"; // �� ��ȣ
		} // if-end
			// ��� �־���.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		//
		return ".main.faq.write";// View return write.jsp
	}// writeForm-end

	// ���ۼ� ����
	@RequestMapping(value = "/faq/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("faqDTO") FaqDTO faqDTO, HttpServletRequest request) {
		String ip = request.getRemoteAddr();// ip���
		faqDTO.setIp(ip);

		sqlSession.insert("faq.insertDao", faqDTO);

		return "redirect:/faq/list";// redirect:faq.jsp
	}// writePro-end

	@RequestMapping("/faq/list")
	public String list(Model model, String pageNo) {
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

		count = sqlSession.selectOne("faq.countDao");// �� ����
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
		List<BoardDTO> list = sqlSession.selectList("faq.listDao", map);

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

		return ".main.faq.list";// �� ���� //views/main.jsp
	}

	// ���� ��
	@RequestMapping("/faq/update")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		FaqDTO fdto = sqlSession.selectOne("faq.getFaq", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("fdto", fdto);
		mv.setViewName(".main.faq.update");// updateForm.jsp

		return mv;
	}

	// DB�� ����
	@RequestMapping(value = "/faq/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNo) {
		sqlSession.update("faq.updateDao", boardDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/faq/list");//

		return mv;
	}

	// �� ����
	@RequestMapping("/faq/del")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("faq.deleteDao", new Integer(no));

		model.addAttribute("pageNo", pageNo);
		return "redirect:/faq/list";
	}
}
