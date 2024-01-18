package bogdan.bookingsystem.service.impl;

import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.User;
import bogdan.bookingsystem.repository.BookingRepository;
import bogdan.bookingsystem.repository.UserRepository;
import bogdan.bookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<Long> displayBookingsById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        List<Booking> returnedBookingList = bookingRepository.findAllByUserId(id);
        return returnedBookingList.stream().map(Booking::getId).collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingByUserIdList(Long id) {
        return bookingRepository.findAllByUserId(id);
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
