package kr.or.ddit.board;

import static org.junit.Assert.assertEquals;

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
	
	
	/**
	* Method : insertBoardTest
	* 작성자 : 박은지
	* 변경이력 :
	* Method 설명 : 게시판 생성 테스트
	*/
	@Test
	public void insertBoardTest() {
		/***Given***/
		Board bd = new Board();
		bd.setBoardNm("보드다오테스트");
		bd.setBoardStatus(1);
		bd.setUserId("brown");
		
		/***When***/
		int insertCnt = boardDao.insertBoard(sqlSession, bd);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	/**
	* Method : updateBoardTest
	* 작성자 : 박은지
	* 변경이력 :
	* Method 설명 :게시판 수정 테스트
	*/
	@Test
	public void updateBoardTest() {
		/***Given***/
		Board board = new Board();
		board.setBoardNm("수정board");
		board.setBoardStatus(0);
		board.setBoardNo(8);

		/***When***/
		int updateCnt = boardDao.updateBoard(sqlSession, board);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void getBoardInfoTest() {
		/***Given***/
		int boardNo = 1;

		/***When***/
		Board board = boardDao.getBoardInfo(sqlSession, boardNo);

		/***Then***/
		assertEquals("자유게시판", board.getBoardNm());
	}
	
	
	

}
