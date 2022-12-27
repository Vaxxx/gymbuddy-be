package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Location;
import ng.com.createsoftware.gymbuddybe.model.User;
import ng.com.createsoftware.gymbuddybe.repository.LocationRepository;
import ng.com.createsoftware.gymbuddybe.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    private final UserRepository userRepository;

    public LocationServiceImpl(LocationRepository locationRepository, UserRepository userRepository) {
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Location addLocation(Location location, Long userId) {

        User user = userRepository.findById(userId).get();

        Location newLocation = new Location();
        return extractedLocation(location, user, newLocation);
    }

    @Override
    public Location getLocation(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() ->
                new IllegalArgumentException("Could not find Location with ID: " + locationId));
    }

    @Override
    public Location getLocationById(Long locationId) {
        return getLocation(locationId);
    }

    @Override
    public Location editLocation(Location location, Long locationId, Long userId) {

        User user = userRepository.findById(userId).get();

       Location locationToEdit = getLocation(locationId);
        return extractedLocation(location, user, locationToEdit);

    }

    private Location extractedLocation(Location location, User user, Location locationToEdit) {
        locationToEdit.setCity(location.getCity());
        locationToEdit.setCountry(location.getCountry());
        locationToEdit.setLatitude(location.getLatitude());
        locationToEdit.setLongitude(location.getLongitude());
        locationToEdit.setUser(user);

        locationRepository.save(locationToEdit);
        return locationToEdit;
    }

    @Override
    public Location deleteLocation(Long locationId) {
        Location location = getLocation(locationId);
        locationRepository.delete(location);
        return location;
    }
}
