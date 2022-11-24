package edu.pnu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.member.MemberInterface;

@SpringBootTest
class Mission4ApplicationTests {

	@Autowired
	MemberInterface dao;

	@Test
	void dbtest() {
	}
	
//	void dbtest() {
//		MemberInterface dao = new MemberDaoH2Impl();
//		MemberVO m = dao.getMember(1);
//		System.out.println(m);
//	}
	
	@Test
	void test01() {
		System.out.println("Test01");
	}

	@Test
	void test02() {
		System.out.println("Test02");
	}

	@Test
	void test03() {
		System.out.println("Test03");
	}
}