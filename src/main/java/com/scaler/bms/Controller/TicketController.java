package com.scaler.bms.Controller;

import com.scaler.bms.DTO.BookTicketRequestDto;
import com.scaler.bms.DTO.BookTicketResponseDto;
import com.scaler.bms.DTO.ResponseType;
import com.scaler.bms.Exception.InvalidRequestException;
import com.scaler.bms.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto){
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        try {
            validate(requestDto);
            this.ticketService.bookTicket(requestDto.getSeatId(),requestDto.getShowId(),requestDto.getUserId());

        }catch (Exception e){
            responseDto.setResponseType(ResponseType.FAILURE);
            responseDto.setErrorMessage(e.getMessage());
        }
        return responseDto;
    }

    private void validate(BookTicketRequestDto requestDto) throws InvalidRequestException {
        if (requestDto.getSeatId() == null || requestDto.getSeatId().isEmpty()){
            throw new InvalidRequestException("Seat Id's should be present");
        }
        if (requestDto.getUserId() < 0){
            throw  new InvalidRequestException("User Id Invalid");
        }
        if (requestDto.getShowId() < 0){
            throw  new InvalidRequestException("User Id Invalid");
        }
    }
}
