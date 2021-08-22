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
	private SqlSession sqlSession;// 변수

	// 글작성폼
	@RequestMapping("/qna-re-co/write")
	public String write(Model model, String no, String pageNo) {

		if (no == null) {// no이 없으면 글 쓰기
			no = "0"; // 글 번호
		} // if-end
			// 모두 넣어줌.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		//
		return ".main.qnaReCo.write";// View return write.jsp
	}

	// 글작성 저장
	@RequestMapping(value = "/qna-re-co/savePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("qnaReCoDTO") QnaReCoDTO qnaReCoDTO, HttpServletRequest request) {
		int maxNo = 0;// 최대 글 번호 넣을 변수

		if (sqlSession.selectOne("qnaReCo.noMax") != null) {// 글번호가 있으면
			// 글 번호가 있음 -> 글이 있음
			maxNo = sqlSession.selectOne("qnaReCo.noMax");// 최대 글번호 maxNo에 할당
		}

		if (maxNo != 0) {// 글이 이미 있으면
			maxNo = maxNo + 1;// No_group 글 그룹에 넣음.
		} else {// 처음 글이면
			maxNo = 1;// No_group 글 그룹에 1을 넣음.(1번 번호)
		}

		String ip = request.getRemoteAddr();// ip얻기
		qnaReCoDTO.setIp(ip);

		// 원글, 답글
		if (qnaReCoDTO.getNo() != 0) {// 답글이면
			// 답글 끼워넣기 위치 확보
			sqlSession.update("qnaReCo.reStep", qnaReCoDTO);
			qnaReCoDTO.setRe_step(qnaReCoDTO.getRe_step() + 1);// 글 순서 + 1
			qnaReCoDTO.setRe_depth(qnaReCoDTO.getRe_depth() + 1);// 글 깊이 + 1
		} else {// 원글이면
			qnaReCoDTO.setRe_group(new Integer(maxNo));// 글 그룹
			qnaReCoDTO.setRe_step(new Integer(0));// 글 순서
			qnaReCoDTO.setRe_depth(new Integer(0));// 글 깊이
		}

		sqlSession.insert("qnaReCo.insertDao", qnaReCoDTO);

		return "redirect:/qna-re-co/list";// redirect:list.jsp
	}// writePro-end
	
	// 리스트 출력
	@RequestMapping("/qna-re-co/list")
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

		count = sqlSession.selectOne("qnaReCo.cntDao");// 글 갯수
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
		List<QnaDTO> list = sqlSession.selectList("qnaReCo.listDao", map);

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

		return ".main.qnaReCo.list";// 뷰 리턴 //views/main.jsp
	}

	// 글 내용 보기
	@RequestMapping("/qna-re-co/view")
	public String content(Model model, String no, String pageNo) {

		int no1 = Integer.parseInt(no);
		sqlSession.update("qnaReCo.readcountDao", no1);// 조회수증가

		QnaReCoDTO bdto = sqlSession.selectOne("qnaReCo.viewDao", no1);

		String content = bdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// 페이지번호
		model.addAttribute("no", no1);
		model.addAttribute("bdto", bdto);

		return ".main.qnaReCo.view"; // view return content.jsp
	}

	// 수정 폼
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
	// 글 수정 저장
	@RequestMapping(value = "/qna-re-co/updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(QnaReCoDTO qnaReCoDTO, String pageNo) {
		sqlSession.update("qnaReCo.updateDao", qnaReCoDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:/qna-re-co/list");//

		return mv;
	}
	// 글 삭제
	@RequestMapping("/qna-re-co/del")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("qnaReCo.deleteDao", new Integer(no));
		
		model.addAttribute("pageNo", pageNo);
		return "redirect:/qna-re-co/list";
	}
}
