//package application.controller.api;
//
//import application.model.api.DataApiResult;
//import application.model.api.FileUploadResult;
//import application.model.dto.BlogDTO;
//import application.service.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/api/upload")
//public class UploadApiController {
//
//    @Autowired
//    FileStorageService storageService;
//
//    @PostMapping("/upload-image")
//    public FileUploadResult uploadImage(
//            @RequestParam("file") MultipartFile file) {
//        String message = "";
//
//        FileUploadResult result = new FileUploadResult();
//        try {
//            String newFilename = storageService.store(file);
//            message = "You successfully uploaded " +
//                    file.getOriginalFilename() + "!";
//            result.setMessage(message);
//            result.setSuccess(true);
//            result.setLink("/link/" +
//                    newFilename);
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setMessage(e.getMessage());
//        }
//        return result;
//    }
//
//}

package application.controller.api;

import application.model.api.DataApiResult;
import application.model.api.FileUploadResult;
import application.model.dto.BlogDTO;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Autowired
    FileStorageService storageService;

    @PostMapping("/upload-image")
    public FileUploadResult uploadImage(
            @RequestParam("file") MultipartFile file) {
        String message = "";

        FileUploadResult result = new FileUploadResult();
        try {
            String newFilename = storageService.store(file);
            message = "You successfully uploaded " +
                    file.getOriginalFilename() + "!";
            result.setMessage(message);
            result.setSuccess(true);
            result.setLink("/link/" +
                    newFilename);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }



}
