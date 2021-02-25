package demo.datajpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.datajpa.models.PricingCategory;

public interface PriceCategoryJpaRepository extends JpaRepository<PricingCategory, String>{

	
	
}
