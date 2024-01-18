package bogdan.bookingsystem.controller;

import bogdan.bookingsystem.dto.BookingDto;
import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable Long id) {
        BookingDto bookingDto = bookingDtoBuilder(bookingService.findBookingById(id));
        return ResponseEntity.ok(bookingDto);
    }

    @GetMapping()
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingDto> bookingDtos = bookings.stream().map(this::bookingDtoBuilder).toList();
        return ResponseEntity.ok(bookingDtos);
    }

    @PostMapping
    public ResponseEntity<BookingDto> makeReservation(@RequestBody BookingDto bookingDto) {
        Booking booking = bookingBuilder(bookingDto);
        Booking returnedBooking = bookingService.makeReservation(booking);
        BookingDto returnedBookingDto = bookingDtoBuilder(returnedBooking);
        return ResponseEntity.ok(returnedBookingDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete booking")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.cancelReservation(id);
        return ResponseEntity.ok().build();
    }

    private Booking bookingBuilder(BookingDto bookingDto) {
        return Booking.builder()
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .user(bookingService.findUserById(bookingDto.getUserId()))
                .room(bookingService.findRoomById(bookingDto.getRoomId()))
                .build();
    }

    private BookingDto bookingDtoBuilder(Booking booking) {
        return BookingDto.builder()
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .userId(booking.getUser().getId())
                .roomId(booking.getRoom().getId())
                .build();
    }
}
