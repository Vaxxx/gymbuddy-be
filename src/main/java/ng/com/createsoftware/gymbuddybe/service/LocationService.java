package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Location;
import ng.com.createsoftware.gymbuddybe.model.User;

public interface LocationService {

    Location addLocation (Location location , Long userId);

    Location getLocation(Long locationId);

    Location getLocationById(Long locationId);

    Location editLocation(Location location, Long locationId, Long userId);

    Location deleteLocation (Long locationId);
}
