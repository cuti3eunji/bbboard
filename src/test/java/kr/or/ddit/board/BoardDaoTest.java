package kr.or.ddit.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.Board;
import kr.or.ddit.util.MybatisUtil;

public class BoardDaoTest {
	
	private IBoardDao boardDao;
	private SqlSession sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	// test에 공통적으로 필요한 자원을 생성 / 초기화
	@Before
	public void setup() {
		logger.debug("before");
		boardDao = new BoardDao();
		sqlSession = MybatisUtil.getSession();
	}

	// test에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
		logger.debug("after");
		sqlSession.close();
	}

	/**
	* Method : getAllBoardListTest
	* 작성자 : 박은지
	* 변경이력 :
	* Method 설명 : 전체 Board List 테스트
	*/
	@Test
	public void getAllBoardListTest() {
		/***Given***/

		/***When***/
		List<Board> boardList = boardDao.getAllBoardList(sqlSession);

		/***Then***/
		assertEquals(6, boardList.size());
	}
	
	/**
	* Method : getBoardStatusListTest
	* 작성자 : 박은지
	* 변경이력 :
	* Method 설명 : 게시판 사용여부에 따른 Board List 테스트
	* 				=> 사용여부가 1(사용가능)인 boardList 갯수 확인
	*/
	@Test
	public void getBoardStatusListTest() {
		/***Given***/
		

		/***When***/
		List<Board> boardList = boardDao.getBoardStatusList(sqlSession, 1);

		/***Then***/
		assertEquals(3, boardList.size());
	}
	
	
	
	
	
	

}
