package zzyzzy.spring.mvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

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

import zzyzzy.spring.mvc.vo.MemberVO;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;
	private NamedParameterJdbcTemplate jdbcNameTemplate;
	
	//private RowMapper<MemberVO> memberMapper = 
	//	BeanPropertyRowMapper.newInstance(MemberVO.class);
			
	public MemberDAOImpl(DataSource dataSource) {
		simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
		.withTableName("member")
		.usingColumns("userid", "passwd", "name", "email");
		
		jdbcNameTemplate = 
				new NamedParameterJdbcTemplate(dataSource);		
	}
	
	@Override
	public int insertMember(MemberVO mvo) {
		SqlParameterSource params = 
				new BeanPropertySqlParameterSource(mvo);
		
		return simpleJdbcInsert.execute(params);
	}

	@Override
	public MemberVO selectOneMember() {
		String sql = 
			" select userid,name,email,regdate from member "
			+ " where mno = 1 ";
		
		RowMapper<MemberVO> memberMapper =
				new MemberRowMapper();
		
		return jdbcTemplate.queryForObject(
								sql, null, memberMapper);
	}
	
	// 콜백 메서드 정의 : mapRow
	private class MemberRowMapper 
					implements RowMapper<MemberVO> {

		@Override
		public MemberVO mapRow(ResultSet rs, int num) throws SQLException {
			MemberVO m = new MemberVO();
			
			m.setUserid(rs.getString("userid"));
			m.setName(rs.getString("name"));
			m.setEmail(rs.getString("email"));
			m.setRegdate(rs.getString("regdate"));
			
			return m;
		}
		
	}

}
