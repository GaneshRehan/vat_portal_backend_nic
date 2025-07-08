package com.telusko.SpringSecEx.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "acknowledgements")
public class Acknowledgement {

    @Id
    @Column(name = "ack_no")
    private String ackNo;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // You can add other fields if needed
}
