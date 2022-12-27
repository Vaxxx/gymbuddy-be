package ng.com.createsoftware.gymbuddybe.controller;


import lombok.AllArgsConstructor;
import ng.com.createsoftware.gymbuddybe.model.Location;
import ng.com.createsoftware.gymbuddybe.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/location")
@AllArgsConstructor
public class LocationController {

    private LocationService locationService;
    //add a location
    @PostMapping("/addLocation/{userId}")
    public ResponseEntity<String> addLocation(@RequestBody Location location, @PathVariable("userId") final Long userId){
        locationService.addLocation(location, userId);
        return new ResponseEntity<>("Location Added Successfully!", HttpStatus.OK);
    }

    //get location by id
    @GetMapping("/get/{locationId}")
    public ResponseEntity<Location> getLocationById(@PathVariable("locationId")final Long locationId){
        Location location = locationService.getLocationById(locationId);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    //edit location
    @PostMapping("/edit/{locationId}/{userId}")
    public ResponseEntity<Location> editLocation(@PathVariable("locationId")final Long locationId,
                                                 @RequestBody Location location,
                                                 @PathVariable("userId") final Long userId){
        Location locationToEdit = locationService.editLocation(location, locationId, userId);
        return new ResponseEntity<>(locationToEdit, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationId") final Long locationId){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
