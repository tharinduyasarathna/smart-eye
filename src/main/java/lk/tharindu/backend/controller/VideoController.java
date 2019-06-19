package lk.tharindu.backend.controller;

import lk.tharindu.backend.model.Image;
import lk.tharindu.backend.model.Video;
import lk.tharindu.backend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/smarteye")
public class VideoController {

    @Autowired
    VideoService videoService;

    @RequestMapping(value = "/video",method = RequestMethod.POST)
    public Video saveVideo(@RequestBody Video video){
        return videoService.saveVideo(video);
    }

    @RequestMapping(value = "/video",method = RequestMethod.GET)
    public List<Video> fetchAllIVideos(){
        return videoService.fetchAllVideos();
    }

    @RequestMapping(value = "/video/{id}",method = RequestMethod.GET)
    public ResponseEntity<Video> fetchVideoById(@PathVariable Integer id){
        Video video=new Video();
        video.setId(id);
        Video video1 = videoService.fetchVideo(video);


        if(video1==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(video1);
        }


    }

    //update existing video ( only name )
    @RequestMapping(value="/updateVideoName/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Video> updateVideo(@PathVariable Integer id, @RequestBody Video video){
        Video tempVideo=new Video();
        video.setId(id);
        Video video1 = videoService.fetchVideo(tempVideo);

        if (!videoService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        video1.setName(video.getName());
        return ResponseEntity.ok(videoService.saveVideo(video1));
    }

    //delete existing video
    @RequestMapping(value = "/deleteVideo/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Image> deleteVideos(@PathVariable Integer id){
        if (!videoService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        videoService.deteteById(id);
        return ResponseEntity.ok().build();
    }
}
