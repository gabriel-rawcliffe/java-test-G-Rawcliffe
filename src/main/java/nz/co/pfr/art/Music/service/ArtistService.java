package nz.co.pfr.art.Music.service;

import nz.co.pfr.art.Music.entities.Artist;
import nz.co.pfr.art.Music.entities.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);
    private final ArtistRepository artistRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<String> getMostProductiveArtists(Integer topn) {
        List<Artist> artists = artistRepository.findAll();

        Map<String, Integer> artistTrackCountMap = artists.stream().collect(Collectors.toMap(
                Artist::getName,
                artist -> artist.getCds().stream().mapToInt(cd -> cd.getTracks().size()).sum()));

        List<Map.Entry<String, Integer>> sortedArtists = artistTrackCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toList());

        List<String> mostProductiveArtists = sortedArtists.stream()
                .limit(topn)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        mostProductiveArtists
                .forEach(artist -> log.info("artist: {} total tracks: {}", artist, artistTrackCountMap.get(artist)));

        return mostProductiveArtists;
    }

    // not currently working
    public List<String> getMostProductiveArtistsSQL(int topn) {
        String sql = "SELECT a.name, COUNT(t.id) AS trackCount " +
                "FROM artist a " +
                "INNER JOIN cd ON cd.artistid = a.artistid " +
                "INNER JOIN track t ON t.cdid = cd.id " +
                "GROUP BY a.artistid, a.name " +
                "ORDER BY COUNT(t.id) DESC";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> results = query.setMaxResults(topn).getResultList();

        return results.stream()
                .map(result -> (String) result[0]) // Assuming the artist's name is the first element in the result
                                                   // array
                .collect(Collectors.toList());
    }

    // public List<String> getArtistNames() {
    // String sql = "SELECT a.name FROM artist a";
    // Query query = entityManager.createNativeQuery(sql);
    // List<String> artistNames = query.getResultList(); // This will return a List
    // of artist names
    // return artistNames;
    // }
}
