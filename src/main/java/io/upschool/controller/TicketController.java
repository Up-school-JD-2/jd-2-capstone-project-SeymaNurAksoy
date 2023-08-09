package io.upschool.controller;

import io.upschool.dto.BaseResponse;
import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSearchRequest;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private  final TicketService ticketService;
    @PostMapping("/ticket")
    public ResponseEntity<Object> createTicket(@RequestBody  TicketSaveRequest request) {
        var ticketSaveResponse = ticketService.save(request);
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/search")
    public ResponseEntity<Object> searchTicket(@RequestBody TicketSearchRequest request) {
        var ticketResponse = ticketService.findTicketByTicketNumber(request.getTicketNumber());
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("{id}")
    public ResponseEntity<Object>  cancelTicket(@PathVariable("id") Long id) {
        var ticketResponse= ticketService.delete(id);
        var response =  BaseResponse.builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<Object> getActiveTicket(){
        var tickets = ticketService.getActiveTickets();
        return ResponseEntity.status(HttpStatus.OK).body(tickets);
    }
}
