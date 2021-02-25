package demo.datajpa.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import demo.datajpa.repositories.SessionsJpaRepository;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionsJpaRepository repository;

    @Test
    public void test1() {
    	Session session=repository.findFirstBySessionDescriptionContains("Amazon");
    	//System.out.println("is null :"+session==null);
    	//if(session!=null)System.out.println(session.getSessionDescription());
    	assertNull(session);
    	
    }
    
    @Test
    public void test2ndCountOne() {
    	Long count=repository.countBySessionDescriptionContains("test");
    	assertTrue(count==1);
    	
    }
    @Test
    public void test2ndCountNonZero() {
    	Long count=repository.countBySessionDescriptionContains("test");
    	assertTrue(count>0);
    	
    }
    @Test
    public void test2ndCountZero() {
    	Long count=repository.countBySessionDescriptionContains("test-");
    	assertTrue(count==0);
    	
    }
   
    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.findAll();
        System.out.println(" findall count :"+ sessions.size());
        assertTrue(sessions.size() > 0);
    }
    
    @Test
    public void testCount() {
    	Long countBySessionNameContains = repository.countBySessionNameContains("KeyNote");
    	
    	//assertTrue(countBySessionNameContains==0);
    	assertFalse(countBySessionNameContains>0);
    }
    @Test
    public void testFindFirst() {
    	Session findFirstBySessionNameContains = repository.findFirstBySessionNameContains("Keynote");
      	assertTrue(findFirstBySessionNameContains !=null);
    }
    @Test
    public void testSessionNameContains() {
    	List<Session> list = repository.findBySessionNameContains("Keynote");
      	assertTrue(list.size()==2);
    }
    
	
	  @Test public void testEquals() { 
		  List<Session> list =repository.findBySessionLengthIs(31);

	 // repository.findBySessionLength(30); 
	  assertFalse(list.size()>0);
	   
	  }
	 
	 
    
    @Test
    public void testNotEquals() {
    	List<Session> list = repository.findBySessionLengthNot(30);
      	assertTrue(list.size()>0);
    }
    
    @Test
    public void testLike() {
    	List<Session> list = repository.findBySessionNameLike("Key%");
      //	assertFalse(list.size()>0);
      	assertTrue(list.size()>0);

    }
    @Test
    public void testNotLike() {
    	List<Session> list = repository.findBySessionNameNotLike("Java%");
      	assertTrue(list.size()>0);
    }
    

	  @Test
	    public void testStartingWith() {
	    	List<Session> list = repository.findBySessionNameStartingWith("Key");//Key%
	    	System.out.println(list.size());
	      	assertTrue(list.size()>0);
	    }
	  @Test
	    public void testEndingWith() {
	    	List<Session> list = repository.findBySessionNameEndingWith("AOP");//%AOP
	      	assertTrue(list.size()>0);
	    }
	    
	  @Test
	    public void testContains() {
	    	List<Session> list = repository.findBySessionNameContains("Java");//%java%
	      	assertTrue(list.size()>0);
	    }


		 @Test
		    public void testLessThan() {
		    	List<Session> list = repository.findBySessionLengthLessThan(30);
		    	assertFalse(list.size()>0);
		    }
		  
		 @Test
		    public void testLessThanEquals() {
		    	List<Session> list = repository.findBySessionLengthLessThanEqual(30);
		      	assertTrue(list.size()>0);
		    }
		  
		 @Test
		    public void testGreaterThan() {
		    	List<Session> list = repository.findBySessionLengthGreaterThan(60);
		      	assertFalse(list.size()>0);
		    }
		  
		 @Test
		    public void testGreaterThanEquals() {
		    	List<Session> list = repository.findBySessionLengthGreaterThanEqual(60);
		      	assertTrue(list.size()>0);
		    }
		  
		 @Test
		    public void testIn() {
		    	List<Session> list = repository.findBySessionLengthIn( Arrays.asList(30, 60));
		        System.out.println(list.size() +" In");
		        assertTrue(list.size()>0);
		    }
		  
		 @Test
		    public void testNotIn() {
		    	List<Session> list = repository.findBySessionLengthNotIn( Arrays.asList(30,45, 60));
		      	assertTrue(list.size()==0);
		    }
		  


			 @Test
			    public void testIgnoreCase() {
			    	List<Session> list = repository.findBySessionNameIgnoreCase("Spring Cloud Primer");
			        System.out.println(list.size() +" In");
			        assertTrue(list.size()>0);
			    }
			  
			 @Test
			    public void testContainsIgnoreCase() {
			    	List<Session> list = repository.findBySessionNameContainsIgnoreCase("keyNOTE");
			      	assertTrue(list.size()!=0);
			    }
			/* @Test
			    public void testOrderBy() {
			    	List<Session> list = repository.findBySessionLengthIsOrderBySessionLengthDescSessionNameAsc(60);
			        System.out.println(list.size());
			    	assertTrue(list.size()>0);
			    }
		
				 @Test
				    public void testFirst() {
				    	List<Session> list = repository.findFirstBySessionLengthIs(60);
				        System.out.println(list.size() +" FindOne");
				        assertTrue(list.size()==1);
				    }*/
				  
				 @Test
				    public void testTop5() {
				    	List<Session> list = repository.findTop5BySessionLengthIs(60);
				        System.out.println(list.size()+"- Top 5");

				      	assertTrue(list.size()<=5);
				    }
				 @Test
				    public void testDistinct() {
				    	List<Session> list = repository.findDistinctBySessionLengthIs(60);
				        System.out.println(list.size()+"- Distinct");
				    	assertTrue(list.size()>0);
				    }
				 
				 @Test
				    public void testPageble() {
				    	Page<Session> page = repository.getSessionWithName("S",PageRequest.of(1, 5,Sort.by(Sort.Direction.DESC,"sessionLength")));
				    	assertTrue(page.getTotalElements()>0);
				    }
				    
	    
}
