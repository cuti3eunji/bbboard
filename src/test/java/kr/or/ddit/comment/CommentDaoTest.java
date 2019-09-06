package kr.or.ddit.comment;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.comment.dao.CommentDao;
import kr.or.ddit.comment.dao.ICommentDao;
import kr.or.ddit.comment.model.Comment;
import kr.or.ddit.util.MybatisUtil;

public class CommentDaoTest {

	private ICommentDao cmtDao;
	private SqlSession sqlSession;
	
	
	@Before
	public void setup() {
		cmtDao = new CommentDao();
		sqlSession = MybatisUtil.getSession();
	}

	@After
	public void tearDown() {
		sqlSession.close();
	}
	
	
	@Test
	public void getCmtListTest() {
		/***Given***/
		int postNo = 1;

		/***When***/
		List<Comment> list = cmtDao.getCmtList(sqlSession, postNo);

		/***Then***/
		assertEquals(13, list.size());
	}
	
	
	@Test
	public void insertCmtTest() {
		/***Given***/
		Comment cmt = new Comment();
		cmt.setCommentContent("test");
		cmt.setPostNo(4);
		cmt.setUserId("sally");

		/***When***/
		int insertCnt = cmtDao.insertCmt(sqlSession, cmt);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteCmtTest() {
		/***Given***/
		int commentNo = 5;

		/***When***/
		int deleteCnt = cmtDao.deleteCmt(sqlSession, commentNo);

		/***Then***/
		assertEquals(1, deleteCnt);
		
	}
	
}
