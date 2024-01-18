package bogdan.bookingsystem.enums.roomtypes;

import bogdan.bookingsystem.enums.RoomType;
import bogdan.bookingsystem.model.Room;

public class SuiteRoom extends Room {

    public SuiteRoom() {
        setRoomType(RoomType.SuiteRoom);
        setPricePerNight(200.00);
    }
}
