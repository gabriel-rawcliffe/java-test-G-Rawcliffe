package nz.co.pfr.art.Music.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name="trackDetails", types = {Track.class})
public interface TrackDetails {
    public UUID getId();
    public String getTitle();

    @Value("#{target.getCd().getArtist().getName()}")
    public String getArtist();
    @Value("#{target.getCd().getTitle()}")
    public String getCd();



}
