package com.server.SpringServer.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Groups {  //Класс, для переноса данных расписания (не реализовано)
    @Id
    private int institutionId;
    private String abbreviation;
    private int course;
}
