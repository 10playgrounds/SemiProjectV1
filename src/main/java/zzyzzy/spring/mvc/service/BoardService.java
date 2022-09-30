package zzyzzy.spring.mvc.service;

import java.util.List;

import zzyzzy.spring.mvc.vo.BoardVO;

public interface BoardService {

	boolean newBoard(BoardVO bvo);

	List<BoardVO> readBoard();

}




