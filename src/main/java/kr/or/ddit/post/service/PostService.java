package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.dao.PostDao;
import kr.or.ddit.post.model.Post;
import kr.or.ddit.util.MybatisUtil;

public class PostService implements IPostService {
	
	private IPostDao postDao;
	
	public PostService() {
		postDao = new PostDao();
	}
	
	@Override
	public List<Post> getPostPagingList(Map map) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<Post> list = postDao.getPostPagingList(sqlSession, map);
		sqlSession.close();
		return list;
	}

	@Override
	public Post getPostDetail(int postNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		Post post = postDao.getPostDetail(sqlSession, postNo);
		sqlSession.close();
		
		return post;
	}
	
	@Override
	public int insertPost(Post post) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int insertCnt = postDao.insertPost(sqlSession, post);
		
		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public int updatePost(Post post) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int updateCnt = postDao.updatePost(sqlSession, post);
		
		sqlSession.commit();
		sqlSession.close();
		
		return updateCnt;
	}

	@Override
	public int deletePost(int postNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int deleteCnt = postDao.deletePost(sqlSession, postNo);
		
		sqlSession.commit();
		sqlSession.close();
		
		return deleteCnt;
	}

	@Override
	public List<String> getMyPost(String userId) {
		SqlSession sqlSession = MybatisUtil.getSession();
		List<String> postNoList = postDao.getMyPost(sqlSession, userId);
		sqlSession.close();
		
		return postNoList;
	}

	@Override
	public int getPostTotalCnt(int boardNo) {
		SqlSession sqlSession = MybatisUtil.getSession();
		int totalCnt = postDao.getPostTotalCnt(sqlSession, boardNo);
		sqlSession.close();
		
		return totalCnt;
	}

	@Override
	public int postSeq() {
		SqlSession sqlSession = MybatisUtil.getSession();
		int postNo = postDao.postSeq(sqlSession);
		
		return postNo;
	}




}
