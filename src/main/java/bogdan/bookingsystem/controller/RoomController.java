package bogdan.bookingsystem.controller;

import bogdan.bookingsystem.dto.RoomDto;
import bogdan.bookingsystem.enums.RoomType;
import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.Room;
import bogdan.bookingsystem.repository.RoomRepository;
import bogdan.bookingsystem.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllAvailableRooms(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Room> roomList = roomService.findAllAvailableRooms(startDate, endDate);
        return ResponseEntity.ok(roomList);
    }

    @GetMapping("/allRooms")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomDto> roomDtoList = rooms.stream().map(this::roomDtoResponseBuilder).toList();
        return ResponseEntity.ok(roomDtoList);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long id) {
        RoomDto roomDto;
        try {
            roomDto = roomService.getRoomById(id);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/roomsByPrice/{minPrice}/{maxPrice}")
    public ResponseEntity<List<RoomDto>> getRoomsByPriceRance(@PathVariable Integer minPrice, @PathVariable Integer maxPrice) {
        List<Room> roomList = roomService.findRoomsByPriceRange(minPrice, maxPrice);
        List<RoomDto> roomDtoList = roomList.stream().map(this::roomDtoResponseBuilder).collect(Collectors.toList());
        return ResponseEntity.ok(roomDtoList);
    }

    @GetMapping("/roomsByPrice/{sortingType}")
    public ResponseEntity<List<RoomDto>> getRoomByPriceOrder(@PathVariable String sortingType) {
        List<Room> roomList = roomService.findRoomsByPriceOrder(sortingType);
        List<RoomDto> roomDtoList = roomList.stream().map(this::roomDtoResponseBuilder).collect(Collectors.toList());
        return ResponseEntity.ok(roomDtoList);
    }

    @PostMapping("/rooms")
    public ResponseEntity<RoomDto> addNewRoom(@RequestBody RoomDto roomDto) {
        Room room = roomBuilder(roomDto, roomDto.getRoomType().toString());
        RoomDto roomDtoResponse = roomDtoCreateBuilder(roomService.save(room));
        return ResponseEntity.ok(roomDtoResponse);
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable Long id) {
        try {
            roomService.deleteById(id);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    public RoomDto roomDtoResponseBuilder(Room room) {
        return RoomDto.builder()
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .pricePerNight(room.getPricePerNight())
                .bookingsIdList(room.getBookings().stream().map(Booking::getId).collect(Collectors.toList()))
                .build();
    }

    public RoomDto roomDtoCreateBuilder(Room room) {
        return RoomDto.builder()
                .roomNumber(room.getRoomNumber())
                .roomType(room.getRoomType())
                .pricePerNight(room.getPricePerNight())
                .build();
    }

    public Room roomBuilder(RoomDto roomDto, String roomType) {
        return Room.builder()
                .roomNumber(roomDto.getRoomNumber())
                .pricePerNight(roomDto.getPricePerNight())
                .roomType(roomType.isEmpty() ? roomDto.getRoomType() : RoomType.valueOf(roomType))
                .build();
    }
}
