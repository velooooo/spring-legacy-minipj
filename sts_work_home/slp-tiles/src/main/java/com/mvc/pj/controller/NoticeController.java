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

import com.mvc.pj.domain.NoticeDTO;

@Controller
public class NoticeController {
	@Autowired
	private SqlSession sqlSession;// 변수

	// 글작성폼
	@RequestMapping("/notice/write")
	public String write(Model model, String no, String pageNo) {

		if (no == null) {// no이 없으면 글 쓰기
			no = "0"; // 글 번호
		} // if-end
			// 모두 넣어줌.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		//
		return ".main.notice.write";// View return write.jsp
	}// writeForm-end

	// 글작성 저장
	@RequestMapping(value = "/notice/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("noticeDTO") NoticeDTO noticeDTO, HttpServletRequest request) {
		String ip = request.getRemoteAddr();// ip얻기
		noticeDTO.setIp(ip);

		sqlSession.insert("notice.insertDao", noticeDTO);

		return "redirect:/notice/list";// redirect:notice.jsp
	}// writePro-end

	@RequestMapping("/notice/list")
	public String list(Model model, String pageNo) {
		if (pageNo == null) {// 페이지 번호 없으면
			pageNo = "1";
		}
		int pageSize = 10;// 페이지당 글 10개씩 보여줌
		int currentPage = Integer.parseInt(pageNo);// 현재 페이지

		int startRow = (currentPage - 1) * pageSize + 1;// 페이지의 첫 번째 행 구함 : (현재페이지-1)*10+1
		int endRow = currentPage * pageSize;// 페이지의 마지막 행
		// startRow 1 11 21
		// endRow 10 20 30
		int count = 0;// 총 글 갯수 넣을 변수
		int pageBlock = 10;// 블럭 당 페이지번호 10개

		count = sqlSession.selectOne("notice.countDao");// 글 갯수
		int number = count - (currentPage - 1) * pageSize;// 글 번호 (글이 37개인 경우, 37 36 35 : 역순으로 나옴)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow - 1);// 시작 번호
		map.put("cnt", pageSize);// 총 갯수

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);// 총 페이지 수 구하기
		// 몫 나머지레코드 없으면0 : 있으면 1 더함
		//
		int startPage = (currentPage / pageBlock) * 10 + 1;// 블럭 시작페이지
		int endPage = startPage + pageBlock - 1;// 블럭의 끝 페이지
		// --------------------------------------------------------------------
		List<NoticeDTO> list = sqlSession.selectList("notice.listDao", map);

		model.addAttribute("pageNo", pageNo);// 페이지번호
		model.addAttribute("currentPage", currentPage);// 현재 페이지
		model.addAttribute("startRow", startRow);// 페이지의 첫 번째 행
		model.addAttribute("endRow", endRow);// 페이지의 마지막 행
		model.addAttribute("pageBlock", pageBlock);// 블럭 당 페이지번호 10개
		model.addAttribute("pageCount", pageCount);// 총 페이지 수
		model.addAttribute("startPage", startPage);// 블럭 시작페이지
		model.addAttribute("endPage", endPage);// 블럭의 끝 페이지
		model.addAttribute("count", count);// 전체 글 갯수
		model.addAttribute("pageSize", pageSize);// 페이지당 글 갯수
		model.addAttribute("number", number);// 글 번호
		model.addAttribute("list", list);//

		return ".main.notice.list";// 뷰 리턴 //views/main.jsp
	}

	// 글 내용 보기
	@RequestMapping("/notice/view")
	public String content(Model model, String no, String pageNo) {

		int no1 = Integer.parseInt(no);
		sqlSession.update("notice.readcountDao", no1);// 조회수증가

		NoticeDTO ndto = sqlSession.selectOne("notice.getNotice", no1);

		String content = ndto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// 페이지번호
		model.addAttribute("no", no1);
		model.addAttribute("ndto", ndto);

		return ".main.notice.view"; // view return content.jsp
	}

	// 수정 폼
	@RequestMapping("/notice/edit")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		NoticeDTO ndto = sqlSession.selectOne("notice.getNotice", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("ndto", ndto);
		mv.setViewName(".main.notice.edit");// updateForm.jsp

		return mv;
	}

	// DB글 수정
	@RequestMapping(value = "/notice/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(NoticeDTO noticeDTO, String pageNo) {
		sqlSession.update("notice.updateDao", noticeDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/notice/list");//

		return mv;
	}

	// 글 삭제
	@RequestMapping("/notice/del")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("notice.deleteDao", new Integer(no));

		model.addAttribute("pageNo", pageNo);
		return "redirect:/notice/list";
	}
}
