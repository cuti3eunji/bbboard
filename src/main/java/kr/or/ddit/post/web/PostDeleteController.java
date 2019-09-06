package kr.or.ddit.post.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet("/deletePost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostDeleteController.class);
       
	private IPostService postService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String postNumber = request.getParameter("postNo");
		if (postNumber == null) {
			postNumber = "0";
		}
		int postNo = Integer.parseInt(postNumber);

		//본인게시글일때만
		
		
		
		
		
		Post postdt = postService.getPostDetail(postNo);
		int boardNo = postdt.getBoardNo();
		
		Board boarddt = boardService.getBoardInfo(boardNo);
		String boardNm = boarddt.getBoardNm();
		logger.debug("postdelete boardNm {}", boardNm);
		
		
		
		
		int deleteCnt = 0;
		deleteCnt = postService.deletePost(postNo);
		
		if(deleteCnt == 1) {
			//성공
			response.sendRedirect(request.getContextPath() + "/postList?boardNo=" + boardNo + "&boardNm=" + boardNm);
		}else {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
