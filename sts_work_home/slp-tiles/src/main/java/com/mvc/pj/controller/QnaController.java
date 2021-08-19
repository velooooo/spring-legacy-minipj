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

import com.mvc.pj.domain.QnaDTO;

@Controller
public class QnaController {
	@Autowired
	private SqlSession sqlSession;// 변수

	// 글작성폼
	@RequestMapping("/qna/write")
	public String write(Model model, String no, String co_group, String co_step, String co_depth, String pageNo) {
		if (no == null) {// no이 없으면 글 쓰기
			no = "0"; // 글 번호
			co_group = "1"; // 글 그룹
			co_step = "0"; // 글 순서
			co_depth = "0";// 글 깊이
		} // if-end
			// 답글, 원글 모두 넣어줌.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		model.addAttribute("co_group", new Integer(co_group));
		model.addAttribute("co_step", new Integer(co_step));
		model.addAttribute("co_depth", new Integer(co_depth));
		//
		return ".main.qna.write";// View return write.jsp
	}// writeForm-end

	// 글작성 저장
	@RequestMapping(value = "/qna/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("qnaDTO") QnaDTO qnaDTO, HttpServletRequest request) {
		String ip = request.getRemoteAddr();// ip얻기
		qnaDTO.setIp(ip);

		sqlSession.insert("qna.insertDao", qnaDTO);

		return "redirect:/qna/list";// redirect:qna.jsp
	}// writePro-end

	// 글작성 저장
	@RequestMapping(value = "/qna/saveCoPro", method = RequestMethod.POST)
	public String writeCoPro(@ModelAttribute("qnaDTO") QnaDTO qnaDTO, HttpServletRequest request) {
		int maxCoNo = 0;// 최대 글 번호 넣을 변수

		if (sqlSession.selectOne("qna.noCoMax") != null) {// 글번호가 있으면
			// 글 번호가 있음 -> 글이 있음
			maxCoNo = sqlSession.selectOne("qna.noCoMax");// 최대 글번호 maxNo에 할당
		}

		if (maxCoNo != 0) {// 글이 이미 있으면
			maxCoNo = maxCoNo + 1;// co_group 글 그룹에 넣음.
		} else {// 처음 글이면
			maxCoNo = 1;// co_group 글 그룹에 1을 넣음.(1번 번호)
		}

		String ip = request.getRemoteAddr();// ip얻기
		qnaDTO.setIp(ip);

		// 원글, 답글
		if (qnaDTO.getCo_is() != 0) {// 게시글이면
			// 답글 끼워넣기 위치 확보
			sqlSession.update("qna.coStep", qnaDTO);
			qnaDTO.setCo_step(qnaDTO.getCo_step() + 1);// 글 순서 + 1
			qnaDTO.setCo_depth(qnaDTO.getCo_depth() + 1);// 글 깊이 + 1
		} else {// 원글이면
			qnaDTO.setCo_group(new Integer(maxCoNo));// 글 그룹
			qnaDTO.setCo_step(new Integer(0));// 글 순서
			qnaDTO.setCo_depth(new Integer(0));// 글 깊이
		}

		sqlSession.insert("qna.insertCoDao", qnaDTO);

		return "redirect:/qna/list";// redirect:qna.jsp
	}// writePro-end

	@RequestMapping("/qna/list")
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

		count = sqlSession.selectOne("qna.countDao");// 글 갯수
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
		List<QnaDTO> list = sqlSession.selectList("qna.listDao", map);

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

		return ".main.qna.list";// 뷰 리턴 //views/main.jsp
	}

	// 글 내용 보기
	@RequestMapping("/qna/view")
	public String content(Model model, String no, String pageNo, String pageCoNo) {

		int no1 = Integer.parseInt(no);
		sqlSession.update("qna.readcountDao", no1);// 조회수증가

		QnaDTO qdto = sqlSession.selectOne("qna.getQna", no1);

		String content = qdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// 페이지번호
		model.addAttribute("no", no1);
		model.addAttribute("qdto", qdto);

		// ====================================================================
		if (pageCoNo == null) {// 페이지 번호 없으면
			pageCoNo = "1";
		}
		int pageCoSize = 10;// 페이지당 글 10개씩 보여줌
		int currentCoPage = Integer.parseInt(pageCoNo);// 현재 페이지

		int startCoRow = (currentCoPage - 1) * pageCoSize + 1;// 페이지의 첫 번째 행 구함 : (현재페이지-1)*10+1
		int endCoRow = currentCoPage * pageCoSize;// 페이지의 마지막 행
		// startCoRow 1 11 21
		// endCoRow 10 20 30
		int countCo = 0;// 총 글 갯수 넣을 변수
		int pageCoBlock = 10;// 블럭 당 페이지번호 10개

		countCo = sqlSession.selectOne("qna.countCoDao");// 글 갯수
		int numberCo = countCo - (currentCoPage - 1) * pageCoSize;// 글 번호 (글이 37개인 경우, 37 36 35 : 역순으로 나옴)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startCoRow - 1);// 시작 번호
		map.put("cnt", pageCoSize);// 총 갯수

		int pageCoCount = countCo / pageCoSize + (countCo % pageCoSize == 0 ? 0 : 1);// 총 페이지 수 구하기
		// 몫 나머지레코드 없으면0 : 있으면 1 더함
		//
		int startCoPage = (currentCoPage / pageCoBlock) * 10 + 1;// 블럭 시작페이지
		int endCoPage = startCoPage + pageCoBlock - 1;// 블럭의 끝 페이지
		// --------------------------------------------------------------------
		List<QnaDTO> listCo = sqlSession.selectList("qna.listCoDao", map);

		model.addAttribute("pageCoNo", pageCoNo);// 페이지번호
		model.addAttribute("currentCoPage", currentCoPage);// 현재 페이지
		model.addAttribute("startCoRow", startCoRow);// 페이지의 첫 번째 행
		model.addAttribute("endCoRow", endCoRow);// 페이지의 마지막 행
		model.addAttribute("pageCoBlock", pageCoBlock);// 블럭 당 페이지번호 10개
		model.addAttribute("pageCoCount", pageCoCount);// 총 페이지 수
		model.addAttribute("startCoPage", startCoPage);// 블럭 시작페이지
		model.addAttribute("endCoPage", endCoPage);// 블럭의 끝 페이지
		model.addAttribute("countCo", countCo);// 전체 글 갯수
		model.addAttribute("pageCoSize", pageCoSize);// 페이지당 글 갯수
		model.addAttribute("numberCo", numberCo);// 글 번호
		model.addAttribute("listCo", listCo);//
		// ====================================================================

		return ".main.qna.view"; // view return content.jsp
	}

	// 수정 폼
	@RequestMapping("/qna/edit")
	public ModelAndView updateForm(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		QnaDTO qdto = sqlSession.selectOne("qna.getQna", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("qdto", qdto);
		mv.setViewName(".main.qna.edit");// updateForm.jsp

		return mv;
	}

	// 수정 폼
	@RequestMapping("/qna/editCo")
	public ModelAndView updateFormCo(String no, String pageNo) {
		int no1 = Integer.parseInt(no);
		QnaDTO qdto = sqlSession.selectOne("qna.getQna", no1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.addObject("qdto", qdto);
		mv.setViewName(".main.qna.edit");// updateForm.jsp

		return mv;
	}

	// DB글 수정
	@RequestMapping(value = "/qna/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(QnaDTO qnaDTO, String pageNo) {
		sqlSession.update("qna.updateDao", qnaDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/qna/list");//

		return mv;
	}

	// 글 삭제
	@RequestMapping("/qna/del")
	public String delete(Model model, String no, String pageNo) {
		// 댓글이 하나라도 있으면 삭제할 수 없음.

		// 삭제
		sqlSession.delete("qna.deleteDao", new Integer(no));

		model.addAttribute("pageNo", pageNo);
		return "redirect:/qna/list";
	}

	// 댓글 삭제
	@RequestMapping("/qna/delCo")
	public String deleteCo(Model model, String no, String pageNo) {
		// 대댓글이 하나라도 있으면 삭제할 수 없음

		// 삭제
		sqlSession.delete("qna.deleteCoDao", new Integer(no));

		model.addAttribute("pageNo", pageNo);
		return "redirect:/qna/list";
	}
}
