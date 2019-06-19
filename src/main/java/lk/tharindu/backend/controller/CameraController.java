package lk.tharindu.backend.controller;

import lk.tharindu.backend.model.Camera;
import lk.tharindu.backend.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smarteye")
public class CameraController {

    @Autowired
    CameraService cameraService;

    @RequestMapping(value = "/camera",method = RequestMethod.POST)
    public Camera addCamera(@RequestBody Camera camera){
        return cameraService.addCamera(camera);
    }

    @RequestMapping(value = "/camera",method = RequestMethod.GET)
    public List<Camera> fetchAllCamera(){
        return cameraService.fetchAllCamera();
    }

    @RequestMapping(value = "/camera/{id}",method = RequestMethod.GET)
    public ResponseEntity<Camera> fetchCamera(@PathVariable Integer id){
        Camera camera= new Camera();
        camera.setId(id);

        Camera camera1= cameraService.fetchCamera(camera);

        if(camera1==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(camera1);
        }


    }

    //update existing Camera ( only camera url )
    @RequestMapping(value="/updateCamera/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Camera> updateUrl(@PathVariable Integer id, @RequestBody Camera camera){
        Camera tempCamera = new Camera();
        tempCamera.setId(id);
        Camera updatedurl = cameraService.fetchCamera(tempCamera);

        if (!cameraService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        updatedurl.setUrl(camera.getUrl());
        return ResponseEntity.ok(cameraService.addCamera(updatedurl));
    }

    //delete existing user
    @RequestMapping(value = "/deleteCamera/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Camera> deleteCameras(@PathVariable Integer id){
        if (!cameraService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        cameraService.deteteById(id);
        return ResponseEntity.ok().build();
    }
}
