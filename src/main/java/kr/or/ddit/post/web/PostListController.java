package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.BoardStatusList;

@WebServlet("/postList")
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostListController.class);
	
	private IPostService postService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		boardService = new BoardService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		String boardNumber = request.getParameter("boardNo");
		if (boardNumber == null) {
			boardNumber = "0";
		}
		int boardNo = Integer.parseInt(boardNumber);
		
		
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
 		int pagesize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
 		logger.debug("pagesize ==> {} " , pagesize + "");
 		
 		int totalCnt = postService.getPostTotalCnt(boardNo);
 		
 		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BOARDNO", boardNo);
		map.put("PAGE", page);
		map.put("PAGESIZE", pagesize);
		
		
		Board board = boardService.getBoardInfo(boardNo);
		
		String boardNm = board.getBoardNm();

		logger.debug("update boardNo, Nm {}{}", boardNo, boardNm);
		
		
		List<Post> list = postService.getPostPagingList(map);
		int paginationSize = (int)Math.ceil((double)totalCnt/pagesize);
		
		logger.debug("pagination size ==> {} " , paginationSize + "");
		
		request.setAttribute("boardNo", boardNo);
		logger.debug("boardNo postListcontroller {}", boardNo);
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postList", list);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("post/postList.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		String boardNumber = request.getParameter("boardNo");
		if (boardNumber == null) {
			boardNumber = "0";
		}
		int boardNo = Integer.parseInt(boardNumber);
		
		
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
 		int pagesize = pagesizeStr == null ? 10 : Integer.parseInt(pagesizeStr);
 		logger.debug("pagesize ==> {} " , pagesize + "");
 		
 		int totalCnt = postService.getPostTotalCnt(boardNo);
 		
 		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("BOARDNO", boardNo);
		map.put("PAGE", page);
		map.put("PAGESIZE", pagesize);
		
		
		Board board = boardService.getBoardInfo(boardNo);
		String boardNm = board.getBoardNm();
		
		List<Post> list = postService.getPostPagingList(map);
		int paginationSize = (int)Math.ceil((double)totalCnt/pagesize);
		
		logger.debug("pagination size ==> {} " , paginationSize + "");
		
		request.setAttribute("boardNo", boardNo);
		logger.debug("boardNo postListcontroller {}", boardNo);
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postList", list);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("post/postList.jsp").forward(request, response);
	}

}
