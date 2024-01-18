package bogdan.bookingsystem.service.impl;

import bogdan.bookingsystem.model.Building;
import bogdan.bookingsystem.model.Location;
import bogdan.bookingsystem.repository.BuildingRepository;
import bogdan.bookingsystem.repository.LocationRepository;
import bogdan.bookingsystem.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final LocationRepository locationRepository;

    @Override
    public void save(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public void deleteBuilding(Long buildingId) {
        buildingRepository.deleteById(buildingId);
    }

    @Override
    public List<Building> getBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Location findLocationById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }
}
