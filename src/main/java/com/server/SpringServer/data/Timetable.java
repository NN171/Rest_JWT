package com.server.SpringServer.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Timetable {  //Класс, для получения вложенных параметров расписания (не реализовано)
    @Id
    private int id;
    private String groupName;
    private String disciplineName;
}
