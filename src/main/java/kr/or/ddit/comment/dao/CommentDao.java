package kr.or.ddit.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comment.model.Comment;

public class CommentDao implements ICommentDao {

	@Override
	public List<Comment> getCmtList(SqlSession sqlSession, int postNo) {
		return sqlSession.selectList("comment.getCmtList", postNo);
	}

	@Override
	public int insertCmt(SqlSession sqlSession, Comment comm) {
		return sqlSession.insert("comment.insertCmt", comm);
	}

	@Override
	public int updateCmt(SqlSession sqlSession, Comment comm) {
		return sqlSession.update("comment.updateCmt", comm);
	}

	@Override
	public int deleteCmt(SqlSession sqlSession, int commentNo) {
		return sqlSession.update("comment.deleteCmt",commentNo);
	}

	@Override
	public String getMyCmt(SqlSession sqlSession, int commentNo) {
		return sqlSession.selectOne("comment.getMyCmt", commentNo);
	}

}
