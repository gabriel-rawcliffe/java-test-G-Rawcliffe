package nz.co.pfr.art.Music.service;

import nz.co.pfr.art.Music.entities.Artist;
import nz.co.pfr.art.Music.entities.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArtistService {
    Logger log = LoggerFactory.getLogger(ArtistService.class);
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<String> getMostProductiveArtists(Integer topn) {

        var artists = artistRepository.findAll();

        var artistTrackCountMap = new HashMap<String, Integer>();
        for(int i = 0; i < artists.size(); i++) {
            var artist = artists.get(i);
            log.info("artist name {} cds {}", artist.getName(), artist.getCds().size());

            int trackCount = 0;
            for(int j = 0; j < artist.getCds().size(); j ++) {
                var cd = artist.getCds().get(j);
                log.info("cd title {} tracks {}", cd.getTitle(), cd.getTracks().size());
                trackCount+= cd.getTracks().size();

            }
            artistTrackCountMap.put(artist.getName(), trackCount);

        }
        int highest = 0;
        var mostProductiveArtists = new ArrayList<String>();

        for(var artist : artistTrackCountMap.keySet()) {
            var totalTrackCount = artistTrackCountMap.get(artist);
            if (highest < totalTrackCount) {
                highest = totalTrackCount;
                mostProductiveArtists.clear();
                mostProductiveArtists.add(artist);
            } else if (highest == totalTrackCount) {
                mostProductiveArtists.add(artist);
            }
            log.info("artist: {} total tracks: {}", artist, artistTrackCountMap.get(artist));
        }
        log.info("most productive artist is: {}", mostProductiveArtists);


        return mostProductiveArtists.subList(0, topn);
    }

    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

}
