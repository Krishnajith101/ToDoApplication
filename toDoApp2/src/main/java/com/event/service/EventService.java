package com.event.service;

import java.util.List;
import java.util.Map;

import com.event.Dto.EventDto;

public interface EventService {

	EventDto createEvent(EventDto eventDto);

	EventDto updateEvent(int eventId, EventDto eventDto);

	void deleteEvent(int eventId);

	List<EventDto> getAllEvents();

	Map<String, List<EventDto>> groupEventsByStatus();

	EventDto markEventsAsCompleted(int eventId);
}
