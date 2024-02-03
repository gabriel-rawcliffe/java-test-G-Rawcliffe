package nz.co.pfr.art.Music;

import com.github.javafaker.Faker;
import nz.co.pfr.art.Music.entities.ArtistRepository;
import nz.co.pfr.art.Music.entities.CdRepository;
import nz.co.pfr.art.Music.entities.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MusicApplication implements CommandLineRunner {
    private final ArtistRepository artistRepository;
    private final CdRepository cdRepository;
    private final TrackRepository trackRepository;

    private Logger log = LoggerFactory.getLogger(MusicApplication.class);

    public MusicApplication(ArtistRepository artistRepository, CdRepository cdRepository,
            TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.cdRepository = cdRepository;
        this.trackRepository = trackRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var faker = new Faker(); // Assuming you're using this for generating test data.

        var artists = DummyData.testData(); // Ensure DummyData.testData() is correctly implemented.

        for (var artist : artists) {
            artist = artistRepository.save(artist);
            for (var cd : artist.getCds()) {
                cd.setArtist(artist);
                cd = cdRepository.save(cd);
                for (var track : cd.getTracks()) {
                    track.setCd(cd);
                    trackRepository.save(track);
                }
            }
        }
    }

}
