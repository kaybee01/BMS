package com.scaler.bms.DTO;

import com.scaler.bms.models.Ticket;
import lombok.Data;

@Data
public class BookTicketResponseDto {
    private Ticket ticket;
    private String errorMessage;
    private ResponseType responseType;
}
