package lk.tharindu.backend.service.impl;

import lk.tharindu.backend.exception.BodyContentNotValidException;
import lk.tharindu.backend.exception.CustomDataIntergrityVoilationException;
import lk.tharindu.backend.exception.DataNotFoundException;
import lk.tharindu.backend.model.Camera;
import lk.tharindu.backend.repository.CameraRepository;
import lk.tharindu.backend.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraServiceImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Override
    public Camera addCamera(Camera camera) {

        if (camera.getName().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty name");
        }
        if (camera.getUrl().trim().isEmpty()){
            throw new BodyContentNotValidException("Can't enter empty URL");
        }
        //check whether email is already exist.
        Optional<Camera> optional= cameraRepository.findByUrl(camera.getUrl());
        if(optional.isPresent()){
            throw new CustomDataIntergrityVoilationException("URL Already Exists");
        }else {
            return cameraRepository.save(camera);
        }

    }

    @Override
    public List<Camera> fetchAllCamera() {
        return cameraRepository.findAll();
    }

    @Override
    public Camera fetchCamera(Camera camera) {
        Optional<Camera> optionalCamera = cameraRepository.findById(camera.getId());

        if (optionalCamera.isPresent()){
            return optionalCamera.get();
        }
        else {
            throw new DataNotFoundException("Camera does not exist");
        }
    }

    @Override
    public Optional<Camera> findById(Integer id) {
        Optional<Camera> optionalCamera = cameraRepository.findById(id);
        if (optionalCamera.isPresent()){
            return cameraRepository.findById(id);
        }else {
            throw new DataNotFoundException("Camera does not exist");
        }
    }

    @Override
    public void deteteById(Integer id) {
        cameraRepository.deleteById(id);
    }
}
