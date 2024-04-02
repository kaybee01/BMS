package com.scaler.bms.Service;

import com.scaler.bms.Exception.InvalidRequestException;
import com.scaler.bms.Exception.UnavailableSeatException;
import com.scaler.bms.Repositories.*;
import com.scaler.bms.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TicketServiceImplementation  implements TicketService{



    private UserRepository userRepository;
    private ShowRepository showRepository;
    private SeatRepository seatRepository;
    private ShowSeatRepository showSeatRepository;
    private TicketRepository ticketRepository;
    private ShowSeatTypRepository showSeatTypRepository;

    @Autowired
    public TicketServiceImplementation(UserRepository userRepository, ShowRepository showRepository, SeatRepository seatRepository, ShowSeatRepository showSeatRepository, TicketRepository ticketRepository, ShowSeatTypRepository showSeatTypRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
        this.showSeatTypRepository = showSeatTypRepository;
    }

    @Override
    public Ticket bookTicket(List<Integer> seatId, int showId, int userId) throws InvalidRequestException, UnavailableSeatException {

        //validating user
        Optional<User> userById = this.userRepository.findUserById(userId);
        if (userById.isEmpty()){
            throw  new InvalidRequestException("User is Invalid..!");
        }

        User user = userById.get();

        //validating show
        Show show = this.showRepository.findShowById(showId).orElseThrow(() -> new InvalidRequestException("Show in Invalid..!"));

        //validating time logic
        Date curremtDate =new Date();
        long currentDateTime = curremtDate.getTime();
        if (show.getStartTime().getTime()+(10+60L) < currentDateTime){
            throw  new InvalidRequestException("Time to booke Ticket Exceeded");
        }

        //validating Seat
        List<Seat> seats = this.seatRepository.findAllByIdIn(seatId);
        if(seats.size()!=seatId.size()){
            throw new InvalidRequestException("Seats are Invalid");

        }
        //bocking selected seats
        blockSeatForUser(user,show ,seatId);

        //pricing
        List<ShowSeatType> showSeatType = this.showSeatTypRepository.findAllByShowId(showId);
        Map<SeatType, Double> pricingMap = new HashMap<>();
        for(ShowSeatType seatType: showSeatType){
            pricingMap.put(seatType.getSeatType(), seatType.getAmount());
        }
        double totalAmount = 0;
        for(Seat seat: seats){
            totalAmount += pricingMap.get(seat.getSeatType());
        }


        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.UNPAID);
        ticket.setUser(user);
        ticket.setShow(show);
        ticket.setSeats(seats);
        ticket.setPrice(totalAmount);

        return this.ticketRepository.save(ticket);

    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void blockSeatForUser(User user,Show show , List<Integer> seatIds) throws UnavailableSeatException {
        List<ShowSeat> showSeats = this.showSeatRepository.findAllByShowIdAndSeatIdsInAndSeatStatus_Available(show.getId(), seatIds);
        if(showSeats.size()!=seatIds.size()){
            throw new UnavailableSeatException("Seats are Unavaliable..!");
        }
        showSeats.stream().forEach(ss -> {
            ss.setSeatStatus(SeatStatus.BLOCKED);
            ss.setUser(user);
        });
        showSeatRepository.saveAll(showSeats);
    }


}
