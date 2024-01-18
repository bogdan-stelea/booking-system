package bogdan.bookingsystem.service.impl;

import bogdan.bookingsystem.dto.RoomDto;
import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.Room;
import bogdan.bookingsystem.repository.BookingRepository;
import bogdan.bookingsystem.repository.RoomRepository;
import bogdan.bookingsystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<Room> findAllAvailableRooms(LocalDate startDate, LocalDate endDate) {
        List<Room> rooms = roomRepository.findAll();
        List<Booking> bookings = bookingRepository.findAll();

        List<Booking> filteredBookings = bookings.stream()
                .filter(booking -> !booking.getStartDate().isBefore(startDate) && !booking.getEndDate().isAfter(endDate))
                .toList();

        return rooms.stream()
                .filter(room -> filteredBookings.stream().anyMatch(booking -> room.getBookings().contains(booking)))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long id) throws IllegalArgumentException {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new IllegalArgumentException("Room doesn't exist");
        }
        return room.map(value -> RoomDto.builder()
                .roomNumber(value.getRoomNumber())
                .roomType(value.getRoomType())
                .pricePerNight(value.getPricePerNight())
                .bookingsIdList(value.getBookings().stream().map(Booking::getId).collect(Collectors.toList()))
                .build()).orElse(null);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomsByPriceRange(Integer minPrice, Integer maxPrice) {
        List<Room> roomList = roomRepository.findAll();
        return roomList.stream().filter(room -> (room.getPricePerNight() <= maxPrice && room.getPricePerNight() >= minPrice)).collect(Collectors.toList());
    }

    @Override
    public List<Room> findRoomsByPriceOrder(String sortingType) {
        List<Room> roomList = roomRepository.findAll();
        roomList.sort(useComparator(sortingType));
        return roomList;
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room save(Room room) {
        roomRepository.save(room);
        return room;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            if (roomOptional.get().getBookings().isEmpty()) {
                roomRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    public Comparator<Room> useComparator(String order) {
        Comparator<Room> comparator;

        switch (order.toLowerCase()) {
            case "asc": {
                comparator = Comparator.comparing(Room::getPricePerNight);
                break;
            }
            case "desc": {
                comparator = Comparator.comparing(Room::getPricePerNight);
            }
            default: {
                return (o1, o2) -> 0;
            }
        }
        return comparator;
    }
}
