package demo.datajpa.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.datajpa.models.TicketPrice;

public interface TicketPriceJpaRepository extends JpaRepository<TicketPrice, Long> {

	@Query("select tp from TicketPrice tp where tp.basePrice < ?1 "+
			 " and tp.ticketType.includesWorkshop ?2")
	List<TicketPrice> getTicketsUnderPriceWithWorksShops(BigDecimal maxPrice,boolean True);
	
	@Query("select tp from TicketPrice tp where tp.basePrice =:maxPrice "+
			 " and tp.ticketType.includesWorkshop = true")
	List<TicketPrice> getTicketsUnderPriceWithWorksShopsWithParam(@Param("maxPrice")BigDecimal maxPrice);

    List<TicketPrice> namedFindTicketsByPricingCategoryName(@Param("name") String name);
    List<TicketPrice> nativeFindTicketsByCategoryWithWorkshop(@Param("name") String name);

}
