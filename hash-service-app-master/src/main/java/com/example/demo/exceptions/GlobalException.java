//package com.example.demo.exceptions;
//
//import com.example.demo.dto.ErrorDto;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ControllerAdvice
//public class GlobalException {
//
//    @ExceptionHandler(FieldNotFound.class)
//    public ResponseEntity<ErrorDto> handleServiceException(FieldNotFound ex) {
//        ErrorDto errorDto = ErrorDto.builder()
//                .code(ex.getCode())
//                .message(ex.getMessage())
//                .detailedMessage(ex.getDetailedMessage())
//                .build();
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getCode()));
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<List<ErrorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        List<ErrorDto> errorDtoList = ex.getBindingResult().getAllErrors().stream()
//                .map(error -> ErrorDto.builder()
//                        .code(HttpStatus.BAD_REQUEST.value())
//                        .message(error.getDefaultMessage())
//                        .detailedMessage(error.getDefaultMessage())
//                        .build())
//                .collect(Collectors.toList());
//        return new ResponseEntity<>(errorDtoList, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(InternalServerError.class)
//    public ResponseEntity<ErrorDto> handleInternalServerException(Exception ex) {
//        ErrorDto errorDto = ErrorDto.builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
//                .detailedMessage(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getCode()));
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorDto> handleNotFoundException(Exception ex) {
//        ErrorDto errorDto = ErrorDto.builder()
//                .code(HttpStatus.NOT_FOUND.value())
//                .message(HttpStatus.NOT_FOUND.toString())
//                .detailedMessage(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getCode()));
//    }
//
//    @ExceptionHandler(CustomServiceUnavailableException.class)
//    public ResponseEntity<ErrorDto> handleServiceUnavailableException(Exception ex) {
//        ErrorDto errorDto = ErrorDto.builder()
//                .code(HttpStatus.SERVICE_UNAVAILABLE.value())
//                .message(HttpStatus.SERVICE_UNAVAILABLE.toString())
//                .detailedMessage(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getCode()));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDto> handleException(Exception ex) {
//        ErrorDto errorDto = ErrorDto.builder()
//                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .message(HttpStatus.INTERNAL_SERVER_ERROR.toString())
//                .detailedMessage(ex.getMessage())
//                .build();
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(errorDto.getCode()));
//    }
//
//
//}