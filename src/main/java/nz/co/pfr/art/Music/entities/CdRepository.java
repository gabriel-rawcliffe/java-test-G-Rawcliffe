package nz.co.pfr.art.Music.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CdRepository  extends JpaRepository<Cd, UUID> {
}
