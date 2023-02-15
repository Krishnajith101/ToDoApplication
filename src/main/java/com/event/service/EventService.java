package com.event.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.entity.Event;
import com.event.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepo;

	public Event saveEvent(Event event) {
		return eventRepo.save(event);
	}

	public Event editEvent(Event event) {
		return eventRepo.save(event);
	}

	public void delEvent(int eId) {
		eventRepo.deleteById(eId);
	}

	public List<Event> viewAllEvents() {
		return eventRepo.findAll();
	}

	public Map<String, List<Event>> groupEvents() {
		List<Event> pendingList = eventRepo.findAll().stream().filter(
				event -> event.getDateTime().isAfter(LocalDateTime.now()) && !event.getStatus().equals("Completed"))
				.collect(Collectors.toList());
		List<Event> completedList = eventRepo.findByStatus("Completed");

		List<Event> overdueList = eventRepo.findAll().stream().filter(
				event -> event.getDateTime().isBefore(LocalDateTime.now()) && !event.getStatus().equals("Completed"))
				.collect(Collectors.toList());

		Map<String, List<Event>> groupedEvents = new HashMap<>();
		groupedEvents.put("Pending", pendingList);
		groupedEvents.put("Completed", completedList);
		groupedEvents.put("Overdue", overdueList);
		return groupedEvents;
	}

	public Event markEventsCompleted(int eId) {
		Event event = eventRepo.findById(eId).get();
		event.setStatus("Completed");
		return eventRepo.save(event);
	}
}
