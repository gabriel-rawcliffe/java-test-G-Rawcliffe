package nz.co.pfr.art.Music.controller;

import nz.co.pfr.art.Music.service.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MusicController {
    private final ArtistService artistService;
    Logger log = LoggerFactory.getLogger(MusicController.class);

    public MusicController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/artists/mostProductive")
    public ResponseEntity<List<String>> mostProductiveArtists(
            @RequestParam(name = "topn", defaultValue = "1") Integer topn) {
        log.info("getting top {} most productive artists", topn);
        return ResponseEntity.ok(artistService.getMostProductiveArtists(topn));

    }

}
