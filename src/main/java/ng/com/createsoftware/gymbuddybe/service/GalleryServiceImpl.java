package ng.com.createsoftware.gymbuddybe.service;

import lombok.extern.log4j.Log4j2;
import ng.com.createsoftware.gymbuddybe.model.Gallery;
import ng.com.createsoftware.gymbuddybe.model.User;
import ng.com.createsoftware.gymbuddybe.properties.FileStorageProperties;
import ng.com.createsoftware.gymbuddybe.repository.GalleryRepository;
import ng.com.createsoftware.gymbuddybe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Log4j2
@Service
public class GalleryServiceImpl implements  GalleryService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    GalleryRepository galleryRepository;

    private final Path fileStorageLocation;

    @Autowired
    public GalleryServiceImpl(FileStorageProperties fileStorageProperties) {
        fileStorageLocation = Path.of(fileStorageProperties.getUploadDir());
        try{
            Files.createDirectories(fileStorageLocation);
          }catch(IOException ex){
            log.error("Could not create the directory where the uploaded file will be stored: ", ex);
          }
    }


    @Override
    public Gallery storeImage(MultipartFile imageFile, Gallery gallery, Long userId) throws IOException {
       //get image
        String pictureImage = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
        //check if the filename contains invalid characters
        if(pictureImage.contains("..")){
            throw new RuntimeException("File contains invalid path sequence");
        }

        //get filename
        String filename = pictureImage.substring(0, pictureImage.lastIndexOf("."))
                .replace(".", "") + pictureImage.substring(pictureImage.lastIndexOf(".") + 1);

        //convert path string to Path
//        Path targetLocation = fileStorageLocation.resolve(filename);
        Path targetLocation = fileStorageLocation.resolve(pictureImage);

        //copy file to the target location and replace existing file with the same name if exists
      //   Files.copy(imageFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        byte[] imageBytes = imageFile.getBytes();
        Path picturePath = Paths.get(targetLocation.toUri());
        Files.write(picturePath, imageBytes);

        //get user
        User user = userRepository.findById(userId).get();

        //save gallery
        Gallery newGallery = new Gallery();
        newGallery.setImage(imageFile.getOriginalFilename());
        newGallery.setUser(user);
        galleryRepository.save(newGallery);
        return newGallery;

    }

    @Override
    public Resource getImage(String filename) {
        try{
             Path file = fileStorageLocation.resolve(filename).normalize();
             Resource resource = new UrlResource(file.toUri());
             if(resource.exists() || resource.isReadable()){
                 return resource;
             }else{
                 throw new RuntimeException("Could not read file with name: " + filename);
             }
          }catch(MalformedURLException ex){
            throw new RuntimeException("Could not retrieve File with name: " + filename);
          }
    }
}
