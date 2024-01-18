package bogdan.bookingsystem.service;

import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.Room;
import bogdan.bookingsystem.model.User;

import java.util.List;

public interface BookingService {
    Booking makeReservation(Booking booking);
    void cancelReservation(Long id);
    User findUserById(Long id);
    Room findRoomById(Long id);
    Booking findBookingById(Long id);
    List<Booking> getAllBookings();
    List<Booking> findUserBookings(User user);
}
