package ng.com.createsoftware.gymbuddybe.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ng.com.createsoftware.gymbuddybe.model.Gallery;
import ng.com.createsoftware.gymbuddybe.service.GalleryService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/gallery")
@AllArgsConstructor
@Log4j2
public class GalleryController {

    private GalleryService galleryService;

    //upload image
    @PostMapping("/upload/{userId}")
    public ResponseEntity<String> uploadImage(@RequestParam("pictureImage")MultipartFile imageFile, Gallery gallery,
                                         @PathVariable("userId") final Long userId) throws IOException {
        galleryService.storeImage(imageFile, gallery, userId);

        return new ResponseEntity<>("Image Added Successfully!", HttpStatus.OK);
    }

    //download image
    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadImage(@PathVariable("filename")String filename, HttpServletRequest request) throws IOException {
        Resource fileResource = galleryService.getImage(filename);

        String contentType = null;

        try{
            contentType = request.getServletContext().getMimeType(fileResource.getFile().getAbsolutePath());
          }catch(IOException ex){
             log.error("Could not determine file type" , ex);
          }

        if(contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +fileResource.getFilename() + "\"")
                .body(fileResource);
    }
}
