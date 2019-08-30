package kr.or.ddit.util;

import java.util.List;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

public class BoardStatusList {
	
	private static List<Board> boardList;
	private static IBoardService boardService;
	
	static {
		boardService = new BoardService();
		boardList = boardService.getBoardStatusList(1);
	}
	
	
	public static List<Board> stBoardList() {
		return boardList;
	}
}
