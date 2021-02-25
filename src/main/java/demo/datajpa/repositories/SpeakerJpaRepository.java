package demo.datajpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.datajpa.models.Speaker;

public interface SpeakerJpaRepository extends JpaRepository<Speaker, Long>{

	//AND ,OR
	List<Speaker> findByFirstNameAndLastName(String firstName,String LastName);
	List<Speaker> findByFirstNameOrLastName(String firstName,String LastName);
	
	//Null =IsNull ,NotNull=IsNotNull
	List<Speaker> findBySpeakerPhotoNull( );
	List<Speaker> findBySpeakerPhotoIsNull( );//both same
	List<Speaker> findBySpeakerPhotoNotNull( );
	List<Speaker> findBySpeakerPhotoIsNotNull( );//both same

	//In ,NotIn
	List<Speaker> findByCompanyIn(List<String> companies);
	List<Speaker> findByCompanyNotIn(List<String> companies);
  
	//IgnoreCase
	List<Speaker> findByCompanyIgnoreCase(String company);
	List<Speaker> findByCompanyContainsIgnoreCase(String company);


}
