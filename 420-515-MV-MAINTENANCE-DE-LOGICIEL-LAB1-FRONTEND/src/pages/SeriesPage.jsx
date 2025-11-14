import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllSeries, rateSeries, verifyToken } from "../utils/apiService";
import BackHome from "../Components/BackHome";
import Background from "../Components/Background";
import "../css/HomePage.css";

function SeriesPage() {
  const navigate = useNavigate();
  const [series, setSeries] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedSeriesId, setSelectedSeriesId] = useState(null);
  const [rating, setRating] = useState(1);

  useEffect(() => {
    const checkTokenAndLoad = async () => {
      const token = localStorage.getItem("authToken");
      console.log("Token sent to backend:", token);
      if (!token) return navigate("/");

      try {
        const response = await verifyToken(token);
        if (!response || !response.email) {
          localStorage.removeItem("authToken");
          return navigate("/");
        }

        const seriesList = await getAllSeries();
        setSeries(seriesList);
      } catch {
        localStorage.removeItem("authToken");
        navigate("/");
      }
    };

    checkTokenAndLoad();
  }, [navigate]);

  const handleRateSubmit = async () => {
    if (!selectedSeriesId || rating < 1 || rating > 5) return;

    const email = localStorage.getItem("userEmail");
    if (!email) return navigate("/");

    const result = await rateSeries(selectedSeriesId, rating, email);

    if (result?.message) {
      console.log(result.message);
    } else if (result?.error) {
      console.error(result.error);
    }

    setShowForm(false);
    setRating(1);
  };

  return (
    <Background>
      <div>
        <input
          type="text"
          placeholder="Ex: drama"
          style={{
            padding: "0.5rem",
            width: "300px",
            fontSize: "1rem",
            marginRight: "10px",
          }}
        />
        <input
          type="number"
          placeholder="Ex: 2"
          style={{ padding: "0.5rem", width: "300px", fontSize: "1rem" }}
        />

        <BackHome />

        <h2>Liste des séries</h2>
        <div className="series-table-container">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Genre</th>
                <th>Nb Épisodes</th>
                <th>Note</th>
                <th>Rate</th>
              </tr>
            </thead>
            <tbody>
              {series.map((s) => (
                <tr key={s.id}>
                  <td>{s.id}</td>
                  <td>{s.title}</td>
                  <td>{s.genre}</td>
                  <td>{s.nbepisodes}</td>
                  <td>{s.score}</td>
                  <td>
                    <button
                      className="btn neon"
                      onClick={() => {
                        setSelectedSeriesId(s.id);
                        setShowForm(true);
                      }}
                    >
                      +
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {showForm && (
          <div
            style={{
              position: "fixed",
              top: 0,
              left: 0,
              width: "100%",
              height: "100%",
              background: "rgba(0,0,0,0.5)",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              zIndex: 1000,
            }}
          >
            <div
              style={{
                background: "#fff",
                padding: "20px",
                borderRadius: "10px",
                minWidth: "300px",
                textAlign: "center",
                boxShadow: "0 4px 10px rgba(0,0,0,0.3)",
              }}
            >
              <h3>Noter la série (1-5)</h3>
              <input
                type="number"
                min="1"
                max="5"
                value={rating}
                onChange={(e) => setRating(parseInt(e.target.value))}
                style={{
                  padding: "0.5rem",
                  width: "60px",
                  marginRight: "10px",
                  textAlign: "center",
                }}
              />
              <div style={{ marginTop: "15px" }}>
                <button className="btn neon" onClick={handleRateSubmit}>
                  Soumettre
                </button>
                <button
                  className="btn neon"
                  onClick={() => setShowForm(false)}
                  style={{ marginLeft: "10px" }}
                >
                  Annuler
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </Background>
  );
}

export default SeriesPage;
