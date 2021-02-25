package demo.datajpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.datajpa.models.Session;
@Repository
public interface SessionsJpaRepository extends JpaRepository<Session,Long> {

	//GetFirst,Count  SessionName
	Session findFirstBySessionNameContains(String sessionName);
	
	//get first record where sessionDescription contains keyword java
	 Session findFirstBySessionDescriptionContains(String sessionDescription );
	// get count from session table where sessionDescription contains keyword java
	Long countBySessionDescriptionContains(String sessionName);
	
	
	Long countBySessionNameContains(String sessionName);
	
	
	//Equals and Not
	/*List<Session> findBySessionLengthIs(Integer sessionLength);
	List<Session> findBySessionLengthEquals(Integer sessionLength);
	List<Session> findBySessionLengthNotEquals(Integer sessionLength);*/
	List<Session> findBySessionLengthIs(Integer sessionLength);
	List<Session> findBySessionLengthNot(Integer sessionLength);

	
	//Like Not Like
	List<Session> findBySessionNameLike(String sessionName);
	List<Session> findBySessionNameNotLike(String sessionName);
	
	//Startingwith,Endinwith, contains
	List<Session> findBySessionNameContains(String sessionName);
	List<Session> findBySessionNameStartingWith(String sessionName);
	List<Session> findBySessionNameEndingWith(String sessionName);
	 
	//<,<=,>,>=
	List<Session> findBySessionLengthLessThan(Integer sessionLenght);
	List<Session> findBySessionLengthLessThanEqual(Integer sessionLenght);//not equals
	List<Session> findBySessionLengthGreaterThan(Integer sessionLenght);
	List<Session> findBySessionLengthGreaterThanEqual(Integer sessionLenght);

    //before ,after,between
    /* findByDateAttributeBefore(dateAttribute);
     * findByDateAttributeAfter(dateAttribute);
     * findByDateAttributeBetween(dateAttribute1,dateAttribute2);
     */
	
	//True r false --TicketTypeRepo
	//Null =IsNull ,NotNull=IsNotNull --SpeakerRepo

	//In ,Not in  --speaker with List<String>
	List<Session> findBySessionLengthIn(List<Integer> sessionLength);
	List<Session> findBySessionLengthNotIn(List<Integer> sessionLength);

	//IgnoreCase  --speakerRepo
	List<Session> findBySessionNameIgnoreCase(String sessionName);
	List<Session> findBySessionNameContainsIgnoreCase(String sessionName);

	//OrderByAsc, OrderByDesc 
	
	List<Session>  findBySessionLengthIsAndSessionNameContainsOrderBySessionLengthDescSessionNameAsc(Integer sessionLenght,String sessionName);
	List<Session>  findBySessionLengthInOrderBySessionLengthDescSessionNameAsc(List<Integer> sessionLength);

	List<Session>  findBySessionLengthIsOrderBySessionLengthDesc(Integer sessionLenght);

	//First,Top,Distinct
	
	Session  findFirstBySessionLengthIs(Integer sessionLenght);
	List<Session>  findTop5BySessionLengthIs(Integer sessionLenght);
	List<Session>  findDistinctBySessionLengthIs(Integer sessionLenght);

    @Query("select s from Session s where s.sessionName like %?1")
    List<Session> getSessionWithName(String name);
    
    @Query(value="select s from session s where s.session_name like %?1",nativeQuery = true)
    List<Session> getSessionWithNameNativeQuery(String name);
    
    @Transactional
    @Modifying
    @Query("update Session s set s.sessionName =?1")
    int updateSessionName(String sessionName);
    
    @Query("select s from Session s where s.sessionName like %?1")
    Page<Session> getSessionWithName(@Param("name")String name,Pageable Pageable);
}
