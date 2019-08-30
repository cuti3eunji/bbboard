package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

public class UserDao implements IUserDao{

	/**
	* Method : getUserList
	* 작성자 : 
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList(SqlSession sqlSession) {
		return sqlSession.selectList("user.getUserList");
	}

	/**
	* Method : getUser
	* 작성자 : 
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : userId를 갖는 사용자 정보 조히
	*/
	@Override
	public User getUser(SqlSession sqlSession, String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

	@Override
	public List<User> getUserListOnlyHalf(SqlSession sqlSession) {
		return sqlSession.selectList("user.getUserListOnlyHalf");
	}

	/**
	* Method : getUserPagingList
	* 작성자 : 
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public List<User> getUserPagingList(SqlSession sqlSession, Page page) {
		return sqlSession.selectList("user.getUserPagingList", page);
	}
	
	/**
	* Method : getUserTotalCnt
	* 작성자 : 
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 사용자 건수 조회 
	*/
	@Override
	public int getUserTotalCnt(SqlSession sqlSession) {
		return sqlSession.selectOne("user.getUserTotalCnt");
	}
	/**
	* Method : insertUser
	* 작성자 : 
	* 변경이력 :
	* @param sqlSession
	* @param user
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(SqlSession sqlSession, User user) {
		return sqlSession.insert("user.insertUser", user);
	}
	
	/**
	* Method : deleteUser
	* 작성자 : PC-05
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(SqlSession sqlSession, String userId) {
		return sqlSession.delete("user.deleteUser", userId);
	}

	/**
	* Method : updateUser
	* 작성자 : PC-14
	* 변경이력 :
	* @param sqlSession
	* @param user
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	@Override
	public int updateUser(SqlSession sqlSession, User user) {
		return sqlSession.update("user.updateUser", user);
	}
	
}
