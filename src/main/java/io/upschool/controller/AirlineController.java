package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSaveRequest;
import io.upschool.dto.airline.AirlineSaveResponse;
import io.upschool.dto.airline.AirlineSearchRequest;
import io.upschool.dto.flight.FlightSearchRequest;
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private  final AirlineService airlineService;
    @PostMapping("/airline")
    public ResponseEntity<AirlineSaveResponse> createAirline(@Valid @RequestBody AirlineSaveRequest airlineSaveRequest) {
        var response = airlineService.save(airlineSaveRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchFlight(@RequestBody AirlineSearchRequest request) {
        var flightResponse = airlineService.findByAirlineId(request.getId());
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<Object> getAirlines(){
        var list = airlineService.getAllAirline();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
