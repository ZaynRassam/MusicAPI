import { useState, useEffect } from 'react';
import './App.css';
import ArtistList from './ArtistList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import ArtistDetail from './ArtistDetail';
import AlbumDetail from './AlbumDetail';
import NavBar from './NavBar';
import AddArtist from './AddArtist';
import UpdateArtist from './UpdateArtist';

function App() {
  const [artists, setArtists] = useState([]);
  const [loading, setLoading] = useState(true);   
  const [error, setError] = useState(false);   
  const [isEditMode, setIsEditMode] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/artists/all')
      .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
      })
      .then(data => setArtists(data))
      .catch(() => setError(true))
      .finally(() => setLoading(false));
  }, []);

  return (
    <Router>
      <div className="container">
        {/* <NavBar></NavBar> */}
        <Routes>
          <Route
            path="/"
            element={
                <div className="container mt-4">
                  {loading && <p>Loading...</p>}
                  {!loading && error && <p>Failed to fetch artists. Is the API on?</p>}
                  {!loading && !error && artists.length === 0 && <p>No artists found.</p>}

                  {!loading && !error && artists.length > 0 && (
                    <div>
                      <div className="position-relative mb-4">
                        <h2 className="text-center">Artists</h2>
                        <div className="position-absolute top-0 end-0 d-flex gap-2">
                          <a href="/add-artist" className="btn btn-primary">Upload</a>
                          <button
                            className="btn btn-primary"
                            onClick={() => setIsEditMode(prev => !prev)}
                          >
                            {isEditMode ? 'Done Editing' : 'Edit'}
                          </button>
                        </div>
                      </div>

                      <ArtistList artists={artists} isEditMode={isEditMode} />
                    </div>
                  )}
                </div>
            }
          />
          <Route path="/artist/:artistId" element={<ArtistDetail />} />
          <Route path="/albums/:albumId/details" element={<AlbumDetail />} />
          <Route path="/add-artist" element={<AddArtist />} />
          <Route path="/update-artist/:artistId" element={<UpdateArtist />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
