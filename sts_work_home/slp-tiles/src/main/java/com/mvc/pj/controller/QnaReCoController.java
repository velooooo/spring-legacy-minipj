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

import com.mvc.pj.domain.QnaReCoDTO;
import com.mvc.pj.domain.QnaDTO;
import com.mvc.pj.domain.QnaReCoDTO;

@Controller
public class QnaReCoController {
	@Autowired
	private SqlSession sqlSession;// ����

	// ���ۼ���
	@RequestMapping("/qna-re-co/write")
	public String write(Model model, String no, String pageNo) {

		if (no == null) {// no�� ������ �� ����
			no = "0"; // �� ��ȣ
		} // if-end
			// ��� �־���.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		//
		return ".main.qnaReCo.write";// View return write.jsp
	}

	// ���ۼ� ����
	@RequestMapping(value = "/qna-re-co/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("qnaReCoDTO") QnaReCoDTO qnaReCoDTO, HttpServletRequest request) {
		int maxNo = 0;// �ִ� �� ��ȣ ���� ����

		if (sqlSession.selectOne("qnaReCo.noMax") != null) {// �۹�ȣ�� ������
			// �� ��ȣ�� ���� -> ���� ����
			maxNo = sqlSession.selectOne("qnaReCo.noMax");// �ִ� �۹�ȣ maxNo�� �Ҵ�
		}

		if (maxNo != 0) {// ���� �̹� ������
			maxNo = maxNo + 1;// No_group �� �׷쿡 ����.
		} else {// ó�� ���̸�
			maxNo = 1;// No_group �� �׷쿡 1�� ����.(1�� ��ȣ)
		}

		String ip = request.getRemoteAddr();// ip���
		qnaReCoDTO.setIp(ip);

		// ����, ���
		if (qnaReCoDTO.getNo() != 0) {// ����̸�
			// ��� �����ֱ� ��ġ Ȯ��
			sqlSession.update("qnaReCo.reStep", qnaReCoDTO);
			qnaReCoDTO.setRe_step(qnaReCoDTO.getRe_step() + 1);// �� ���� + 1
			qnaReCoDTO.setRe_depth(qnaReCoDTO.getRe_depth() + 1);// �� ���� + 1
		} else {// �����̸�
			qnaReCoDTO.setRe_group(new Integer(maxNo));// �� �׷�
			qnaReCoDTO.setRe_step(new Integer(0));// �� ����
			qnaReCoDTO.setRe_depth(new Integer(0));// �� ����
		}

		sqlSession.insert("qnaReCo.insertDao", qnaReCoDTO);

		return "redirect:/qna-re-co/list";// redirect:list.jsp
	}// writePro-end
	
	// ����Ʈ ���
	@RequestMapping("/qna-re-co/list")
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

		count = sqlSession.selectOne("qnaReCo.cntDao");// �� ����
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
		List<QnaDTO> list = sqlSession.selectList("qnaReCo.listDao", map);

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

		return ".main.qnaReCo.list";// �� ���� //views/main.jsp
	}

	// �� ���� ����
	@RequestMapping("/qna-re-co/view")
	public String content(Model model, String no, String pageNo) {

		int no1 = Integer.parseInt(no);
		sqlSession.update("qnaReCo.readcountDao", no1);// ��ȸ������

		QnaReCoDTO bdto = sqlSession.selectOne("qnaReCo.viewDao", no1);

		String content = bdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// ��������ȣ
		model.addAttribute("no", no1);
		model.addAttribute("bdto", bdto);

		return ".main.qnaReCo.view"; // view return content.jsp
	}

	// ���� ��
	@RequestMapping("/qna-re-co/edit")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		QnaReCoDTO bdto = sqlSession.selectOne("qnaReCo.viewDao", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("bdto", bdto);
		mv.setViewName(".main.qnaReCo.edit");// updateForm.jsp

		return mv;
	}
	// �� ���� ����
	@RequestMapping(value = "/qna-re-co/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(QnaReCoDTO qnaReCoDTO, String pageNo) {
		sqlSession.update("qnaReCo.updateDao", qnaReCoDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/qna-re-co/list");//

		return mv;
	}
	// �� ����
	@RequestMapping("/qna-re-co/del")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("qnaReCo.deleteDao", new Integer(no));
		
		model.addAttribute("pageNo", pageNo);
		return "redirect:/qna-re-co/list";
	}
}
