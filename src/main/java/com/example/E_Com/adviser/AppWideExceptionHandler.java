package com.example.E_com.adviser;

import com.example.E_com.exception.DuplicateEntryException;
import com.example.E_com.exception.EntryNotFoundException;
import com.example.E_com.exception.ProductNotAvailableException;
import com.example.E_com.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler( EntryNotFoundException.class )
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException entryNotFoundException){
        return new ResponseEntity<>(
                new StandardResponse(404, entryNotFoundException.getMessage(), entryNotFoundException),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler( ProductNotAvailableException.class )
    public ResponseEntity<StandardResponse> handleProductNotAvailableException(ProductNotAvailableException productNotAvailableException){
        return new ResponseEntity<>(
                new StandardResponse(400, productNotAvailableException.getMessage(), productNotAvailableException),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler( DuplicateEntryException.class )
    public ResponseEntity<StandardResponse> handleDuplicateEntryException(DuplicateEntryException duplicateEntryException){
        return new ResponseEntity<>(
                new StandardResponse(409, duplicateEntryException.getMessage(), duplicateEntryException),
                HttpStatus.CONFLICT
        );
    }
}
