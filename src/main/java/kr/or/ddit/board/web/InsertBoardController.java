package kr.or.ddit.board.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.User;

@WebServlet("/insertBoard")
public class InsertBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(InsertBoardController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String boardNm = request.getParameter("boardNm");
		String boardStatus = request.getParameter("boardStatus");
		User user = (User) request.getSession().getAttribute("S_USERVO");
		
		logger.debug("boardNm {}, boardStatus {}", boardNm, boardStatus);
		logger.debug("InsertBoardController.doPost userId {}", user.getUserId());
		
		// 게시판 등록
		Board board = new Board(user.getUserId(), boardNm, Integer.parseInt(boardStatus));
		
		int insertCnt = 0;

		try {
			insertCnt = boardService.insertBoard(board);
			
			if (insertCnt == 1) {
				// 서버 상의 상태가 바뀔때는 redirect를 사용한다. -중복방지
				response.sendRedirect(request.getContextPath() + "/managementBoard?");
			}
			// 실패 시 : 메인으로
			else {
				doGet(request, response);
			}
		} catch (Exception e) {
			doGet(request, response);
		} // catch
		
	}

}
