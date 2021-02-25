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
import org.springframework.web.bind.annotation.RestController;

import demo.datajpa.models.TicketPrice;
import demo.datajpa.repositories.TicketPriceJpaRepository;

@RestController
@RequestMapping("/api/v1/ticket_prices")
public class TicketPricesController {

    @Autowired
    private TicketPriceJpaRepository repository;

    @GetMapping
    public List<TicketPrice> list() {
        return repository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public TicketPrice get(@PathVariable Long id) {
        return repository.getOne(id);
    }

    @PostMapping
    public TicketPrice create(@RequestBody final TicketPrice tp){
        return repository.save(tp);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping
    public TicketPrice update(@PathVariable Long id, @RequestBody TicketPrice tp) {
        //because this is a PUT, we expect all attributes to be passed in. A PATCH would only need what has changed.
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        TicketPrice existingTp = repository.getOne(id);
        BeanUtils.copyProperties(tp, existingTp, "ticket_price_id");
        return repository.save(tp);
    }

}
