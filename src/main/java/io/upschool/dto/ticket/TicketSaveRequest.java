package io.upschool.dto.ticket;

import io.upschool.entity.Flight;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {

    private Flight flight;
    private String passengerName;
    @NotBlank
    @Size(min = 16, max = 16,message = "Kredi Kartı Numarası 16 Haneli Olmalıdır.")
    private String creditCardNumber;
   // private Boolean active;

}
