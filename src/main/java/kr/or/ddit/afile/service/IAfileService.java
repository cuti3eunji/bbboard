package kr.or.ddit.afile.service;

import java.util.List;

import kr.or.ddit.afile.model.AFile;

public interface IAfileService {
	
	public List<AFile> getAttachedFile(int postNo);
	
	public int insertFile(AFile afile);

	
	/**
	 * Method : deleteFile
	 * 작성자 : 박은지
	 * 변경이력 :
	 * @param sqlSession
	 * @param afile
	 * @return
	 * Method 설명 : 삭제
	 */
	public int deleteFile(int fileNo);
	
	public AFile selectFile(int fileNo);

}
