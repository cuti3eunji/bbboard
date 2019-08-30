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

@WebServlet("/updateBoard")
public class UpdateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UpdateBoardController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String boardNo = request.getParameter("boardNo");
		String boardNm = request.getParameter("boardNm");
		String boardStatus = request.getParameter("boardStatus");
		
		logger.debug("boardNm {}, boardStatus {}, boardNo {}", boardNm, boardStatus, boardNo);
		
		Board board = new Board();
		board.setBoardNo(Integer.parseInt(boardNo));
		board.setBoardNm(boardNm);
		board.setBoardStatus(Integer.parseInt(boardStatus));
		
		// 게시판 수정
		int updateCnt = 0;

		try {
			updateCnt = boardService.updateBoard(board);
			
			if (updateCnt == 1) {
				// 서버 상의 상태가 바뀔때는 redirect를 사용한다. -중복방지
				response.sendRedirect(request.getContextPath() + "/managementBoard");
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
