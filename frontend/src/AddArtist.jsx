import React, { useState, useRef, useCallback } from "react";
import { DndProvider, useDrag, useDrop } from "react-dnd";
import { HTML5Backend } from "react-dnd-html5-backend";
import { useNavigate } from "react-router-dom";



const SongItem = ({
  song,
  index,
  albumIndex,
  moveSong,
  handleSongChange,
  removeSong,
}) => {
  const ref = useRef(null);

  const [, drop] = useDrop({
    accept: "song",
    hover(item) {
      if (item.index === index && item.albumIndex === albumIndex) return;
      if (item.albumIndex === albumIndex) {
        moveSong(albumIndex, item.index, index);
        item.index = index;
      }
    },
  });

  const [{ isDragging }, drag] = useDrag({
    type: "song",
    item: { index, albumIndex },
    collect: (monitor) => ({
      isDragging: monitor.isDragging(),
    }),
  });

  drag(drop(ref));

  return (
    <div
      ref={ref}
      className={`border-start border p-3 rounded bg-white ${
        isDragging ? "opacity-50" : ""
      }`}
    >
      <div className="d-flex justify-content-between align-items-center mb-2">
        <h5 className="mb-0">Song {index + 1}</h5>
        <button
          className="btn btn-danger btn-sm"
          onClick={() => removeSong(albumIndex, index)}
        >
          Delete
        </button>
      </div>
      <input
        type="text"
        className="form-control mb-2"
        name="songName"
        value={song.songName}
        onChange={(e) => handleSongChange(albumIndex, index, e)}
        placeholder="Song Name"
      />
      <input
        type="number"
        className="form-control"
        name="duration"
        value={song.duration}
        onChange={(e) => handleSongChange(albumIndex, index, e)}
        placeholder="Duration (seconds)"
        min={0}
      />
    </div>
  );
};

export default function ArtistAlbumForm() {
    const [submitAnother, setSubmitAnother] = useState(false);
    const navigate = useNavigate();
  const [artist, setArtist] = useState({
    artistName: "",
    artistUrl: "",
    bio: "",
    albums: [],
  });

  const handleArtistChange = (e) => {
    const { name, value } = e.target;
    setArtist((prev) => ({ ...prev, [name]: value }));
  };

  const addAlbum = () => {
    setArtist((prev) => ({
      ...prev,
      albums: [
        ...prev.albums,
        { albumName: "", albumUrl: "", releaseYear: "", description: "", songs: [] },
      ],
    }));
  };

  const removeAlbum = (albumIndex) => {
    setArtist((prev) => ({
      ...prev,
      albums: prev.albums.filter((_, i) => i !== albumIndex),
    }));
  };

  const handleAlbumChange = (albumIndex, e) => {
    const { name, value } = e.target;
    setArtist((prev) => {
      const albums = [...prev.albums];
      albums[albumIndex] = { ...albums[albumIndex], [name]: value };
      return { ...prev, albums };
    });
  };

  const addSong = (albumIndex) => {
    setArtist((prev) => {
      const albums = [...prev.albums];
      albums[albumIndex] = {
        ...albums[albumIndex],
        songs: [...albums[albumIndex].songs, { songName: "", duration: "" }],
      };
      return { ...prev, albums };
    });
  };

  const removeSong = (albumIndex, songIndex) => {
    setArtist((prev) => {
      const albums = [...prev.albums];
      albums[albumIndex] = {
        ...albums[albumIndex],
        songs: albums[albumIndex].songs.filter((_, i) => i !== songIndex),
      };
      return { ...prev, albums };
    });
  };

  const moveSong = (albumIndex, fromIndex, toIndex) => {
    setArtist((prev) => {
      const albums = [...prev.albums];
      const songs = [...albums[albumIndex].songs];
      const [movedSong] = songs.splice(fromIndex, 1);
      songs.splice(toIndex, 0, movedSong);
      albums[albumIndex] = { ...albums[albumIndex], songs };
      return { ...prev, albums };
    });
  };

  const handleSongChange = (albumIndex, songIndex, e) => {
    const { name, value } = e.target;
    setArtist((prev) => {
      const albums = [...prev.albums];
      const songs = [...albums[albumIndex].songs];
      songs[songIndex] = { ...songs[songIndex], [name]: value };
      albums[albumIndex] = { ...albums[albumIndex], songs };
      return { ...prev, albums };
    });
  };

  const handleSubmit = async () => {
    // Format data as per your required structure
    const data = {
      artistName: artist.artistName,
      artistUrl: artist.artistUrl,
      bio: artist.bio,
      albums: artist.albums.map((album) => ({
        albumName: album.albumName,
        albumUrl: album.albumUrl,
        releaseYear: album.releaseYear,
        description: album.description,
        songs: album.songs.map((song, idx) => ({
          songName: song.songName,
          duration: Number(song.duration) || 0,
          trackNumber: idx + 1,
        })),
      })),
    };

    console.log("Submitting data:", data);

    try {
      const response = await fetch("http://localhost:8080/artists/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const result = await response.text();
      console.log("Upload successful:", result);
      alert("Artist submitted successfully!");
      if (submitAnother) {
        setArtist({
          artistName: "",
          artistUrl: "",
          bio: "",
          albums: [],
        });
      } else {
        navigate("/", { replace: true });
        window.location.reload();
      }
    } catch (error) {
      console.error("Upload failed:", error);
      alert("Failed to submit artist data.");
    }


    
  };

  return (
    <DndProvider backend={HTML5Backend}>
      <div className="container my-5">
        <h1 className="mb-4 text-center">Artist Upload</h1>

        <div className="mb-4">
          <input
            type="text"
            className="form-control mb-2"
            name="artistName"
            value={artist.artistName}
            onChange={handleArtistChange}
            placeholder="Artist Name"
          />
          <input
            type="text"
            className="form-control mb-2"
            name="artistUrl"
            value={artist.artistUrl}
            onChange={handleArtistChange}
            placeholder="Artist URL"
          />
          <textarea
            className="form-control"
            name="bio"
            value={artist.bio}
            onChange={handleArtistChange}
            placeholder="Artist Bio"
          />
        </div>

        {artist.albums.map((album, albumIndex) => (
          <div key={albumIndex} className="card mb-4">
            <div className="card-body">
              <div className="d-flex justify-content-between align-items-center mb-3">
                <h4 className="mb-0">Album {albumIndex + 1}</h4>
                <button
                  className="btn btn-danger"
                  onClick={() => removeAlbum(albumIndex)}
                >
                  Delete Album
                </button>
              </div>
              <input
                type="text"
                className="form-control mb-2"
                name="albumName"
                value={album.albumName}
                onChange={(e) => handleAlbumChange(albumIndex, e)}
                placeholder="Album Name"
              />
              <input
                type="text"
                className="form-control mb-2"
                name="albumUrl"
                value={album.albumUrl}
                onChange={(e) => handleAlbumChange(albumIndex, e)}
                placeholder="Album URL"
              />
              <input
                type="text"
                className="form-control mb-2"
                name="releaseYear"
                value={album.releaseYear}
                onChange={(e) => handleAlbumChange(albumIndex, e)}
                placeholder="Release Year"
              />
              <textarea
                className="form-control mb-3"
                name="description"
                value={album.description}
                onChange={(e) => handleAlbumChange(albumIndex, e)}
                placeholder="Album Description"
              />
              {album.songs.map((song, songIndex) => (
                <SongItem
                  key={songIndex}
                  index={songIndex}
                  albumIndex={albumIndex}
                  song={song}
                  moveSong={moveSong}
                  handleSongChange={handleSongChange}
                  removeSong={removeSong}
                />
              ))}
              <button
                className="btn btn-secondary mt-3"
                onClick={() => addSong(albumIndex)}
              >
                Add Song
              </button>
            </div>
          </div>
        ))}



        <div className="d-flex flex-column gap-2">
          <button className="btn btn-primary" onClick={addAlbum}>
            Add Album
          </button>
          <div className="form-check mb-3">
            <input
                className="form-check-input"
                type="checkbox"
                id="submitAnother"
                checked={submitAnother}
                onChange={() => setSubmitAnother(!submitAnother)}
            />
            <label className="form-check-label" htmlFor="submitAnother">
                Submit another artist after this
            </label>
        </div>
          <button className="btn btn-success" onClick={handleSubmit}>
            Submit
          </button>
        </div>
      </div>
    </DndProvider>
  );
}
