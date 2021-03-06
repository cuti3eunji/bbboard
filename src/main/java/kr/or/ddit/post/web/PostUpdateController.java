package kr.or.ddit.post.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.afile.service.AfileService;
import kr.or.ddit.afile.service.IAfileService;
import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.BoardStatusList;
import kr.or.ddit.util.FileuploadUtil;

@WebServlet("/updatePost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostDeleteController.class);
       
	private IPostService postService;
	private IAfileService afileService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		afileService = new AfileService();
		boardService = new BoardService();
	}
	
	//수정으로 넘어가기위한 get 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		
		String pstNo = request.getParameter("postNo");
		int postNo = pstNo == null ? 0 : Integer.parseInt(pstNo);
		
		Post postdt = postService.getPostDetail(postNo);
		int boardNo = postdt.getBoardNo();
		logger.debug("update controller boardNo {}", boardNo);

		Board boarddt = boardService.getBoardInfo(boardNo);
		String boardNm = boarddt.getBoardNm();
		
		List<AFile> afList = afileService.getAttachedFile(postNo);
		
		request.setAttribute("postNo", postNo);
		request.setAttribute("postdt", postdt);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("afList", afList);
		request.setAttribute("stBoardList", stBoardList);

		
		request.getRequestDispatcher("post/postUpdate.jsp").forward(request, response);
		
	}
	
	
	//수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		   
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		request.setAttribute("stBoardList", stBoardList);
		
		String smarteditor = request.getParameter("smarteditor");
		logger.debug(" detail.doPost -> boardNO {}", request.getParameter("boardNo"));
		
		String boardNumber = request.getParameter("boardNo");
		if (boardNumber == null) {
			boardNumber = "0";
		}
		int boardNo = Integer.parseInt(boardNumber);
		
		User user = (User) request.getSession().getAttribute("S_USERVO");
		String userId = user.getUserId();
		if(userId == null || userId.length() == 0) userId = "brown";
		
		String postTitle = request.getParameter("postTitle");
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		
		
		
		Post post = new Post();

		post.setPostTitle(postTitle);
		post.setPostContent(smarteditor);
		post.setPostNo(postNo);
		
		postService.updatePost(post);
		
		logger.debug("postNo updatePost = > {} ", postNo);
		
//		try {
			//사용자가 파일을 업로드 한 경우
			String filename = "";
			String path = "";

			Collection<Part> files = request.getParts();
			for(Part picture : files) {
				if("picture".equals(picture.getName())) {
					
					if(picture.getSize() > 0) {
						filename = FileuploadUtil.getFilename(picture.getHeader("Content-Disposition"));	//사용자가 업로드한 파일
						String realFilename = UUID.randomUUID().toString();
						String ext = FileuploadUtil.getFileExtension(picture.getHeader("Content-Disposition"));
						path = FileuploadUtil.getPath() + realFilename + ext;
						
						picture.write(path);
						
						AFile afile = new AFile();
						afile.setFilename(filename);
						afile.setRealfilename(path);
						afile.setPostNo(postNo);
						
						int finsertCnt = 0;

						finsertCnt = afileService.insertFile(afile);
						logger.debug("postInsertController insertCnt {}", finsertCnt);
					}
				}
			}
			response.sendRedirect(request.getContextPath() + "/postList?boardNo=" + boardNo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.getRequestDispatcher("/main.jsp").forward(request, response);
//		} // catch
		
	}

}
