package com.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.event.dto.EventDto;

@Service
public interface EventService {

	EventDto createEvent(EventDto eventDto);

	EventDto updateEvent(EventDto eventDto, int eventId);

	void deleteEvent(int eventId);

	List<EventDto> getAllEvents();

	Map<String, List<EventDto>> groupEventsByStatus();

	EventDto markEventsAsCompleted(int eventId);
}
