package com.server.SpringServer.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Timetable {
    @Id
    private int id;
    private String groupName;
    private String disciplineName;
}
