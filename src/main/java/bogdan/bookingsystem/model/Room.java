package bogdan.bookingsystem.model;

import bogdan.bookingsystem.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private Integer roomNumber;

    @Column
    @NonNull
    private RoomType roomType;

    @Column
    private Double pricePerNight;

    @OneToMany(mappedBy = "room")
    private List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "bulding_id")
    @JsonIgnore
    private Building building;
}
