package bogdan.bookingsystem.dto;

import bogdan.bookingsystem.enums.RoomType;
import bogdan.bookingsystem.model.Building;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Getter
@Setter
@Builder
public class RoomDto {

    private Integer roomNumber;

    private RoomType roomType;

    private Double pricePerNight;

    private List<Long> bookingsIdList;

    private Building building;
}
