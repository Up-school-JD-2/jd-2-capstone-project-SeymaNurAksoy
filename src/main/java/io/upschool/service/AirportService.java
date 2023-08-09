package io.upschool.service;
import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private  final AirportRepository airportRepository;
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }
    public AirportSaveResponse save(AirportSaveRequest authorSaveRequest) {
//mapstruct dozer
        var newAirport = Airport
                .builder()
                .airportName(authorSaveRequest.getAirportName())
                .code(authorSaveRequest.getCode())
                .build();
        Airport savedAirport = airportRepository.save(newAirport);
        return AirportSaveResponse
                .builder()
                .id(savedAirport.getId())
                .airportName(savedAirport.getAirportName())
                .code(savedAirport.getCode())
                .build();
    }
    @Transactional(readOnly = true)
    public Airport getReferenceById(Long id) {
        return airportRepository.getReferenceById(id);
    }
    public List<Airport> getAllAirport() {
        return airportRepository.findAll();
    }
    public Airport findByAirportId(Long id) {
        return getAllAirport().stream().filter(airport -> airport.getId().equals(id))
                .findFirst().orElse(null);
    }
}
