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
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.BoardStatusList;

@WebServlet("/postList")
public class PostListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostListController.class);
	
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String pageStr = request.getParameter("page");
		String pagesizeStr = request.getParameter("pagesize");
		
		int page = pageStr==null ? 1 : Integer.parseInt(pageStr);
		int pagesize = pagesizeStr==null ? 10 : Integer.parseInt(pagesizeStr);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardNo", boardNo);
		map.put("page", page);
		map.put("pagesize", pagesize);

		List<Post> list = postService.getPostPagingList(map);
		int paginationSize = (int)Math.ceil((double)list.size()/pagesize);
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postList", list);
		request.setAttribute("boardNm", request.getParameter("boardNm"));
		request.setAttribute("paginationSize", paginationSize);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("post/postList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
