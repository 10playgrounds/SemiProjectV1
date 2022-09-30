package zzyzzy.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zzyzzy.spring.mvc.dao.BoardDAO;
import zzyzzy.spring.mvc.vo.BoardVO;

@Service("bsrv")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO bdao;
	
	@Override
	public boolean newBoard(BoardVO bvo) {
		boolean isInsert = false;
		
		if (bdao.insertBoard(bvo) > 0)  isInsert = true;
		
		return isInsert;
	}

}
