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

import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

@WebServlet("/updatePost")
@MultipartConfig()
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(PostDeleteController.class);
       
	private IPostService postService;
	
	@Override
	public void init() throws ServletException {
		postService = new PostService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		
		Post post = postService.getPostDetail(postNo);
		
		int updateCnt = 0;
		
		updateCnt = postService.updatePost(post);
		if(updateCnt == 1) {
			//성공
			response.sendRedirect(request.getContextPath() + "/postDetail");
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
