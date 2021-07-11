package springmvc.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.model.AuthInfo;
import springmvc.model.Board;
import springmvc.model.BoardListInfo;
import springmvc.model.LoginCommand;
import springmvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	private BoardService boardService;
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 게시물 단건 조회
	 * @param bSeq
	 * @return
	 * ex) /board/view?boardSeq=1
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getView(@RequestParam(value = "boardSeq") long bSeq, Model model) throws Exception 
	{
		Board board = boardService.getBoardOne(bSeq);
		
//		Board board = new Board();
//		board.setId(1);
//		board.setTitle("aaa");
//		board.setContent("bbb");
//		board.setMemberId(2);
//		board.setRegDate(LocalDateTime.now());
		
		model.addAttribute("view", board);
		
		return "board/boardView";
	}	
	
	/**
	 * 게시물 리스트 조회
	 * @param 
	 * @return
	 * ex) /board/list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) throws Exception 
	{
		List<BoardListInfo> boardListInfo = boardService.getBoardList();
		
		model.addAttribute("boardList", boardListInfo);
		
		return "board/boardList";
	}	
	
	/**
	 * 게시물 작성화면(GET)
	 * @param 
	 * @return
	 * ex) /board/write
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String getWrite(Board board, HttpSession session) throws Exception 
	{
//		Object loginInfo = session.getAttribute("authInfo");
//		if(loginInfo == null) {
//			return "redirect:/login";
//		}
		
		return "board/boardWrite";
	}
	
	/**
	 * 게시물 작성(POST)
	 * @param 
	 * @return
	 * ex) /board/write
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String postWrite(Board board, HttpSession session) throws Exception 
	{
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//		if(authInfo == null) {
//			return "redirect:/login";
//		}
		
		board.setMemberId(authInfo.getId());
		board.setRegDate(LocalDateTime.now());
		
		int rowAffected = boardService.write(board);
		
		return "redirect:/board/list";
	}
	
	/**
	 * 게시물 수정화면(GET)
	 * @param 
	 * @return
	 * ex) /board/modify
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String getModify(@RequestParam(value = "boardSeq") long bSeq, Model model) throws Exception 
	{
		Board board = boardService.getBoardOne(bSeq);
		model.addAttribute("view", board);
		
		return "board/boardModify";
	}

	/**
	 * 게시물 수정(POST)
	 * @param
	 * @return
	 * ex) /board/modify
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String postModify(Board board, HttpSession session) throws Exception
	{
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");

		board.setModifyDate(LocalDateTime.now());

		int rowAffected = boardService.modify(board);

		return "redirect:/board/list";
	}
}
