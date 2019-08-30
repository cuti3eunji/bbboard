package kr.or.ddit.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.util.BoardStatusList;

@WebServlet("/managementBoard")
public class managementBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IBoardService boardService;
    
    @Override
    public void init() throws ServletException {
    	boardService = new BoardService();
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> boardList = boardService.getAllBoardList();
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("stBoardList", stBoardList);
		
		request.getRequestDispatcher("/board/managementBoard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

}
