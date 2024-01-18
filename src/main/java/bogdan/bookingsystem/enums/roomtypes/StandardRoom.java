package bogdan.bookingsystem.enums.roomtypes;

import bogdan.bookingsystem.enums.RoomType;
import bogdan.bookingsystem.model.Room;

public class StandardRoom extends Room {

    public StandardRoom() {
        setRoomType(RoomType.StandardRoom);
        setPricePerNight(80.00);
    }
}
