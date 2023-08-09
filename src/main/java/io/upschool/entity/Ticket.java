package io.upschool.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "passenger_name")
    private String passengerName;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "is_active",columnDefinition = "boolean default true" )
    private Boolean active;

    @Column(name = "description")
    private String description;

}
