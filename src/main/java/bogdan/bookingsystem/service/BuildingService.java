package bogdan.bookingsystem.service;

import bogdan.bookingsystem.model.Building;
import bogdan.bookingsystem.model.Location;

import java.util.List;

public interface BuildingService {

    void save(Building building);

    void deleteBuilding(Long buildingId);

    List<Building> getBuildings();

    Location findLocationById(Long id);
}
