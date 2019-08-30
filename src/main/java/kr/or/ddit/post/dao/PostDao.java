package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.AFile;
import kr.or.ddit.post.model.Post;

public class PostDao implements IPostDao {
	

	/**
	* Method : getPostPagingList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param map
	* @return
	* Method 설명 : 해당 게시판의 게시글을 페이징 처리하여 전체 리스트 조회
	*/
	@Override
	public List<Post> getPostPagingList(SqlSession sqlSession, Map map) {
		return sqlSession.selectList("post.getPostPagingList", map);
	}
	
	/**
	* Method : getPostTotalCnt
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 :
	*/
	@Override
	public int getPostTotalCnt(SqlSession sqlSession) {
		return sqlSession.selectOne("post.getPostTotalCnt");
	}
	
	/**
	* Method : getPostDetail
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 게시글조회 
	*/
	@Override
	public Post getPostDetail(SqlSession sqlSession, int postNo) {
		return sqlSession.selectOne("post.getPostDetail", postNo);
	}
	
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
		return sqlSession.selectList("post.getAttachedFile", postNo);
	}

	
	/**
	* Method : insertPost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 작성
	*/
	@Override
	public int insertPost(SqlSession sqlSession, Post post) {
		return sqlSession.insert("post.insertPost", post);
	}

	/**
	* Method : updatePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	@Override
	public int updatePost(SqlSession sqlSession, Post post) {
		return sqlSession.update("post.updatePost", post);
	}
	
	/**
	* Method : deletePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 게시글 삭제 - 삭제여부 수정
	*/
	@Override
	public int deletePost(SqlSession sqlSession, int postNo) {
		return sqlSession.update("post.deletePost", postNo);
	}


}
