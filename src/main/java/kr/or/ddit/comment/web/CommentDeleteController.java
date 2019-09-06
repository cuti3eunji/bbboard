package kr.or.ddit.comment.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.comment.service.CommentService;
import kr.or.ddit.comment.service.ICommentService;

@WebServlet("/removeCmt")
public class CommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommentDeleteController.class);
    
	private ICommentService cmtService;
	
	@Override
	public void init() throws ServletException {
		cmtService = new CommentService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		logger.debug("delete cmt - postNo {} ", postNo);
		
		
		int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));
		logger.debug("delete cmt - cmtNo {} ", cmtNo);
		
		int deleteCnt = cmtService.deleteCmt(cmtNo);
		
		try {
			if (deleteCnt == 1) {
				// 서버 상의 상태가 바뀔때는 redirect를 사용한다. -중복방지
				response.sendRedirect(request.getContextPath() + "/postDetail?postNo="+ postNo);
			}
			// 실패 시 : 메인으로
			else {
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} // catch	
	}

}
