package io.upschool.service;

import io.upschool.dto.ticket.TicketCancelResponse;
import io.upschool.dto.ticket.TicketSaveRequest;
import io.upschool.dto.ticket.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.TicketException;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private  final TicketRepository ticketRepository;

    private  final  FlightService flightService;

    @Transactional
    public TicketSaveResponse save(TicketSaveRequest request) {
        Ticket newTicket= null;
        Ticket ticketResponse=null;
        var maskedmaskCreditCardNumber=maskCreditCardNumber(request.getCreditCardNumber());
        var ticketNumber = ticketNumber();
        if(maskedmaskCreditCardNumber.length() == 16){
            if(request.getFlight().getFlightNumber() == null){
                Flight flight = flightService.save(request.getFlight());
                newTicket = Ticket.builder()
                        .flight(flight)
                        .passengerName(request.getPassengerName())
                        .creditCardNumber(maskedmaskCreditCardNumber)
                        .ticketNumber(ticketNumber)
                        .active(true)
                        .build();
            }
            else{
                Flight flightByReference = flightService.getReferenceById(request.getFlight().getFlightNumber());
                newTicket = Ticket.builder()
                        .flight(flightByReference)
                        .passengerName(request.getPassengerName())
                        .creditCardNumber(maskedmaskCreditCardNumber)
                        .ticketNumber(ticketNumber)
                        .active(true)
                        .build();
            }
        }else{
            throw new TicketException("Kart numarası 16 haneli olmaldır.");
        }
        ticketResponse = ticketRepository.save(newTicket);
        return TicketSaveResponse.builder()
                .passengerName(ticketResponse.getPassengerName())
                .ticketNumber(ticketResponse.getTicketNumber())
                .flightNumber(ticketResponse.getFlight().getFlightNumber())
                .creditCardNumber(ticketResponse.getCreditCardNumber())
                .active(ticketResponse.getActive())
                .build();
    }
    public String maskCreditCardNumber(String creditCardNumber) {
        String digitsOnly = creditCardNumber.replaceAll("\\D", "");
        String maskedDigits = "*".repeat(digitsOnly.length() - 10);
        return digitsOnly.substring(0, 6) + maskedDigits + digitsOnly.substring(digitsOnly.length() - 4);
    }
    public String ticketNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(1000000);
        var ticketNumber = "TICKET_" + randomNumber;
        return  ticketNumber;
    }
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Ticket findTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
    public Ticket findTicketByTicketNumber(String ticketNumber) {
        return getAllTickets().stream().filter(ticket -> ticket.getTicketNumber().equals(ticketNumber))
                .findFirst().orElse(null);
    }
    public TicketCancelResponse delete(Long id) {
        var ticket = ticketRepository.findById(id).get();
        ticket.setActive(false);
        ticket.setDescription("Bilet iptal olmuştur.");
        ticketRepository.save(ticket);
        return TicketCancelResponse.builder()
                .description("Bilet iptal olmuştur.")
                .build();
    }
    public List<Ticket> getActiveTickets(){
        return ticketRepository.getActiveTickets();
    }
}
