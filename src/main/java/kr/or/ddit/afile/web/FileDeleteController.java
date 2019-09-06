package kr.or.ddit.afile.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.afile.service.AfileService;
import kr.or.ddit.afile.service.IAfileService;

/**
 * Servlet implementation class FileDelete
 */
@WebServlet("/delfile")
public class FileDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FileDeleteController.class);
	
	private IAfileService afileService;
	
	@Override
	public void init() throws ServletException {
		afileService = new AfileService();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fileNo = Integer.parseInt(request.getParameter("fileNo"));
		logger.debug("fileno deleteFile => {}", fileNo);
		
		int deleteCnt = afileService.deleteFile(fileNo);
		logger.debug("delCnt {}", deleteCnt);
		
		response.sendRedirect(request.getContextPath() + "/updatePost");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
