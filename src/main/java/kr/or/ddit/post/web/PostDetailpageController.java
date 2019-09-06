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

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.afile.service.AfileService;
import kr.or.ddit.afile.service.IAfileService;
import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.comment.model.Comment;
import kr.or.ddit.comment.service.CommentService;
import kr.or.ddit.comment.service.ICommentService;
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
	private IAfileService afileService;
	private ICommentService cmtService;
	private IBoardService boardService;

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		afileService = new AfileService();
		cmtService = new CommentService();
		boardService = new BoardService();
	}
    
	
	//등록후 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String postNumber = request.getParameter("postNo");
		if (postNumber == null) {
			postNumber = "0";
		}
		int postNo = Integer.parseInt(postNumber);

		Post post = postService.getPostDetail(postNo);
		int boardNo = post.getBoardNo();
		
		String boardNm = boardService.getBoardInfo(boardNo).getBoardNm();
		
		
		List<Board> stBoardList = BoardStatusList.stBoardList();
		Post postdt = postService.getPostDetail(postNo);
		List<AFile> afileList = afileService.getAttachedFile(postNo);

		List<Comment> cmtList = cmtService.getCmtList(postNo);
		
		for(Comment cc : cmtList) {
			logger.debug(cc.getCommentContent());
		}
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postdt", postdt);
		request.setAttribute("afile", afileList);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("cmtList", cmtList);
		request.setAttribute("boardNo", boardNo);
		
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
		
	}
	
	
	//일반 조회 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String postNumber = request.getParameter("postNo");
		if (postNumber == null) {
			postNumber = "0";
		}
		int postNo = Integer.parseInt(postNumber);
		logger.debug("detailpage postNo {} " , postNo);
		

		String boardNm = request.getParameter("boardNm");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		logger.debug("boardNo postdetailController {}", boardNo);
		
		List<Board> stBoardList = BoardStatusList.stBoardList();
		Post postdt = postService.getPostDetail(postNo);
		List<AFile> afileList = afileService.getAttachedFile(postNo);

		List<Comment> cmtList = cmtService.getCmtList(postNo);
		
		request.setAttribute("stBoardList", stBoardList);
		request.setAttribute("postdt", postdt);
		request.setAttribute("afile", afileList);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("cmtList", cmtList);
		request.setAttribute("boardNo", boardNo);

		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
	}

}
