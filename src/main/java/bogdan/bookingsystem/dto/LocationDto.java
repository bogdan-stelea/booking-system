package bogdan.bookingsystem.dto;

import bogdan.bookingsystem.model.Building;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LocationDto {

    private String name;

    private String country;

    private List<Long> buildings;
}
