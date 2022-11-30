package edu.pnu.dao.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberInterface {
	
	private static final Logger log = LoggerFactory.getLogger(MemberDaoH2Impl.class);

	private JdbcTemplate jdbcTemplate;
	
	public MemberDaoH2Impl() {
		log.info("MemberDaoH2Impl() 생성자 호출됨.");
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		log.info("MemberDaoH2Impl - setJdbcTemplate() 호출됨.");
	}
	
	@Override
	public Map<String, Object> getMembers() {
		String sqlString = "select * from member order by id asc";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			List<MemberVO> list = jdbcTemplate.query(sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
			ret.put("data", list);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		Map<String, Object> ret = new HashMap<>();
		String sqlString = String.format("select * from member where id=%d", id);
		ret.put("sql", sqlString);
		try {
			MemberVO member = jdbcTemplate.queryForObject(sqlString, new BeanPropertyRowMapper<MemberVO>(MemberVO.class));
			ret.put("data", member);
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		String sqlString = "insert into member (id, pass, name) values (?,?,?)";
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		int id;
		try {
			id = jdbcTemplate.queryForObject("select max(id) from member", Integer.class) + 1;
		} catch (Exception e) {
			ret.put("data", null);
			return ret;
		}
		try {
			if (jdbcTemplate.update(sqlString, id, member.getPass(), member.getName()) != 0) {
				Map<String, Object> map = getMember(id);
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("data", null);
			}
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		String sqlString = String.format("update member set pass='%s', name='%s' where id=%d",
				member.getPass(), member.getName(), member.getId());
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);
		try {
			if (jdbcTemplate.update(sqlString) != 0) {
				Map<String, Object> map = getMember(member.getId());
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("data", null);
			}
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		String sqlString = String.format("delete from member where id=%d", id);
		Map<String, Object> ret = new HashMap<>();
		ret.put("sql", sqlString);

		try {
			Map<String, Object> map = getMember(id);
			if (map.get("data") != null && jdbcTemplate.update(sqlString) != 0) {
				ret.put("data", map.get("data"));
			}
			else {
				ret.put("data", null);
			}
		} catch (Exception e) {
			ret.put("data", null);
		}
		return ret;
	}
}
