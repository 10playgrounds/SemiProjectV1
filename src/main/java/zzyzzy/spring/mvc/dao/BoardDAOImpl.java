package zzyzzy.spring.mvc.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import zzyzzy.spring.mvc.vo.BoardVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired  // bean태그에 정의한 경우 생략가능
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleInsert;
	private NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	private RowMapper<BoardVO> boardMapper = 
		BeanPropertyRowMapper.newInstance(BoardVO.class);
	
	public BoardDAOImpl(DataSource dataSource) {
		simpleInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("board")
			.usingColumns("title","userid","contents");
		
		jdbcNamedTemplate = 
			new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public int insertBoard(BoardVO bvo) {
		SqlParameterSource params = 
			new BeanPropertySqlParameterSource(bvo);
		
		return simpleInsert.execute(params); 
	}

	@Override
	public List<BoardVO> selectBoard(int snum) {
		String sql = 
		" select bno,title,userid,regdate,views from board "
		+ " order by bno desc limit :snum, 25";
		
		Map<String, Object> params = new HashMap<>();
		params.put("snum", snum);
		
		return jdbcNamedTemplate.query(sql, 
							params, boardMapper);
	}

	@Override
	public BoardVO selectOneBoard(String bno) {
		// 본문글에 대한 조회수 증가시키기
		String sql = " update board set views = views + 1 "
				   + " where bno = ? ";
		Object[] param = { bno };
		jdbcTemplate.update(sql, param);
		
		// 본문글 가져오기
		sql = "select * from board where bno = ?";
		return jdbcTemplate
				.queryForObject(sql, param, boardMapper);
	}

}
