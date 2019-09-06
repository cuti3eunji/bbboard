package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.BoardStatusList;

@WebServlet("/writePost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class writePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(writePostController.class);
	
    
	//일반 작성시
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> list = BoardStatusList.stBoardList();
		
		User user = (User) request.getSession().getAttribute("S_USERVO");
		String userId = user.getUserId();
		logger.debug("writePost userId 1111 {}", userId);
		
		if(userId == null || userId.length() == 0) userId = "brown";
		
		logger.debug("writePost userId *** {}", userId);
		
		String boardNm = request.getParameter("boardNm");
		String boardNo = request.getParameter("boardNo");
		
		request.setAttribute("stBoardList", list);
		request.setAttribute("userId", userId);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("boardNo", boardNo);
		
		request.getRequestDispatcher("/post/writePost.jsp").forward(request, response);
	}

	
	//답글 작성시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		List<Board> list = BoardStatusList.stBoardList();
		
		User user = (User) request.getSession().getAttribute("S_USERVO");
		String userId = user.getUserId();
		
		if(userId == null || userId.length() == 0) userId = "brown";
		
		logger.debug("writePost userId dpdpdp {}", userId);
		
		String boardNm = request.getParameter("boardNm");
		String boardNo = request.getParameter("boardNo");
		logger.debug("writePost boardNo dpdpdp {}", boardNo);
		
		
		String parentNo = request.getParameter("parentNo");
		
		request.setAttribute("parentNo", parentNo);
		request.setAttribute("stBoardList", list);
		request.setAttribute("userId", userId);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("boardNo", boardNo);
		
		request.getRequestDispatcher("/post/writePost.jsp").forward(request, response);
	}

}
