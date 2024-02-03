

CREATE TABLE artist (
  artistid UUID PRIMARY KEY NOT NULL,
  name text NOT NULL
);

CREATE UNIQUE INDEX artist_name ON artist (name);



CREATE TABLE cd (
  cdid UUID PRIMARY KEY NOT NULL,
  artistid UUID NOT NULL,
  title text NOT NULL,
  release_year datetime,
  FOREIGN KEY (artistid) REFERENCES artist(artistid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX cd_idx_artistid ON cd (artistid);
CREATE UNIQUE INDEX cd_title_artistid ON cd (title, artistid);



CREATE TABLE track (
  trackid UUID PRIMARY KEY NOT NULL,
  cdid UUID NOT NULL,
  title text NOT NULL,
  FOREIGN KEY (cdid) REFERENCES cd(cdid) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX track_idx_cdid ON track (cdid);
CREATE UNIQUE INDEX track_title_cdid ON track (title, cdid);
