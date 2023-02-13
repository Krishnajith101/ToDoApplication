package com.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
	
	List<Event> findByStatus(String status);
}
