import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  getAllSeries,
  getTop10,
  getTopQuality,
  verifyToken,
} from "../utils/apiService";
import BackHome from "../Components/BackHome";
import Background from "../Components/Background";
import "../css/HomePage.css";

function TrendPage() {
  const navigate = useNavigate();
  const [series, setSeries] = useState([]);

  useEffect(() => {
    const checkTokenAndLoad = async () => {
      const token = localStorage.getItem("authToken");
      if (!token) return navigate("/");

      try {
        const response = await verifyToken(token);
        if (!response || !response.email) {
          localStorage.removeItem("authToken");
          return navigate("/");
        }

        const allSeries = await getAllSeries();
        setSeries(allSeries);
      } catch {
        localStorage.removeItem("authToken");
        navigate("/");
      }
    };

    checkTokenAndLoad();
  }, [navigate]);

  const handleTop10Viewed = async () => {
    setSeries(await getTop10());
  };

  const handleTop10Quality = async () => {
    setSeries(await getTopQuality());
  };

  return (
    <Background>
      <div>
        <button className="btn neon" onClick={handleTop10Viewed}>
          Top 10 des plus vues
        </button>
        <button className="btn neon" onClick={handleTop10Quality}>
          Top 10 des mieux notées
        </button>

        <BackHome />

        <h2>Trends</h2>
        <div className="series-table-container">
          <table>
            <thead>
              <tr>
                <th>ID</th>
                <th>Titre</th>
                <th>Genre</th>
                <th>Nb Épisodes</th>
                <th>Note</th>
                <th>Favoris</th>
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
                      onClick={() => console.log("pas encore implementé")}
                    >
                      +
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </Background>
  );
}

export default TrendPage;
