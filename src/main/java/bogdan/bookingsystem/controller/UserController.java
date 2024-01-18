package bogdan.bookingsystem.controller;

import bogdan.bookingsystem.dto.BookingDto;
import bogdan.bookingsystem.dto.UserDto;
import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.User;
import bogdan.bookingsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/bookings")
    public ResponseEntity<List<Long>> getBookingsByUserId(@PathVariable Long id) {
        List<Long> bookingList = userService.displayBookingsById(id);
        return ResponseEntity.ok(bookingList);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Long>> getBookingsByUserId(@RequestParam Long id, @RequestParam LocalDate startDate) {
        List<Booking> returnedBookingList = userService.getBookingByUserIdList(id);
        List<Booking> filteredBookingList = returnedBookingList.stream().filter(booking -> booking.getStartDate().isAfter(startDate)).toList();
        List<Long> bookingList = filteredBookingList.stream().map(Booking::getId).collect(Collectors.toList());
        return ResponseEntity.ok(bookingList);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = users.stream().map(this::userDtoBuilder).toList();
        return ResponseEntity.ok(userDtos);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDto userDto) {
        User user = userBuilder(userDto);
        User returnedUser = userService.saveUser(user);
        return ResponseEntity.ok(returnedUser);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }


    private User userBuilder(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }

    private UserDto userDtoBuilder(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .bookingIds(user.getBookings().stream().map(Booking::getId).toList())
                .build();
    }

}
