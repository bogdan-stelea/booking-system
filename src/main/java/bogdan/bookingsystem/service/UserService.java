package bogdan.bookingsystem.service;

import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.User;

import java.util.List;

public interface UserService {
    List<Long> displayBookingsById(Long id);
    List<Booking> getBookingByUserIdList(Long id);
    User saveUser(User user);
    List<User> getAllUsers();
    void deleteUser(Long id);

}
