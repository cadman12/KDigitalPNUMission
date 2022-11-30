package edu.pnu.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.domain.MethodType;
import edu.pnu.domain.RequestLog;
import edu.pnu.persistence.MemberRepository;
import edu.pnu.persistence.RequestLogRepository;

@Service
public class MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberService.class);
	
	private MemberRepository memberRepo;
	private RequestLogRepository requestLogRepo;

	@PostConstruct
	public void init() {
		for (int i = 1 ; i <= 5 ; i++) {
			memberRepo.save(new Member("pass"+i, "name"+i));
		}
	}
	
	@Autowired
	public MemberService(MemberRepository memberRepo, RequestLogRepository requestLogRepo) {
		log.info("MemberService() 생성자 호출되면서 MemberRepository, RequestLogRepository Bean 객체가 Autowired 됨.");
		this.memberRepo = memberRepo;
		this.requestLogRepo = requestLogRepo;		
	}

	public List<Member> getMembers() {
		List<Member> list = memberRepo.findAll();;
		requestLogRepo.save(new RequestLog(MethodType.GET, "getMembers", true));
		return list;
	}

	public Member getMember(Long id) {
		Optional<Member> option = memberRepo.findById(id);
		Member member = option.get();
		if (member != null)
			requestLogRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%s)", member), true));
		else
			requestLogRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%d)", id), false));
		return member;
	}

	public Member addMember(Member member) {
		Member m = memberRepo.save(member);
		requestLogRepo.save(new RequestLog(MethodType.POST, String.format("addMember(%s)", member), true));
		return m;		
	}

	public Member updateMember(Member member) {
		Member m = memberRepo.save(member);
		requestLogRepo.save(new RequestLog(MethodType.POST, String.format("updateMember(%s)", member), true));
		return m;		
	}

	public Member deleteMember(Long id) {
		Optional<Member> option = memberRepo.findById(id);
		Member member = option.get();
		if (member != null) {
			memberRepo.deleteById(id);
			requestLogRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%d)", id), true));
		}
		else {
			requestLogRepo.save(new RequestLog(MethodType.GET, String.format("getMember(%d)", id), false));
		}
		return member;
	}
}
