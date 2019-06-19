package lk.tharindu.backend.repository;

import lk.tharindu.backend.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CameraRepository extends JpaRepository<Camera,Integer> {
    Optional<Camera> findByUrl(String url);
}
