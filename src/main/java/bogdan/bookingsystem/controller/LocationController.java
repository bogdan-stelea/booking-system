package bogdan.bookingsystem.controller;

import bogdan.bookingsystem.dto.BookingDto;
import bogdan.bookingsystem.dto.LocationDto;
import bogdan.bookingsystem.model.Booking;
import bogdan.bookingsystem.model.Location;
import bogdan.bookingsystem.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        List<LocationDto> locationDtos = locations.stream().map(this::locationDtoBuilder).toList();
        return ResponseEntity.ok(locationDtos);
    }

    @PostMapping
    public ResponseEntity<LocationDto> saveLocation(@RequestBody LocationDto locationDto) {
        Location location = locationBuilder(locationDto);
        Location returnedLocation = locationService.save(location);
        LocationDto returnedLocationDto = locationDtoBuilder(returnedLocation);
        return ResponseEntity.ok(returnedLocationDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(Long locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.ok().build();
    }

    private LocationDto locationDtoBuilder(Location location) {
        return LocationDto.builder()
                .name(location.getName())
                .country(location.getCountry())
                .build();
    }

    private Location locationBuilder(LocationDto locationDto) {
        return Location.builder()
                .name(locationDto.getName())
                .country(locationDto.getCountry())
                .build();
    }
}
