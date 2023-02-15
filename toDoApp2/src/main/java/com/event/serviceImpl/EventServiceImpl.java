package com.event.serviceImpl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.event.Dto.EventDto;
import com.event.entity.Event;
import com.event.repository.EventRepository;
import com.event.service.EventService;

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
		event = eventRepository.save(event);
		return modelMapper.map(event, EventDto.class);
	}

	@Override
	public EventDto updateEvent(int eventId, EventDto eventDto) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));
		modelMapper.map(eventDto, Event.class);
		event = eventRepository.save(event);
		return modelMapper.map(event, EventDto.class);
	}

	@Override
	public void deleteEvent(int eventId) {
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));
		eventRepository.delete(event);
	}

	@Override
	public List<EventDto> getAllEvents() {
		List<Event> events = eventRepository.findAll();
		return events.stream().map(event -> modelMapper.map(event, EventDto.class)).collect(Collectors.toList());
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
		Event event = eventRepository.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found"));
		event.setStatus("Completed");
		event = eventRepository.save(event);
		return modelMapper.map(event, EventDto.class);
	}
}
