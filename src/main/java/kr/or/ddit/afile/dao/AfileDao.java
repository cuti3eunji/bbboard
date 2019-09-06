package kr.or.ddit.afile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.afile.model.AFile;

public class AfileDao implements IAfileDao {

	/**
	* Method : getAttachedFile
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 첨부파일
	*/
	@Override
	public List<AFile> getAttachedFile(SqlSession sqlSession, int postNo) {
		return sqlSession.selectList("afile.getAttachedFile", postNo);
	}

	@Override
	public int insertFile(SqlSession sqlSession, AFile afile) {
		return sqlSession.insert("afile.insertFile", afile);
	}

	@Override
	public int updateFile(SqlSession sqlSession, AFile afile) {
		return sqlSession.update("afile.updateFile", afile);
	}

	@Override
	public int deleteFile(SqlSession sqlSession, int fileNo) {
		return sqlSession.delete("afile.deleteFile", fileNo);
	}

	@Override
	public AFile selectFile(SqlSession sqlSession, int fileNo) {
		return sqlSession.selectOne("afile.selectFile", fileNo);
	}
	
	

}
