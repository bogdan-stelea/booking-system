package bogdan.bookingsystem.controller;

import bogdan.bookingsystem.dto.BuildingDto;
import bogdan.bookingsystem.model.Building;
import bogdan.bookingsystem.model.Room;
import bogdan.bookingsystem.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@AllArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<BuildingDto>> getBuildings() {
        List<Building> buildings = buildingService.getBuildings();
        List<BuildingDto> buildingDtosList = buildings.stream().map(this::buildingDtoBuilder).toList();
        return ResponseEntity.ok(buildingDtosList);
    }

    @PostMapping
    public ResponseEntity<String> saveBuilding(BuildingDto buildingDto) {
        Building building = buildingBuilder(buildingDto);
        buildingService.save(building);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.ok().build();
    }

    private BuildingDto buildingDtoBuilder(Building building) {
        return BuildingDto.builder()
                .name(building.getName())
                .locationId(building.getLocation().getId())
                .roomsId(building.getRooms().stream().map(Room::getId).toList())
                .build();
    }

    private Building buildingBuilder(BuildingDto buildingDto) {
        return Building.builder()
                .name(buildingDto.getName())
                .location(buildingService.findLocationById(buildingDto.getLocationId()))
                .build();
    }
}
