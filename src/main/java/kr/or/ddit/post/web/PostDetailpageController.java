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
import kr.or.ddit.post.model.AFile;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.util.BoardStatusList;

@WebServlet("/postDetail")
@MultipartConfig()
public class PostDetailpageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostDetailpageController.class);

	private IPostService postService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
    
	
	//일반 조회 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String boardNm = request.getParameter("boardNm");
		
		List<Board> stBoardList = BoardStatusList.stBoardList();
		Post postdt = postService.getPostDetail(postNo);
		List<AFile> afileList = postService.getAttachedFile(postNo);
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postdt", postdt);
		request.setAttribute("afile", afileList);
		request.setAttribute("boardNm", boardNm);
		
		
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
	}
	
	//글작성 후 보여지는 화면
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		
	}

}
