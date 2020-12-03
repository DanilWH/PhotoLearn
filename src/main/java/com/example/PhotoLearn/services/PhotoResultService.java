package com.example.PhotoLearn.services;

import com.example.PhotoLearn.dto.PhotoResultDto;
import com.example.PhotoLearn.models.PhotoResult;
import com.example.PhotoLearn.repositories.PhotoResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PhotoResultService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoResultRepository photoResultRepository;

    @Autowired
    private UserService userService;

    public PhotoResult savePhotoResult(
            PhotoResultDto photoResultDto, MultipartFile multipartFile
    ) throws Exception {
        // upload the image first.
        String filename = uploadPhoto(multipartFile);
        // data won't be saved into the database if there was an error uploading the image.
        if (filename == null) throw new Exception("Couldn't upload the image.");

        // create a new object.
        PhotoResult photoResult = new PhotoResult();

        // set the fields with the values of the DTO.
        photoResult.setDescription(photoResultDto.getDescription());
        photoResult.setFilename(filename);
        photoResult.setUser(this.userService.getCurrentUserOrElseThrow());
        photoResult.setTutorial(photoResultDto.getTutorial());

        return this.photoResultRepository.save(photoResult);
    }

    public void deletePhotoResult(Long photoResultId) {
        // find the 'PhotoResult' in the database.
        PhotoResult photoResult = this.photoResultRepository.findById(photoResultId).orElseThrow(() -> new NoResultException());
        // remove the image from the server.
        this.deletePhoto(photoResult.getFilename());
        // remove the record from the database.
        this.photoResultRepository.delete(photoResult);
    }

    public PhotoResultDto mapFromEntityToDto(PhotoResult photoResult) {
        ModelMapper modelMapper = new ModelMapper();

        PhotoResultDto photoResultDto = modelMapper.map(photoResult, PhotoResultDto.class);

        return photoResultDto;
    }

    private String uploadPhoto(MultipartFile multipartFile) throws IOException {
        // check if there's an image.
        // TODO.
        if (multipartFile != null && multipartFile.isEmpty()) return null;

        // create a random UUID.
        String fileUUID = UUID.randomUUID().toString();
        // assign the UUID to the filename;
        String filename = fileUUID + "." + multipartFile.getOriginalFilename();

        // make the directories if they don't exist.
        File directory = new File(this.uploadPath);
        if (!directory.exists())
            directory.mkdirs();

        // save the image (file) in the server directory.
        multipartFile.transferTo(new File(this.uploadPath + filename));

        // return the file name as a result.
        return filename;
    }

    private void deletePhoto(String filename) {
        if (filename != null && !filename.isEmpty()) {
            File file = new File(uploadPath + filename);
            file.delete();
        }
    }

}
