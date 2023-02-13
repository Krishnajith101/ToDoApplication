package com.event.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.entity.Event;
import com.event.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	EventService eventServ;
	
	@PostMapping("/event")
	public Event createEvent(@RequestBody Event event) {
		return eventServ.saveEvent(event);
	}
	
	@PutMapping("/event/{eId}")
	public Event updateEvent(@RequestBody Event event, @PathVariable int eId) {
		event.seteId(eId);
		return eventServ.editEvent(event);
	}
	
	@DeleteMapping("/event/{eId}")
	public String deleteEvent(@PathVariable int eId) {
		eventServ.delEvent(eId);
		return "Event Deleted Successfully";
	}
	
	@GetMapping("/event")
	public List<Event> getAllEvents(){
		return eventServ.viewAllEvents();
	}
	
	@PutMapping("/event/{eId}/completed")
	public Event MarkEventsAsCompleted(@PathVariable int eId) {
		return eventServ.markEventsCompleted(eId);
	}
	
	@GetMapping("/event/groupEvents")
	public Map<String,List<Event>> groupEventsByStatus(){
		return eventServ.groupEvents();
	}
}
