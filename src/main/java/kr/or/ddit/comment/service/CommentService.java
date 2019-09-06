package kr.or.ddit.comment.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comment.dao.CommentDao;
import kr.or.ddit.comment.dao.ICommentDao;
import kr.or.ddit.comment.model.Comment;
import kr.or.ddit.util.MybatisUtil;

public class CommentService implements ICommentService {
	
	private ICommentDao cmtDao;
	
	public CommentService() {
		cmtDao = new CommentDao();
	}
	
	
	@Override
	public List<Comment> getCmtList(int postNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<Comment> list = cmtDao.getCmtList(sqlSession, postNo);
		sqlSession.close();
		return list;
	}

	@Override
	public int insertCmt(Comment comm) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = cmtDao.insertCmt(sqlSession, comm);
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updateCmt(Comment comm) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = cmtDao.updateCmt(sqlSession, comm);
		
		sqlSession.commit();
		sqlSession.close();
		
		return updateCnt;	}

	@Override
	public int deleteCmt(int commentNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCmt = cmtDao.deleteCmt(sqlSession, commentNo);
		
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCmt;	
	}

	@Override
	public String getMyCmt(int commentNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		String userId = cmtDao.getMyCmt(sqlSession, commentNo);
		sqlSession.close();
		
		return userId;
	}

}
