package com.example.concertapplication.exceptions;

import com.example.concertapplication.exceptions.service.InvalidInternalLotNumberException;
import com.example.concertapplication.exceptions.service.InvalidScanIdException;
import com.example.concertapplication.exceptions.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@AllArgsConstructor
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("not_found", "Requested resource was not found"),
                                    HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("generic_error", "Invalid input or other error occurred"),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidInternalLotNumberException exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("invalid_internal_lot_number", "Invalid internal lot number"),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidScanIdException exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse("invalid_scan_id", "Input scan_id does not match the scan_id of the product for this lot"),
                                    HttpStatus.BAD_REQUEST);
    }
}