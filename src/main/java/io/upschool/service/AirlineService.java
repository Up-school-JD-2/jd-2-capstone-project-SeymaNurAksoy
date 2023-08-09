package io.upschool.service;
import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private  final AirlineRepository airlineRepository;
    public Airline save(Airline airline) {
        Airline saveAirline=  airlineRepository.save(airline);
        return saveAirline;
    }
    public AirlineSaveResponse save(AirlineSaveRequest airlineSaveRequest) {
        var newAirline = Airline
                .builder()
                .name(airlineSaveRequest.getName())
                .build();
        Airline savedAirline = airlineRepository.save(newAirline);
        return AirlineSaveResponse
                .builder()
                .id(savedAirline.getId())
                .name(savedAirline.getName())
                .build();
    }
    @Transactional(readOnly = true)
    public Airline getReferenceById(Long id) {
        return airlineRepository.getReferenceById(id);
    }
    public List<Airline> getAllAirline() {
        return airlineRepository.findAll();
    }
    public Airline findByAirlineId(Long id) {
        return getAllAirline().stream().filter(airline -> airline.getId().equals(id))
                .findFirst().orElse(null);
    }

}
