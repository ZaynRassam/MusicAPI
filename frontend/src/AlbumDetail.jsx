import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

const AlbumDetail = () => {
  const { albumId } = useParams();

  const [album, setAlbum] = useState(null);
  const [artistName, setArtistName] = useState('');
  const [songs, setSongs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    // Fetch album details
    fetch(`http://localhost:8080/albums/${albumId}/details`)
      .then(res => {
        if (!res.ok) throw new Error('Failed to fetch album details');
        return res.json();
      })
      .then(data => {
        setAlbum(data);
        setArtistName(data.artistName || 'Unknown Artist');
        setLoading(false);
      })
      .catch(err => {
        console.error('Error fetching album details:', err);
        setError(true);
        setLoading(false);
      });
  }, [albumId]);

  const formatDuration = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins}:${secs < 10 ? '0' : ''}${secs}`;
  };

  if (loading) return <p>Loading album details...</p>;
  if (error) return <p>Failed to load album details. Is the API on?</p>;
  if (!album) return <p>Album not found.</p>;

  return (
    <div className="container test mt-4">
      <h1 className="text-center">{album.albumName}</h1>
      <h4 className="text-center mb-4">{album.artistName}</h4>

      <div className="text-center mb-4">
        <img
          src={album.albumUrl || 'https://placehold.co/300x300'}
          alt={`Album art for ${album.albumName}`}
          style={{ maxWidth: '100%', height: 'auto' }}
        />
      </div>

      <section>
        <h4>Track List</h4>
        {album.songs.length > 0 ? (
          <ol className="list-unstyled">
            {album.songs
            .slice()
            .sort((a, b) => a.trackNumber - b.trackNumber) 
            .map((song) => (
              <li
                key={song.songId}
                className="d-flex justify-content-between border-bottom py-2 song"
              >
                <span className="songName">{song.trackNumber}. {song.songName}</span>
                <span className="text-muted">{formatDuration(song.duration)}</span>
              </li>
            ))}
          </ol>
        ) : (
          <p>No songs found for this album.</p>
        )}
      </section>
    </div>
  );
};

export default AlbumDetail;
