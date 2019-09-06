package kr.or.ddit.afile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.afile.dao.AfileDao;
import kr.or.ddit.afile.dao.IAfileDao;
import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.util.MybatisUtil;

public class AfileDaoTest {
	private IAfileDao afileDao;
	private SqlSession sqlSession;
	
	@Before
	public void setup() {
		afileDao = new AfileDao();
		sqlSession = MybatisUtil.getSession();
	}

	@After
	public void tearDown() {
		sqlSession.close();
	}

	@Test
	public void getAttachedFileTest() {
		/***Given***/
		int postNo = 1;

		/***When***/
		List<AFile> afile = afileDao.getAttachedFile(sqlSession, postNo);

		/***Then***/
		assertEquals(3, afile.size());
	}
	
	@Test
	public void insertFileTest() {
		/***Given***/
		AFile afile = new AFile();
		afile.setFilename("test.png");
		afile.setRealfilename("real");
		afile.setPostNo(52);

		/***When***/
		int insertCnt = afileDao.insertFile(sqlSession, afile);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteFileTest() {
		/***Given***/
		int fileNo = 27;

		/***When***/
		int deleteCnt = afileDao.deleteFile(sqlSession, fileNo);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
}
