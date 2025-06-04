import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';

const ArtistDetail = () => {
  const { artistId } = useParams();
  const [artist, setArtist] = useState(null);
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    // Fetch artist details
    fetch(`http://localhost:8080/artists/${artistId}`)
      .then((res) => {
        if (!res.ok) throw new Error('Failed to fetch artist');
        return res.json();
      })
      .then((data) => {
        setArtist(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching artist data:", err);
        setError(true);
        setLoading(false);
      });
  }, [artistId]);

  if (loading) return <p>Loading...</p>;
  if (error || !artist) {
    return (
      <div className="container text-center mb-4">
        <h2>Artist Details</h2>
        <p>Failed to load artist. Is the API on?</p>
      </div>
    );
  }
  return (
    <div className="container mt-4">
      <h2 className="text-center">{artist.artistName}</h2>

      <img
        src={artist.artistUrl || 'https://placehold.co/800x400'}
        alt={`Portrait of ${artist.artistName}`}
        className="d-block mx-auto mb-4"
        style={{ maxWidth: '100%', height: 'auto' }}
      />

      <section className="mb-4">
        <h4>Biography</h4>
        <p>{artist.bio || 'No biography available.'}</p>
      </section>

      <section>
        <h4 className="text-center">Discography</h4>

        {artist.albums.length == 0 && <p className="text-center">Failed to load albums. Is the API on?</p>}

        {artist.albums.length > 0 ? (
          <div className="row justify-content-center">
            {artist.albums.map((album) => (
              <div className="col-md-4 col-sm-6 mb-4" key={album.albumId}>
                <Link to={`/albums/${album.albumId}/details`} style={{ textDecoration: 'none', color: 'inherit' }}>
                <div className="card h-100">
                  <img
                    src={album.albumUrl || 'https://www.shutterstock.com/image-vector/no-photo-thumbnail-graphic-element-600nw-2311073121.jpg'}
                    className="card-img-top"
                    alt={`Album art for ${album.albumName}`}
                  />
                  <div className="card-body text-center">
                    <p className="card-text">{album.releaseYear}</p>
                    <h5 className="card-title">{album.albumName}</h5>
                  </div>
                </div>
                </Link>
              </div>
            ))}
          </div>
        ) : (
          artist.albums.length == 0 && <p className="text-center">No albums found for this artist.</p>
        )}
      </section>
    </div>
  );
};

export default ArtistDetail;
