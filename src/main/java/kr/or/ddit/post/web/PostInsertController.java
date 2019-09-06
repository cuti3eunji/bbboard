package kr.or.ddit.post.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;
import kr.or.ddit.user.model.User;
import kr.or.ddit.util.BoardStatusList;
import kr.or.ddit.util.FileuploadUtil;

@WebServlet("/insertPost")
@MultipartConfig(maxFileSize = 1024*1024*5, maxRequestSize = 1024*1024*5*5)
public class PostInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostInsertController.class);
	
	private IPostService postService;
	private IAfileService afileService;
       
	@Override
	public void init() throws ServletException {
		postService = new PostService();
		afileService = new AfileService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		   
		List<Board> stBoardList = BoardStatusList.stBoardList();
		
		request.setAttribute("stBoardList", stBoardList);
		
		String smarteditor = request.getParameter("smarteditor");
		logger.debug(" detail.doPost -> boardNO {}", request.getParameter("boardNo"));
		
		String boardNumber = request.getParameter("boardNo");
		int boardNo = boardNumber == null ? 0 : Integer.parseInt(boardNumber);
		
		User user = (User) request.getSession().getAttribute("S_USERVO");
		String userId = user.getUserId();
		
		if(userId == null || userId.length() == 0) userId = "brown";
		
		String postTitle = request.getParameter("postTitle");
		
		String parenNo = request.getParameter("parentNo");
		int parentNo = parenNo == null ? 0 : Integer.parseInt(parenNo);

		
		Post post = new Post();

		if(parentNo == 0) {		//일반 게시글 작성시
			post.setBoardNo(boardNo);
			post.setUserId(userId);
			post.setPostTitle(postTitle);
			post.setPostContent(smarteditor);
			logger.debug("post.일반작성 {}{}{}{}", post.getBoardNo(), post.getUserId(), post.getPostTitle(), post.getPostContent());
		}else {
			post.setBoardNo(boardNo);
			post.setParent_postNo(parentNo);
			post.setUserId(userId);
			post.setPostTitle(postTitle);
			post.setPostContent(smarteditor);
			logger.debug("post.insert doget 답글 {}{}{}{}{}", post.getBoardNo(), post.getUserId(), post.getPostTitle(), post.getPostContent(), post.getParent_postNo());
		}
		
		int insertCnt = postService.insertPost(post);
		logger.debug("insertCnt  =>{} ", insertCnt);
		
		int postNo = postService.postSeq();
		logger.debug("postNo insertPost = > {} ", postNo);
		
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
			response.sendRedirect(request.getContextPath() + "/postList?boardNo=" + post.getBoardNo());
//		} catch (Exception e) {
//			e.printStackTrace();
//			request.getRequestDispatcher("/main.jsp").forward(request, response);
//		} // catch
		
		
	}

}
