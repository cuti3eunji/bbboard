package kr.or.ddit.comment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.comment.model.Comment;
import kr.or.ddit.comment.service.CommentService;
import kr.or.ddit.comment.service.ICommentService;
import kr.or.ddit.user.model.User;

@WebServlet("/insertCmt")
public class CommentInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommentInsertController.class);
	
	private ICommentService cmtService;
	
	@Override
	public void init() throws ServletException {
		cmtService = new CommentService();
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("S_USERVO");
		String userId = user.getUserId();
		logger.debug("writePost userId 1111 {}", userId);
		
		if(userId == null || userId.length() == 0) userId = "brown";
		
		String postNo = request.getParameter("postNo");
		logger.debug("postNo commentInsert > {}", postNo);

		String cmtcnt = request.getParameter("commentContent");
		Comment comm = new Comment();
		comm.setCommentContent(cmtcnt);
		comm.setPostNo(Integer.parseInt(postNo));
		comm.setUserId(userId);
		
		
		int insertCnt = 0;
		insertCnt = cmtService.insertCmt(comm);
		
		logger.debug("commentInsertController insertCnt {}", insertCnt);
		
		try {
			if (insertCnt == 1) {
				// 서버 상의 상태가 바뀔때는 redirect를 사용한다. -중복방지
				response.sendRedirect(request.getContextPath() + "/postDetail?postNo="+postNo);
			}
			// 실패 시 : 메인으로
			else {
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} // catch	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
