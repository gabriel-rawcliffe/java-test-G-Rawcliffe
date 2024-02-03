package nz.co.pfr.art.Music.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Projection(name="cdDetails", types = {Cd.class})
public interface CdDetails {
    public UUID getId();
    public String getTitle();

    @Value("#{target.getArtist().getName()}")
    public String getArtist();


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Value("#{target.getReleaseYear()}")
    public Date getYear();

    @Value("#{target.getTracks()}")
    public List<TrackFormat> getTracks();
    public interface TrackFormat{
        public String getTitle();
        public String getId();
    }




}
