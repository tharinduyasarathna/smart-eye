package lk.tharindu.backend.service;

import lk.tharindu.backend.model.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    Video saveVideo(Video video);
    List<Video> fetchAllVideos();
    Video fetchVideo(Video video);
    Optional<Video> findById(Integer id);
    void deteteById(Integer id);
}
