package bogdan.bookingsystem.service;

import bogdan.bookingsystem.dto.RoomDto;
import bogdan.bookingsystem.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<Room> findAllAvailableRooms(LocalDate start, LocalDate end);

    Room findById(Long id);

    Room save(Room room);

    void deleteById(Long id);

    RoomDto getRoomById(Long id);

    List<Room> getAllRooms();

    List<Room> findRoomsByPriceOrder(String sortingType);
    List<Room> findRoomsByPriceRange(Integer minPrice, Integer maxPrice);
}
