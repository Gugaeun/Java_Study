package springmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import springmvc.model.Member;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;

@Repository
public class MemberDao {
	private JdbcTemplate jdbcTemplate;	// Database를 사용할 컨넥션 라이브러리
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
     * 회원 조회(단건)
     */
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
        	"SELECT * FROM member WHERE email = ?",
        	new RowMapper<Member>() {
        		@Override
        		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        			Member member = new Member(
        					rs.getString("email"),
        					rs.getString("password"),
        					rs.getString("name"),
        					rs.getTimestamp("register_datetime").toLocalDateTime());
        			member.setId(rs.getLong("id"));
        			
        			return member;
        		}
        	}, email);
        
        return results.isEmpty() ? null : results.get(0);
    }
    
    /**
     * 회원 가입
     */
    public Member insert(Member member) {
    	KeyHolder keyHolder = new GeneratedKeyHolder();
    	int rowCount = jdbcTemplate.update(new PreparedStatementCreator() {
    		@Override
    		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO member (email, password, name, register_datetime) " + 
					"VALUES (?, ?, ?, ?)", new String[] { "id" });
    			// ? 인덱스 파라미터 값 설정
    			pstmt.setString(1, member.getEmail());
    			pstmt.setString(2, member.getPassword());
    			pstmt.setString(3, member.getName());
    			pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDatetime()));
    			
    			return pstmt;
    		}
    	}, keyHolder);
    	
    	Number keyValue = keyHolder.getKey();
    	member.setId(keyValue.longValue());	// db에서 부여한 회원번호(id)
    	
    	return member;
    }
    
    /**
     * 회원 정보 수정
     */
    public int update(Member member) {
    	int rowAffectedCount = jdbcTemplate.update(
			"UPDATE member SET name = ?, password = ? WHERE email = ?",
			member.getName(), member.getPassword(), member.getEmail()
		);
    	
    	return rowAffectedCount;
    }
    
    /**
     * 회원 정보 리스트
     */
    public List<Member> selectAll() {
    	List<Member> results = jdbcTemplate.query(
    			"SELECT get_mem FROM member", (ResultSet rs, int rowNum) -> {
    				Member member = new Member(
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getTimestamp("register_datetime").toLocalDateTime());
    				member.setId(rs.getLong("id"));
    				return member;
    			}
		);
    	
    	return results;
    }
    
    public long count() {
    	Long memberCount = jdbcTemplate.queryForObject(
			"SELECT count(*) FROM member", Long.class
		);
    	
    	return memberCount;
    }
}

