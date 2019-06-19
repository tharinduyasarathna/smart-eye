package lk.tharindu.backend.service.impl;

import lk.tharindu.backend.exception.BodyContentNotValidException;
import lk.tharindu.backend.exception.CustomDataIntergrityVoilationException;
import lk.tharindu.backend.exception.DataNotFoundException;
import lk.tharindu.backend.model.Video;
import lk.tharindu.backend.repository.VideoRepository;
import lk.tharindu.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Override
    public Video saveVideo(Video video) {
//        if(video.getName().trim().isEmpty()){
//            throw new BodyContentNotValidException("Can't enter empty name");
//        }

        video.setName("vid_"+ java.time.LocalDateTime.now());
        //check whether name is already exist.
        Optional<Video> optionalVideo= videoRepository.findByName(video.getName());
        if(optionalVideo.isPresent()){
            throw new CustomDataIntergrityVoilationException("Name Already Exists");
        }else {
            return videoRepository.save(video);
        }

    }

    @Override
    public List<Video> fetchAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video fetchVideo(Video video) {
        Optional<Video> optionalVideo = videoRepository.findById(video.getId());

        if (optionalVideo.isPresent()){
            return optionalVideo.get();
        }
        else {
            throw new DataNotFoundException("Video does not exist");
        }
    }

    @Override
    public Optional<Video> findById(Integer id) {
        Optional<Video> optionalVideo = videoRepository.findById(id);

        if (optionalVideo.isPresent()){
            return videoRepository.findById(id);
        }
        else {
            throw new DataNotFoundException("Image does not exist");
        }
    }

    @Override
    public void deteteById(Integer id) {
            videoRepository.deleteById(id);
    }
}
