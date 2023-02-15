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

import com.event.Dto.EventDto;
import com.event.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("/event")
	public EventDto createEvent(@RequestBody EventDto eventDto) {
		return eventService.createEvent(eventDto);
	}

	@PutMapping("/event/{eventId}")
	public EventDto updateEvent(@RequestBody EventDto eventDto, @PathVariable int eventId) {
		return eventService.updateEvent(eventId, eventDto);
	}

	@DeleteMapping("/event/{eventId}")
	public String deleteEvent(@PathVariable int eventId) {
		eventService.deleteEvent(eventId);
		return "Event Deleted Successfully";
	}

	@GetMapping("/event")
	public List<EventDto> getAllEvents() {
		return eventService.getAllEvents();
	}

	@PutMapping("/event/{eventId}/completed")
	public EventDto markEventsAsCompleted(@PathVariable int eventId) {
		return eventService.markEventsAsCompleted(eventId);
	}

	@GetMapping("/event/groupEvents")
	public Map<String, List<EventDto>> groupEventsByStatus() {
		return eventService.groupEventsByStatus();
	}
}
