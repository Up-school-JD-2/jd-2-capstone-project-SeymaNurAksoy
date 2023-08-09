package io.upschool.dto.route;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveResponse {

    private Long id;
    private String airlineName;
    private String airportSourceName;
    private String airportDestinationName;
    private  int numberOfSeat;
    private Date date;
}
