package bogdan.bookingsystem.enums.roomtypes;

import bogdan.bookingsystem.enums.RoomType;
import bogdan.bookingsystem.model.Room;

public class DeluxeRoom extends Room {

    public DeluxeRoom() {
        setRoomType(RoomType.DeluxeRoom);
        setPricePerNight(120.00);
    }
}
