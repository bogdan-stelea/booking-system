package bogdan.bookingsystem.service.impl;

import bogdan.bookingsystem.dto.LocationDto;
import bogdan.bookingsystem.model.Location;
import bogdan.bookingsystem.repository.LocationRepository;
import bogdan.bookingsystem.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    @Override
    public Location save(Location location) {
        locationRepository.save(location);
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}
