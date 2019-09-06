package kr.or.ddit.util;

import java.util.List;

import kr.or.ddit.board.model.Board;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

public class BoardStatusList {
	
	private static IBoardService boardService;
	
	static {
		boardService = new BoardService();
	}
	
	public static List<Board> stBoardList() {
		List<Board> boardList = boardService.getBoardStatusList(1);
		return boardList;
	}
}

	