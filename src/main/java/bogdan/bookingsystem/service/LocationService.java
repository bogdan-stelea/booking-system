package bogdan.bookingsystem.service;

import bogdan.bookingsystem.model.Location;

import java.util.List;

public interface LocationService {

    void deleteLocation(Long locationId);
    Location save (Location location);
    List<Location> getAllLocations();

}
