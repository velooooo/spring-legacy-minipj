package com.mvc.pj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.pj.domain.BoardDTO;

public class BoardController {
	@Autowired
	private SqlSession sqlSession;// 변수, setter 자동으로

	// 글쓰기 폼, 답글쓰기
	@RequestMapping("writeForm")
	public String writeForm(Model model, String num, String re_group, String re_step, String re_depth, String pageNum) {

		if (num == null) {// num이 없으면 글 쓰기
			num = "0"; // 글 번호
			re_group = "1"; // 글 그룹
			re_step = "0"; // 글 순서
			re_depth = "0";// 글 깊이
		} // if-end
			// 답글, 원글 모두 넣어줌.
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num", new Integer(num));
		model.addAttribute("re_group", new Integer(re_group));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_depth", new Integer(re_depth));
		//
		return "board/writeForm";// View return writeForm.jsp
	}// writeForm-end

	// DB글 쓰기
	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNum = 0;// 최대 글 번호 넣을 변수

		if (sqlSession.selectOne("board.numMax") != null) {// 글번호가 있으면
			// 글 번호가 있음 -> 글이 있음
			maxNum = sqlSession.selectOne("board.numMax");// 최대 글번호 maxNum에 할당
		}

		if (maxNum != 0) {// 글이 이미 있으면
			maxNum = maxNum + 1;// re_group 글 그룹에 넣음.
		} else {// 처음 글이면
			maxNum = 1;// re_group 글 그룹에 1을 넣음.(1번 번호)
		}

		String ip = request.getRemoteAddr();// ip얻기
		boardDTO.setIp(ip);

		// 원글, 답글
		if (boardDTO.getNum() != 0) {// 답글이면
			// 답글 끼워넣기 위치 확보
			sqlSession.update("board.reStep", boardDTO);
			boardDTO.setRe_step(boardDTO.getRe_step() + 1);// 글 순서 + 1
			boardDTO.setRe_depth(boardDTO.getRe_depth() + 1);// 글 깊이 + 1
		} else {// 원글이면
			boardDTO.setRe_group(new Integer(maxNum));// 글 그룹
			boardDTO.setRe_step(new Integer(0));// 글 순서
			boardDTO.setRe_depth(new Integer(0));// 글 깊이
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:list";// redirect:list.jsp
	}// writePro-end

	// 리스트
	@RequestMapping("list")
	public String listBoard(Model model, String pageNum) {
		if (pageNum == null) {// 페이지 번호 없으면
			pageNum = "1";
		}
		int pageSize = 10;// 페이지당 글 10개씩 보여줌
		int currentPage = Integer.parseInt(pageNum);// 현재 페이지

		int startRow = (currentPage - 1) * pageSize + 1;// 페이지의 첫 번째 행 구함 : (현재페이지-1)*10+1
		int endRow = currentPage * pageSize;// 페이지의 마지막 행
		// startRow 1 11 21
		// endRow 10 20 30
		int count = 0;// 총 글 갯수 넣을 변수
		int pageBlock = 10;// 블럭 당 페이지번호 10개

		count = sqlSession.selectOne("board.countDao");// 글 갯수
		int number = count - (currentPage - 1) * pageSize;// 글 번호 (글이 37개인 경우, 37 36 35 : 역순으로 나옴)

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow + 1);// 시작 번호
		map.put("cnt", pageSize);// 총 갯수

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);// 총 페이지 수 구하기
		// 몫 나머지레코드 없으면0 : 있으면 1 더함
		//
		int startPage = (currentPage / pageBlock) * 10 + 1;// 블럭 시작페이지
		int endPage = startPage + pageBlock - 1;// 블럭의 끝 페이지
		// --------------------------------------------------------------------
		List<BoardDTO> list = sqlSession.selectList("board.listDao", map);

		model.addAttribute("pageNum", pageNum);// 페이지번호
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

		return "board/list"; // View return list.jsp
	}

	// 글 내용 보기
	@RequestMapping("content")
	public String content(Model model, String num, String pageNum) {

		int num1 = Integer.parseInt(num);
		sqlSession.update("board.readcountDao", num1);// 조회수증가

		BoardDTO bdto = sqlSession.selectOne("board.getBoard", num1);

		String content = bdto.getContent();
		content = content.replace("\n", "<br/>");

		model.addAttribute("content", content);
		model.addAttribute("pageNum", pageNum);// 페이지번호
		model.addAttribute("num", num1);
		model.addAttribute("bdto", bdto);

		return "board/content"; // view return content.jsp
	}

	// 수정 폼
	@RequestMapping("updateForm")
	public ModelAndView updateForm(String num, String pageNum) {
		int num1 = Integer.parseInt(num);
		BoardDTO bdto = sqlSession.selectOne("board.getBoard", num1);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("bdto", bdto);
		mv.setViewName("board/updateForm");// updateForm.jsp

		return mv;
	}

	// DB글 수정
	@RequestMapping(value = "updatePro", method = RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNum) {
		sqlSession.update("board.updateDao", boardDTO);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("refirect:list");//

		return mv;
	}
	
	//글 삭제
	@RequestMapping("delete")
	public String delete(Model model, String num, String pageNum) {
		sqlSession.delete("board.deleteDao", new Integer(num));
		
		model.addAttribute("pageNum", pageNum);
		return "redirect:list";
	}
}
