package nz.co.pfr.art.Music;

import nz.co.pfr.art.Music.entities.Artist;
import nz.co.pfr.art.Music.entities.Cd;
import nz.co.pfr.art.Music.entities.Track;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    @SafeVarargs
    private static Artist createArtist(String artistName, List<Cd>... cds) {
        var artist = new Artist();
        artist.setName(artistName);
        artist.setCds(new ArrayList<>());

        for(var cd: cds) {
            artist.getCds().addAll(cd);
        }
        return artist;
    }

    private static List<Cd> createCds(String cdtitle, String... tracks) {

        var cds = new ArrayList<Cd>();

        var tracks1  = new ArrayList<Track>();

        var cd = new Cd();
        cd.setTitle(cdtitle);
        cd.setTracks(tracks1);
        cds.add(cd);
        for(var trackTitle: tracks) {
            var track = new Track();
            track.setTitle(trackTitle);
            tracks1.add(track);

        }

        return cds;
    }

    public static List<Artist> testData() {
        var artists = new ArrayList<Artist>();

        artists.add(createArtist("Eminem",
                createCds("The Eminem Show","Marshall Matters", "Without Me", "The Real Slim Shady", "Mockingbird", "Lose Yourself")));
        artists.add(createArtist("SB19",
                createCds("Pagsibol","Gento", "I want you", "Liham", "Mapa")));
        artists.add(createArtist("Michael Jackson",
                createCds("Thriller", "Wanna be Startlin' so...", "Baby Be Mine", "The Girl Is Mine", "Beat it", "Billie Jean","Human Nature", "P.Y.T Pretty Young Thing"),
                createCds("Dangerous", "Jam", "Why you Wanna Trip", "In the Closet", "Remember The Time", "Heal the World","Black or White", "She drives me mad")));
        artists.add(createArtist("The Wonders",
                createCds( "Doing that thing you do", "Doing that thing you do")));
        return artists;
    }

}
