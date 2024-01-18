package bogdan.bookingsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDto {

    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String password;

    private List<Long> bookingIds;
}
