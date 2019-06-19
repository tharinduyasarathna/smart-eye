package lk.tharindu.backend.repository;

import lk.tharindu.backend.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Integer> {

    Optional<Video> findByName(String name);
}
