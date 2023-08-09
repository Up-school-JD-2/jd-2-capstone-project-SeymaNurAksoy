package io.upschool.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_number")
    private Long flightNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route", nullable = false)
    private Route route;






}
