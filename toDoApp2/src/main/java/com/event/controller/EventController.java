package com.event.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.EventDto;
import com.event.entity.Event;
import com.event.response.ResponseObject;
import com.event.service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping("/event")
	public ResponseEntity<ResponseObject> createEvent(@RequestBody EventDto eventDto) {
		EventDto event = eventService.createEvent(eventDto);
		return ResponseEntity.ok(new ResponseObject(event, HttpStatus.OK.value(), null));
	}

	@PutMapping("/event/{eventId}")
	public ResponseEntity<ResponseObject> updateEvent(@RequestBody EventDto eventDto, @PathVariable int eventId) {

		EventDto event = eventService.updateEvent(eventDto, eventId);
		return ResponseEntity.ok(new ResponseObject(event, HttpStatus.OK.value(), null));
	}

	@DeleteMapping("/event/{eventId}")
	public ResponseEntity<ResponseObject> deleteEvent(@PathVariable int eventId) {
		eventService.deleteEvent(eventId);
		return ResponseEntity.ok(new ResponseObject("Event deleted successfully", HttpStatus.OK.value()));
	}

	@GetMapping("/event")
	public ResponseEntity<ResponseObject> getAllEvents() {
		List<EventDto> events = eventService.getAllEvents();
		return ResponseEntity.ok(new ResponseObject(events, HttpStatus.OK.value(), null));
	}

	@PutMapping("/event/{eventId}/completed")
	public ResponseEntity<ResponseObject> markEventsAsCompleted(@PathVariable int eventId) {
		EventDto eventDto = eventService.markEventsAsCompleted(eventId);
		return ResponseEntity.ok(new ResponseObject(eventDto, HttpStatus.OK.value(), null));
	}

	@GetMapping("/event/groupEvents")
	public ResponseEntity<ResponseObject> groupEventsByStatus() {
		Map<String, List<EventDto>> groupEvents = eventService.groupEventsByStatus();
		return ResponseEntity.ok(new ResponseObject(groupEvents, HttpStatus.OK.value(), null));
	}
}
