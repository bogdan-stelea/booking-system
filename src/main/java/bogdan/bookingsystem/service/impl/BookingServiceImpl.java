package bogdan.bookingsystem.service.impl;

import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.Room;
import bogdan.bookingsystem.model.User;
import bogdan.bookingsystem.repository.BookingRepository;
import bogdan.bookingsystem.repository.RoomRepository;
import bogdan.bookingsystem.repository.UserRepository;
import bogdan.bookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    @Override
    public Booking makeReservation(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelReservation(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @Override
    public List<Booking> findUserBookings(User user) {
        return bookingRepository.findAllByUserId(user.getId());
    }
}
