package demo.datajpa.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.datajpa.models.Session;
import demo.datajpa.repositories.SessionsJpaRepository;

@RestController
//@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionsJpaRepository repository;

    @GetMapping("/test")
    public Session getSessions(){
    	return  repository.findFirstBySessionNameContains("KeyNote");
    }
    
    @GetMapping
    public List<Session> list(@RequestParam(required = false) String name) {
        if(name != null) {
            return repository.findBySessionNameContains(name);
        } else {
            return repository.findAll();
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return repository.save(session);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = repository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return repository.save(session);
    }
    
    

}
