package io.upschool.dto.route;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {
    private Airline airline;
    private Airport sourceAirport;
    private Airport destinationAirport;
    private  int numberOfSeat;
    private Date date;
}
