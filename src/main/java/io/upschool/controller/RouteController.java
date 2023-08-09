package io.upschool.controller;
import io.upschool.dto.*;
import io.upschool.dto.flight.FlightSearchRequest;
import io.upschool.dto.route.RouteSaveRequest;
import io.upschool.dto.route.RouteSearchRequest;
import io.upschool.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private  final RouteService routeService;
    @PostMapping("/route")
    public ResponseEntity<Object> createRoute(@RequestBody RouteSaveRequest request) {
        var routeSaveResponse = routeService.save(request);
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(routeSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchRoute(@RequestBody RouteSearchRequest request) {
        var flightResponse = routeService.findByRouteId(request.getId());
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<Object> getRoutes(){
        var list = routeService.getAllRoute();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
