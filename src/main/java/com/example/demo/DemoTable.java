package com.example.demo;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "test")
public class DemoTable {

    @Id
    @Column(name="id")
    private Long id;

    private String message;
}
