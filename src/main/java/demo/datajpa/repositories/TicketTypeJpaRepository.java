package demo.datajpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.datajpa.models.TicketType;

public interface TicketTypeJpaRepository extends JpaRepository<TicketType, String> {
    //True or false
	List<TicketType> findByIncludesWorkshopFalse();
	
	
	List<TicketType> findByIncludesWorkshopTrue();//No parameters
     
	@Query("select tp from TicketType tp where tp.includesWorkshop =true and tp.ticketTypeName In :codes ")
	List<TicketType> findByIncludesWorkshopTrueAnd (List<String> codes );


}
