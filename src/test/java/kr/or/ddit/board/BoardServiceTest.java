package kr.or.ddit.board;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

public class BoardServiceTest {
	
	private IBoardService boardService;
	
	@Before
	public void setup() {
		boardService = new BoardService();
	}

	@Test
	public void getAllBoardListTest() {
		/***Given***/
		
		/***When***/
		List<Board> boardList = boardService.getAllBoardList();

		/***Then***/
		assertEquals(10, boardList.size());
	}
	
	
	@Test
	public void getBoardStatusListTest() {
		/***Given***/
		int boardStatus = 1;

		/***When***/
		List<Board> list = boardService.getBoardStatusList(boardStatus);

		/***Then***/
		assertEquals(4, list.size());
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
		int insertCnt = boardService.insertBoard(bd);

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
		int updateCnt = boardService.updateBoard(board);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void getBoardInfoTest() {
		/***Given***/
		int boardNo = 1;

		/***When***/
		Board board = boardService.getBoardInfo(boardNo);

		/***Then***/
		assertEquals("자유게시판", board.getBoardNm());
	}
	
	
	

}
