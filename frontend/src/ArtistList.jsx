import { Link } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

const ArtistList = ({ artists, isEditMode }) => {
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleAIComplete = async (e, artistId) => {
    e.stopPropagation();
    e.preventDefault();
    const confirmed = window.confirm("Are you sure you want AI to complete this artist?");
    if (!confirmed) return;

    setLoading(true);

    try {
      const response = await fetch(`http://localhost:8080/artists/ai-complete/${artistId}`, {
        method: "GET",
      });

      if (!response.ok) {
        throw new Error(`Failed to autocomplete artist: ${response.status}`);
      }
      window.location.reload();
    } catch (err) {
      console.error("Error completing artist:", err);
      alert("Failed to complete the artist.");
      setLoading(false);
    }
  };

  const handleDelete = async (e, artistId) => {
    e.stopPropagation();
    e.preventDefault();

    const confirmed = window.confirm("Are you sure you want to delete this artist?");
    if (!confirmed) return;

    try {
      const response = await fetch(`http://localhost:8080/artists/delete/${artistId}`, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error(`Failed to delete artist: ${response.status}`);
      }
      window.location.reload();
    } catch (err) {
      console.error("Error deleting artist:", err);
      alert("Failed to delete the artist.");
    }
  };

  const handleUpdate = (e, artistId) => {
    e.stopPropagation();
    e.preventDefault();

    navigate(`/update-artist/${artistId}`);
  };

  return (
    <div style={{ position: 'relative' }}>
      {loading && (
        <div style={{
          position: 'fixed',
          top: 0, left: 0,
          width: '100%',
          height: '100%',
          backgroundColor: 'rgba(128, 128, 128, 0.7)',
          zIndex: 9999,
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          color: '#fff',
          fontSize: '2rem',
        }}>
          Loading...
        </div>
      )}

      <div className="row">
        {artists.map((artist) => (
          <div className="col-lg-4 col-md-6 col-sm-12 mb-4" key={artist.artistId}>
            <Link to={`/artist/${artist.artistId}`} className="text-decoration-none text-dark">
              <div className="card h-100">
                <img
                  src={artist.artistUrl || "https://placehold.co/600x400"}
                  className="card-img-top"
                  alt={artist.artistName}
                />
                <div className="card-body">
                  <h5 className="card-title">{artist.artistName}</h5>
                  <p className="card-text">Artist</p>
                  {isEditMode && (
                    <div className='px-2'>
                      <button
                        className="btn btn-sm btn-danger px-2"
                        onClick={(e) => handleUpdate(e, artist.artistId)}>
                        Update
                      </button>
                      <button
                        className="btn btn-sm btn-danger px-2 ms-2"
                        onClick={(e) => handleDelete(e, artist.artistId)}>
                        Delete
                      </button>
                      <div className="mt-2">
                        <button
                          className="btn btn-sm btn-primary px-2"
                          onClick={(e) => handleAIComplete(e, artist.artistId)}>
                          AI Complete
                        </button>
                      </div>
                    </div>
                  )}
                </div>
              </div>
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ArtistList;
