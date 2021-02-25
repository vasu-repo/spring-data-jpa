package demo.datajpa.models;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import demo.datajpa.repositories.PriceCategoryJpaRepository;
import demo.datajpa.repositories.TicketPriceJpaRepository;
import demo.datajpa.repositories.TicketTypeJpaRepository;

@SpringBootTest
public class TicketPriceTest {
    @Autowired
    private TicketPriceJpaRepository repository;

  
    @Autowired
    TicketTypeJpaRepository ttRepository;
    

    @Test
    public void testFind() throws Exception {
        TicketPrice ticket = repository.getOne(1L);
        assertNotNull(ticket);
    }

 /*   @Test
    public void testQuery() throws Exception {
        List<TicketPrice> ticket =  repository.getTicketsUnderPriceWithWorksShops(BigDecimal.valueOf(1000L));
        assertTrue(ticket.size()>0);
    }*/
    
    @Test
    public void testQueryParam() throws Exception {
        List<TicketPrice> ticket =  repository.getTicketsUnderPriceWithWorksShopsWithParam(BigDecimal.valueOf(1000L));
        assertTrue(ticket.size()>0);
       }
    
    @Test
    public void testNamedQuery() throws Exception {
        List<TicketPrice> ticket =  repository.namedFindTicketsByPricingCategoryName("Regular");
        assertTrue(ticket.size()>0);
       }
    @Test
    public void testNamedNativeQuery() throws Exception {
        List<TicketPrice> ticket =  repository.nativeFindTicketsByCategoryWithWorkshop("Regular");
        assertTrue(ticket.size()>0);
       }
}
