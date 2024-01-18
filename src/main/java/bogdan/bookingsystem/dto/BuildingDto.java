package bogdan.bookingsystem.dto;

import bogdan.bookingsystem.model.Location;
import bogdan.bookingsystem.model.Room;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BuildingDto {

    private String name;

    private Long locationId;

    private List<Long> roomsId;
}
