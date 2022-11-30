package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.log.LogDao;
import edu.pnu.dao.member.MemberInterface;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private MemberInterface memberDao;
	private LogDao logDao;

	public MemberService() {
		log.info("MemberService() 생성자 호출됨.");
	}

	@Autowired
	public void setMemberInterface(MemberInterface memberDao) {
		this.memberDao = memberDao;
		log.info("MemberService - setMemberInterface() 호출됨.");
	}
	
	@Autowired
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
		log.info("MemberService - setLogDao() 호출됨.");
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String, Object> map = memberDao.getMembers();
		List<MemberVO> list = (List<MemberVO>) map.get("data");
		if (list != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return list;
	}

	public MemberVO getMember(Integer id) {
		Map<String, Object> map = memberDao.getMember(id);
		MemberVO member = (MemberVO) map.get("data");
		if (member != null)	logDao.addLog("get", (String)map.get("sql"), true);
		else				logDao.addLog("get", (String)map.get("sql"), false);
		return member;
	}

	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("post", (String)map.get("sql"), true);
		else			logDao.addLog("post", (String)map.get("sql"), false);
		return m;		
	}

	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("put", (String)map.get("sql"), true);
		else			logDao.addLog("put", (String)map.get("sql"), false);	
		return m;
	}

	public MemberVO deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		MemberVO m = (MemberVO) map.get("data");
		if (m != null)	logDao.addLog("delete", (String)map.get("sql"), true);
		else			logDao.addLog("delete", (String)map.get("sql"), false);
		return m;
	}
}
