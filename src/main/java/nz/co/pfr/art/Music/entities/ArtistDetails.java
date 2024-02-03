package nz.co.pfr.art.Music.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name="artistDetails", types = {Artist.class})
public interface ArtistDetails {
    public UUID getId();
    public String getName();


}
