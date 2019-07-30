package cn.mldn.test.junit;


import java.util.Date;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.mldn.service.IMemberService;
import cn.mldn.util.factory.Factory;
import cn.mldn.vo.Member;
import junit.framework.TestCase;
@FixMethodOrder(value=MethodSorters.NAME_ASCENDING)
public class IMemberServiceTest {
	private static int rand = 0;
	static {
		rand = new Random().nextInt(99999);
	}
	@Test
	public void test1Add() throws Exception {
		IMemberService memberService = Factory.getServiceInstance("member.service");
		Member vo = new Member();
		vo.setMid("mldnjava-"+rand);
		vo.setName("小王"+rand);
		vo.setSalary(1.1);
		vo.setBirthday(new Date());
		vo.setNote("是个人");
		vo.setDel(1);	//故意设置一个错误
		TestCase.assertTrue(memberService.add(vo));
	}

}
