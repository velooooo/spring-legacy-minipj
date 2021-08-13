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
	private SqlSession sqlSession;// 변수, setter 자동으로

	// 글쓰기 폼, 답글쓰기
	@RequestMapping("writeForm")
	public String writeForm(Model model, String no, String ref, String re_step, String re_level, String pageNo) {

		if (no == null) {// no이 없으면 글 쓰기
			no = "0"; // 글 번호
			ref = "1"; // 글 그룹
			re_step = "0"; // 글 순서
			re_level = "0";// 글 깊이
		} // if-end
			// 답글, 원글 모두 넣어줌.
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("no", new Integer(no));
		model.addAttribute("ref", new Integer(ref));
		model.addAttribute("re_step", new Integer(re_step));
		model.addAttribute("re_level", new Integer(re_level));
		//
		return "board/writeForm";// View return writeForm.jsp
	}// writeForm-end

	// DB글 쓰기
	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String writePro(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
		int maxNo = 0;// 최대 글 번호 넣을 변수

		if (sqlSession.selectOne("board.noMax") != null) {// 글번호가 있으면
			// 글 번호가 있음 -> 글이 있음
			maxNo = sqlSession.selectOne("board.noMax");// 최대 글번호 maxno에 할당
		}

		if (maxNo != 0) {// 글이 이미 있으면
			maxNo = maxNo + 1;// ref 글 그룹에 넣음.
		} else {// 처음 글이면
			maxNo = 1;// ref 글 그룹에 1을 넣음.(1번 번호)
		}

		sqlSession.insert("board.insertDao", boardDTO);

		return "redirect:list";// redirect:list.jsp
	}// writePro-end

	// 리스트
	@RequestMapping("list")
	public String listBoard(Model model, String pageNo) {
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

		count = sqlSession.selectOne("board.countDao");// 글 갯수
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
		List<BoardDTO> list = sqlSession.selectList("board.listDao", map);

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

		return "board/list"; // View return list.jsp
	}
	
	//글 내용 보기
	@RequestMapping("content")
	public String content(Model model, String no, String pageNo) {
		
		int no1 = Integer.parseInt(no);
		sqlSession.update("board.hitDao", no1);//조회수증가
		
		BoardDTO bdto=sqlSession.selectOne("board.getBoard", no1);

		String content=bdto.getContent();
		content=content.replace("\n", "<br/>");
		
		model.addAttribute("content", content);
		model.addAttribute("pageNo", pageNo);// 페이지번호
		model.addAttribute("no", no1);
		model.addAttribute("bdto", bdto);
		
		return "board/content"; //view return content.jsp
	}
	
	//수정 폼
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
	
	//DB글 수정
	@RequestMapping(value="updatePro", method=RequestMethod.POST)
	public ModelAndView updatePro(BoardDTO boardDTO, String pageNo) {
		sqlSession.update("board.updateDao", boardDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNo", pageNo);
		mv.setViewName("redirect:list");//
		
		return mv;
	}
	
	//글 삭제
	@RequestMapping("delete")
	public String delete(Model model, String no, String pageNo) {
		sqlSession.delete("board.deleteDao", new Integer(no));
		
		model.addAttribute("pageNo", pageNo);
		return "redirect:list";
	}
	
}
