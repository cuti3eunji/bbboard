package kr.or.ddit.afile.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.afile.service.AfileService;
import kr.or.ddit.afile.service.IAfileService;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

/**
 * Servlet implementation class FileDownloadController
 */
@WebServlet("/fileDownload")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);
	
	private IPostService postService;
	private IAfileService afileService;
	

	@Override
	public void init() throws ServletException {
		postService = new PostService();
		afileService = new AfileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String fileNo = request.getParameter("fileNo");
		
		AFile afile = afileService.selectFile(Integer.parseInt(fileNo));
		
		ServletOutputStream sos = response.getOutputStream();
		
		File file = new File(afile.getRealfilename());
		
		FileInputStream fis = new FileInputStream(file);
		
		byte[] buff =new byte[512];
		int len = 0;
		
		while((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff,0,len);
		}
		
		fis.close();
	}

}
