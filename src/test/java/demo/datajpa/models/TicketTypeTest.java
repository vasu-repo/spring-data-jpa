package demo.datajpa.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import demo.datajpa.repositories.TicketTypeJpaRepository;

@SpringBootTest
public class TicketTypeTest {
 
    @Autowired
    TicketTypeJpaRepository repository;
    

    @Test
    public void testFind() throws Exception {
    	TicketType ticket = repository.getOne("P");//C,P,S
        assertNotNull(ticket);
    }
    @Test
    public void testFindFailedCase() throws Exception {
    	//TicketType ticket = repository.getOne("B");//C,P,S
    	Optional<TicketType> ticket1 = repository.findById("B");//C,P,S
    	System.out.println(ticket1.isEmpty());
        assertTrue(ticket1.isEmpty());
    }
    
    @Test
    public void testTrue() throws Exception {
    	 List<TicketType> findByIncludesWorkshopTrue = repository.findByIncludesWorkshopTrue();
        assertTrue(findByIncludesWorkshopTrue.size()>0);
    }
    
    @Test
    public void testFalse() throws Exception {
    	 List<TicketType> findByIncludesWorkshopFalse = repository.findByIncludesWorkshopFalse();
         assertTrue(findByIncludesWorkshopFalse.size()>0);
    }
 
}
