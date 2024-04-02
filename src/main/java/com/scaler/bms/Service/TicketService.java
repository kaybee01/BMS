package com.scaler.bms.Service;

import com.scaler.bms.Exception.InvalidRequestException;
import com.scaler.bms.Exception.UnavailableSeatException;
import com.scaler.bms.models.Ticket;

import java.util.List;

public interface TicketService {
    public Ticket bookTicket(List<Integer> seatId , int showId ,int userId) throws InvalidRequestException, UnavailableSeatException;
}
