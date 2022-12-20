package ng.com.createsoftware.gymbuddybe.service;

import ng.com.createsoftware.gymbuddybe.model.Admin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {

    Admin addAdmin(Admin admin, MultipartFile file, String pictureP);

    List<Admin> displayAdmins();

    String displayAdminPix(String pictureName);//display picture path

    Admin editAdmin(Admin admin, Long adimId);
}
