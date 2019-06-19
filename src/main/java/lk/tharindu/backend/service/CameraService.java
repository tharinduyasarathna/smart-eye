package lk.tharindu.backend.service;

import lk.tharindu.backend.model.Camera;

import java.util.List;
import java.util.Optional;

public interface CameraService {

    Camera addCamera(Camera camera);
    List<Camera> fetchAllCamera();
    Camera fetchCamera(Camera camera);
    Optional<Camera> findById(Integer id);
    void deteteById(Integer id);

}
