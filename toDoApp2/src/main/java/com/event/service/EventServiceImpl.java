package com.event.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.event.dto.EventDto;
import com.event.entity.Event;
import com.event.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public EventDto createEvent(EventDto eventDto) {
		Event event = modelMapper.map(eventDto, Event.class);
		event.setStatus("Pending");
		eventRepository.save(event);
		return modelMapper.map(event, EventDto.class);
	}

	@Override
	public EventDto updateEvent(EventDto eventDto, int eventId) {
		Optional<Event> event = eventRepository.findById(eventId);
		Event updateEvent = event.get();
		updateEvent.setName(eventDto.getName());
		updateEvent.setDescription(eventDto.getDescription());
		updateEvent.setDateTime(eventDto.getDateTime());
		eventRepository.save(updateEvent);
		return modelMapper.map(updateEvent, EventDto.class);
	}

	@Override
	public void deleteEvent(int eventId) {
		eventRepository.deleteById(eventId);
	}

	@Override
	public List<EventDto> getAllEvents() {
		return eventRepository.findAll().stream().map(event -> modelMapper.map(event, EventDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public Map<String, List<EventDto>> groupEventsByStatus() {
		List<EventDto> list = eventRepository.findAll().stream().map(event -> modelMapper.map(event, EventDto.class))
				.collect(Collectors.toList());

		List<EventDto> completedList = list.stream().filter(event -> event.getStatus().equals("Completed"))
				.collect(Collectors.toList());

		List<EventDto> pendingList = list.stream().filter(
				event -> event.getDateTime().isAfter(LocalDateTime.now()) && !event.getStatus().equals("Completed"))
				.collect(Collectors.toList());

		List<EventDto> overdueList = list.stream().filter(
				event -> event.getDateTime().isBefore(LocalDateTime.now()) && !event.getStatus().equals("Completed"))
				.collect(Collectors.toList());

		Map<String, List<EventDto>> groupedEvents = new HashMap<>();
		groupedEvents.put("Pending", pendingList);
		groupedEvents.put("Completed", completedList);
		groupedEvents.put("Overdue", overdueList);

		return groupedEvents;
	}

	@Override
	public EventDto markEventsAsCompleted(int eventId) {
		Event event = eventRepository.findById(eventId).get();
		event.setStatus("Completed");
		eventRepository.save(event);
		return modelMapper.map(event, EventDto.class);
	}
}
