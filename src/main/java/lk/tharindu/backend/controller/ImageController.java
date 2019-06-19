package lk.tharindu.backend.controller;

import lk.tharindu.backend.model.Image;
import lk.tharindu.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smarteye")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public Image saveImage(@RequestBody Image image){
        return imageService.saveImage(image);
    }

    @RequestMapping(value = "/image",method = RequestMethod.GET)
    public List<Image> fetchAllImages(){
        return imageService.fetchAllImages();
    }

    @RequestMapping(value = "/image/{id}",method = RequestMethod.GET)
    public ResponseEntity<Image> fetchImageById(@PathVariable Integer id){
        Image image=new Image();
        image.setId(id);
        Image image1 = imageService.fetchImage(image);


        if(image1==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(image1);
        }


    }

    //update existing image ( only name )
    @RequestMapping(value="/updateImageName/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Image> updateReport(@PathVariable Integer id, @RequestBody Image image){
        Image tempImage=new Image();
        image.setId(id);
        Image image1 = imageService.fetchImage(image);

        if (!imageService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        image1.setName(image.getName());
        return ResponseEntity.ok(imageService.saveImage(image1));
    }

    //delete existing report
    @RequestMapping(value = "/deleteReport/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Image> deleteReports(@PathVariable Integer id){
        if (!imageService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        imageService.deteteById(id);
        return ResponseEntity.ok().build();
    }
}
