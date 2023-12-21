package com.server.SpringServer.repository;

import com.server.SpringServer.data.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, String> { //Аналогично StudentRepository (пока не используется)

}