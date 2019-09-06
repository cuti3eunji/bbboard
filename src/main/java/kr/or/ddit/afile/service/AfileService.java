package kr.or.ddit.afile.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.afile.dao.AfileDao;
import kr.or.ddit.afile.dao.IAfileDao;
import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.util.MybatisUtil;

public class AfileService implements IAfileService {
	private IAfileDao afileDao;
	
	public AfileService() {
		afileDao = new AfileDao();
	}
	
	@Override
	public List<AFile> getAttachedFile(int postNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<AFile> afile = afileDao.getAttachedFile(sqlSession, postNo);
		sqlSession.close();
		
		return afile;
	}

	@Override
	public int insertFile(AFile afile) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = afileDao.insertFile(sqlSession, afile);
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int deleteFile(int fileNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = afileDao.deleteFile(sqlSession, fileNo);
		
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public AFile selectFile(int fileNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		AFile afile = afileDao.selectFile(sqlSession, fileNo);
		sqlSession.close();
		
		return afile;
	}

}
