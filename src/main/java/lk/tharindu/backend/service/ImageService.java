package lk.tharindu.backend.service;

import lk.tharindu.backend.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {

    Image saveImage(Image image);
    List<Image> fetchAllImages();
    Image fetchImage(Image image);
    Optional<Image> findById(Integer id);
    void deteteById(Integer id);

}
