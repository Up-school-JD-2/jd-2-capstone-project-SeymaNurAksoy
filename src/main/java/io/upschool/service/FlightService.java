package io.upschool.service;

import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.entity.Ticket;
import io.upschool.exception.FlightException;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private  final FlightRepository flightRepository;
    private final  RouteService routeService;
    public Flight save(@NotNull Flight flight) {
        var route = routeService.save(flight.getRoute());
        flight.setRoute(route);
        return flightRepository.save(flight);
    }
    @Transactional
    public FlightSaveResponse save(FlightSaveRequest request) {
        Flight newFlight= null;
        Flight flightResponse=null;
        Route routeByReference = null;
        int nowNumber=0;
        int seat = 0;
        if(request.getRoute().getId()==null){
            Route route = routeService.save(request.getRoute());
            newFlight = Flight.builder()
                    .route(route)
                    .build();
        }
        else{
            routeByReference = routeService.getReferenceById(request.getRoute().getId());
            nowNumber= routeByReference.getNumberOfSeat();
            seat= routeByReference.getNumberOfSeat()-1;
            routeByReference.setNumberOfSeat(seat);
            newFlight = Flight.builder()
                    .route(routeByReference)
                    .build();
        }
        if(nowNumber == 0){
            throw new FlightException("Koltuk Kalmamıştır.");
        }else{
            flightResponse = flightRepository.save(newFlight);
        }
        return FlightSaveResponse.builder()
                .flightNumber(flightResponse.getFlightNumber())
                .routeName("Nereden: "+flightResponse.getRoute().getSourceAirport().getAirportName()+ "  Nereye: "+flightResponse.getRoute().getDestinationAirport().getAirportName() +
                " Hava Yolu Şirketi: "+flightResponse.getRoute().getAirline().getName())
                .build();
    }
    @Transactional(readOnly = true)
    public Flight getReferenceById(Long id) {
        return flightRepository.getReferenceById(id);
    }
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    public Flight findByFlightNumber(Long flightNumber) {
        return getAllFlights().stream().filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .findFirst().orElse(null);
    }

}
