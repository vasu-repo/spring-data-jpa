package demo.datajpa.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import demo.datajpa.repositories.SpeakerJpaRepository;

@SpringBootTest
public class SpeakerTest {
    @Autowired
    private SpeakerJpaRepository repository;

  
    @Test
    public void testFind() throws Exception {
        Speaker speaker = repository.getOne(1L);
        assertNotNull(speaker);
    }
   
    @Test
    @Transactional
    public void testSaveAndGetAndDelete() throws Exception {
        Speaker s = new Speaker();
        s.setCompany("CDAC");
        s.setFirstName("Alice");
        s.setLastName("Arizona");
        s.setTitle("Author");
        s.setSpeakerBio("Spring development experience");
        s = repository.saveAndFlush(s);
        Speaker otherSpeaker = repository.getOne(s.getSpeakerId());
        assertEquals("Alice", otherSpeaker.getFirstName());

        repository.deleteById(otherSpeaker.getSpeakerId());
    }
    
    @Test
    public void testJpaAnd() {
    	List<Speaker> speakers = repository.findByFirstNameAndLastName("Sergio", "Becker");
    	assertTrue(speakers.size()>0);
    }
    @Test
    public void testJpaOr() {
    	List<Speaker> speakers = repository.findByFirstNameOrLastName("ZSergio", "Becker");
    	assertTrue(speakers.size()>0);
    }
    
    @Test
    public void testNull() {
    	List<Speaker> speakers = repository.findBySpeakerPhotoNull();
    	assertTrue(speakers.size()>0);
    }
    
    @Test
    public void testIsNull() {
    	List<Speaker> speakers = repository.findBySpeakerPhotoIsNull();
    	assertTrue(speakers.size()>0);
    }
    @Test
    public void testNotNull() {
    	List<Speaker> speakers = repository.findBySpeakerPhotoNotNull();
    	assertFalse(speakers.size()>0);
    }
    
    @Test
    public void testIsNotNull() {
    	List<Speaker> speakers = repository.findBySpeakerPhotoIsNotNull();
    	assertFalse(speakers.size()>0);
    }
    
    

	
	 @Test
	    public void testIn() {
	    	List<Speaker> list = repository.findByCompanyIn( Arrays.asList("National Bank", "Adventureworks"));
	       // System.out.println(list.size() +" In");
	        assertTrue(list.size()>0);
	    }
	  
	 @Test
	    public void testNotIn() {
	    	List<Speaker> list = repository.findByCompanyNotIn(Arrays.asList("National Bank", "Adventureworks"));
	      	assertTrue(list.size()>0);
	    }
	 	
		 @Test
		    public void testIgnoreCase() {
		    	List<Speaker> list = repository.findByCompanyIgnoreCase( "national bank");
		       // System.out.println(list.size() +" In");
		        assertTrue(list.size()>0);
		    }
		  
		 @Test
		    public void testContainsIgnoreCase() {
		    	List<Speaker> list = repository.findByCompanyContainsIgnoreCase("bank");
		      	assertTrue(list.size()>0);
		    }
		 
		
}
