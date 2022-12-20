package ng.com.createsoftware.gymbuddybe.controller;

import lombok.AllArgsConstructor;
import ng.com.createsoftware.gymbuddybe.model.Admin;
import ng.com.createsoftware.gymbuddybe.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;


    private final String pictureDir = "pictureUploads/admin/";

    @PostMapping("/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestParam("picture")MultipartFile file, Admin admin){
        adminService.addAdmin(admin, file, pictureDir);
        return new ResponseEntity<>("Admin added Successfully", HttpStatus.OK);
    }

}
