package com.teamtrade.rfp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	public AppTest() {
//		Session session  = HibernateUtil.getSessionFactory().openSession(); 
//		session.beginTransaction();
//		
//		Person p = new Person();
//		p.setCivility(new Civility("Mr"));
//		//p.setActorType(new ActorType("person"));
//		p.setFirstname("Marie");
//		p.setLastname("PAGE");
//		p.setFunction("IT Consultant");
//		p.setTeamtradeAppreciation(new TeamtradeAppreciation("Favorable"));
//		
//		session.persist(p);
//	    session.getTransaction().commit();
//		System.out.println("Done");
		
		A a = new A();
		
		if(a instanceof I){
		}
	}
	class A{}
	class B implements I{
		@Override
		public void testMethod(ArrayList<A> myList) {
		}
	}
	interface I {
	     void testMethod(ArrayList<A> myList);
	}


	public static void main(String[] args) {
		final int var = 3;
		
		System.out.println(var);

	}


	private static void test() throws Exception{
		throw new IOException("Une exception IO s'est produite");
	}
    
}
