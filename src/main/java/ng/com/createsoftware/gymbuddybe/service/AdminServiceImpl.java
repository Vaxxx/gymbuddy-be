package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Admin;
import ng.com.createsoftware.gymbuddybe.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    AdminRepository repository;


    @Override
    public Admin addAdmin(Admin admin, MultipartFile file, String picturePath) {
        //get picName
        String pictureName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try{
          if(pictureName.contains(".."))throw new Exception("Please check the picture path");

          //get picture Bytes
            byte [] pictureBytes = file.getBytes();
            Path path = Paths.get(picturePath + file.getOriginalFilename());
            Files.write(path, pictureBytes);
            
            Admin newAdmin = new Admin();
            newAdmin.setName(admin.getName());
            newAdmin.setEmail(admin.getEmail());
            newAdmin.setPicture(admin.getPicture());

            repository.save(newAdmin);

            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new Admin();
    }

    @Override
    public List<Admin> displayAdmins() {
        return null;
    }

    @Override
    public String displayAdminPix(String pictureName) {
        return null;
    }

    @Override
    public Admin editAdmin(Admin admin, Long adimId) {
        return null;
    }
}
