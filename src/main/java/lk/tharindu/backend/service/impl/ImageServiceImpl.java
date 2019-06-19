package lk.tharindu.backend.service.impl;

import lk.tharindu.backend.exception.CustomDataIntergrityVoilationException;
import lk.tharindu.backend.exception.DataNotFoundException;
import lk.tharindu.backend.model.Image;
import lk.tharindu.backend.repository.ImageRepository;
import lk.tharindu.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image saveImage(Image image) {
//        if (image.getName().trim().isEmpty()){
//            throw new BodyContentNotValidException("Can't enter empty name");
//        }


        image.setName("img_"+ java.time.LocalDateTime.now());

        //check whether name is already exist.
        Optional<Image> optional= imageRepository.findByName(image.getName());
        if(optional.isPresent()){
            throw new CustomDataIntergrityVoilationException("Name Already Exists");
        }else {
            return imageRepository.save(image);
        }

    }

    @Override
    public List<Image> fetchAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image fetchImage(Image image) {
        Optional<Image> optionalImage = imageRepository.findById(image.getId());

        if (optionalImage.isPresent()){
            return optionalImage.get();
        }
        else {
            throw new DataNotFoundException("Image does not exist");
        }
    }

    @Override
    public Optional<Image> findById(Integer id) {
        Optional<Image> optionalImage = imageRepository.findById(id);

        if (optionalImage.isPresent()){
            return imageRepository.findById(id);
        }
        else {
            throw new DataNotFoundException("Image does not exist");
        }
    }

    @Override
    public void deteteById(Integer id) {
            imageRepository.deleteById(id);
    }




}
