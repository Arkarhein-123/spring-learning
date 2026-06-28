package org.example.bankapp.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timeStamp,
    String message,
    String detail
    ) {
}
