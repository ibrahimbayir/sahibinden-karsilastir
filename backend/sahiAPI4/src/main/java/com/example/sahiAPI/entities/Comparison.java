package com.example.sahiAPI.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comparisons")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comparison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "car1_id")
    private Car car1;

    @ManyToOne
    @JoinColumn(name = "car2_id")
    private Car car2;
}

