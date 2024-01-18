package bogdan.bookingsystem.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BookingDto {

    private LocalDate startDate;

    private LocalDate endDate;

    private Long userId;

    private Long roomId;
}
