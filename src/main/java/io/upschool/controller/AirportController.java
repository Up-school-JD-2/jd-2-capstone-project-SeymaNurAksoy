package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.airline.AirlineSearchRequest;
import io.upschool.dto.airport.AirportSaveRequest;
import io.upschool.dto.airport.AirportSaveResponse;
import io.upschool.dto.airport.AirportSearchRequest;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {
    private  final AirportService airportService;
    @PostMapping("/airport")
    public ResponseEntity<AirportSaveResponse> createAirport(@Valid @RequestBody AirportSaveRequest airportSaveRequest) {
        var response = airportService.save(airportSaveRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchAirport(@RequestBody AirportSearchRequest request) {
        var flightResponse = airportService.findByAirportId(request.getId());
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<Object> getAirports(){
        var list = airportService.getAllAirport();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
