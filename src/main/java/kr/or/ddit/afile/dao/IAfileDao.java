package kr.or.ddit.afile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.afile.model.AFile;

public interface IAfileDao {
	/**
	* Method : getAttachedFile
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 첨부파일 출력
	*/
	public List<AFile> getAttachedFile(SqlSession sqlSession, int postNo);
	
	
	/**
	* Method : insertFile
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param afile
	* @return
	* Method 설명 : 파일 업로드
	*/
	public int insertFile(SqlSession sqlSession, AFile afile);
	
	/**
	* Method : updateFile
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param afile
	* @return
	* Method 설명 : 수정
	*/
	public int updateFile(SqlSession sqlSession, AFile afile);
	
	/**
	 * Method : deleteFile
	 * 작성자 : 박은지
	 * 변경이력 :
	 * @param sqlSession
	 * @param afile
	 * @return
	 * Method 설명 : 수정
	 */
	public int deleteFile(SqlSession sqlSession, int fileNo);
	
	public AFile selectFile(SqlSession sqlSession, int fileNo);
}
