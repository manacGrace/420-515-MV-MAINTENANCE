import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  getSeriesByGenre,
  getPlaylist,
  verifyToken,
} from "../utils/apiService";
import BackHome from "../Components/BackHome";
import Background from "../Components/Background";
import "../css/HomePage.css";

function PlaylistPage() {
  const navigate = useNavigate();
  const [playlist, setPlaylist] = useState([]);
  const [playlistName, setPlaylistName] = useState("");

  useEffect(() => {
    const checkToken = async () => {
      const token = localStorage.getItem("authToken");
      if (!token) return navigate("/");

      try {
        const response = await verifyToken(token);
        if (!response || !response.email) {
          localStorage.removeItem("authToken");
          navigate("/");
        }
      } catch {
        localStorage.removeItem("authToken");
        navigate("/");
      }
    };
    checkToken();
  }, [navigate]);

  const handleFetchPlaylist = async () => {
    const playlistString = await getPlaylist();
    if (!playlistString) return;

    setPlaylistName(playlistString);
    const result = await getSeriesByGenre(playlistString);
    setPlaylist(result || []);
  };

  return (
    <Background>
      <div style={{ padding: "2rem" }}>
        <BackHome />

        <h2>Playlist</h2>

        <div
          style={{
            marginBottom: "2rem",
            display: "flex",
            alignItems: "center",
            gap: "1rem",
          }}
        >
          <button
            onClick={handleFetchPlaylist}
            style={{
              padding: "0.8rem 1.5rem",
              fontSize: "1rem",
              backgroundColor: "#188ef6ff",
              color: "white",
              border: "none",
              borderRadius: "6px",
              cursor: "pointer",
              fontWeight: "bold",
              boxShadow: "0 2px 4px rgba(0,0,0,0.2)",
              transition: "all 0.3s ease",
            }}
            onMouseOver={(e) => {
              e.target.style.backgroundColor = "#45a049";
              e.target.style.transform = "translateY(-2px)";
            }}
            onMouseOut={(e) => {
              e.target.style.backgroundColor = "#188ef6ff";
              e.target.style.transform = "translateY(0)";
            }}
          >
            Avoir ma playlist
          </button>

          {playlistName && (
            <span style={{ fontSize: "1.1rem", fontWeight: "500" }}>
              genre actuel: {playlistName}
            </span>
          )}
        </div>

        <div className="series-table-container">
          <table style={{ width: "100%" }}>
            <thead>
              <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Genre</th>
                <th>Nb Épisodes</th>
                <th>Note</th>
              </tr>
            </thead>
            <tbody>
              {playlist.map((serie) => (
                <tr key={serie.id}>
                  <td>{serie.id}</td>
                  <td>{serie.title}</td>
                  <td>{serie.genre}</td>
                  <td>{serie.nbepisodes}</td>
                  <td>{serie.score}</td>
                </tr>
              ))}
            </tbody>
          </table>

          {playlist.length === 0 && <div>Aucune série trouvée.</div>}
        </div>
      </div>
    </Background>
  );
}

export default PlaylistPage;
