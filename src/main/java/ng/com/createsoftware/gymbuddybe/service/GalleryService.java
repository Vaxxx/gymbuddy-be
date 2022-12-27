package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Gallery;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface GalleryService {

    Gallery storeImage(MultipartFile imageFile, Gallery gallery, Long userId) throws IOException;

    Resource getImage(String filename);
}
