package io.upschool.service;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final  AirportService airportService;
    private final AirlineService airlineService;

    public Route save(Route route) {
        var airline = airlineService.save(route.getAirline());
        route.setAirline(airline);
        var sourceAirport = airportService.save(airportService.save(route.getSourceAirport()));
        route.setSourceAirport(sourceAirport);
        var destinationAirport = airportService.save(route.getDestinationAirport());
        route.setDestinationAirport(destinationAirport);
        return routeRepository.save(route);
    }

    // isolation = Isolation.READ_UNCOMMITTED
    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
        Route routeResponse= null;
        Route newRoute = null;
        if(request.getSourceAirport().getId()==null && request.getDestinationAirport().getId() ==null && request.getAirline().getId() ==null){
            Airline airline= airlineService.save(request.getAirline());
            Airport sourceAirport = airportService.save(request.getSourceAirport());
            Airport destinationAirport= airportService.save(request.getDestinationAirport());
            newRoute = Route.builder()
                    .airline(airline)
                    .date(request.getDate())
                    .numberOfSeat(request.getNumberOfSeat())
                    .sourceAirport(sourceAirport)
                    .destinationAirport(destinationAirport)
                    .build();
        }
        else{
            Airline airlineByReference = airlineService.getReferenceById(request.getAirline().getId());
            Airport airportSourceByReference = airportService.getReferenceById(request.getSourceAirport().getId());
            Airport airportDestinationByReference = airportService.getReferenceById(request.getDestinationAirport().getId());
            newRoute = Route.builder()
                    .airline(airlineByReference)
                    .sourceAirport(airportSourceByReference)
                    .date(request.getDate())
                    .numberOfSeat(request.getNumberOfSeat())
                    .destinationAirport(airportDestinationByReference)
                    .build();
        }
         routeResponse = routeRepository.save(newRoute);
        return RouteSaveResponse.builder()
                .id(routeResponse.getId())
                .numberOfSeat(routeResponse.getNumberOfSeat())
                .date(routeResponse.getDate())
                .airlineName(routeResponse.getAirline().getName())
                .airportSourceName(routeResponse.getSourceAirport().getAirportName())
                .airportDestinationName(routeResponse.getDestinationAirport().getAirportName())
                .build();
    }
    @Transactional(readOnly = true)
    public Route getReferenceById(Long id) {
        return routeRepository.getReferenceById(id);
    }
    public List<Route> getAllRoute() {
        return routeRepository.findAll();
    }
    public Route findByRouteId(Long id) {
        return getAllRoute().stream().filter(route -> route.getId().equals(id))
                .findFirst().orElse(null);
    }

}