package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.flight.FlightSaveRequest;
import io.upschool.dto.flight.FlightSearchRequest;
import io.upschool.dto.ticket.TicketSearchRequest;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private  final FlightService flightService;
    @PostMapping("/flight")
    public ResponseEntity<Object> createFlight(@RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.save(request);
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchFlight(@RequestBody FlightSearchRequest request) {
        var flightResponse = flightService.findByFlightNumber(request.getFlightNumber());
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<Object> getFlights(){
        var list = flightService.getAllFlights();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
