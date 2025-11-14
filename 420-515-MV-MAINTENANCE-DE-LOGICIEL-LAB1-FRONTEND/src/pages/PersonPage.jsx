import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getAllNames, verifyToken } from "../utils/apiService";
import BackHome from "../Components/BackHome";
import Background from "../Components/Background";
import "../css/HomePage.css";

function PersonPage() {
  const navigate = useNavigate();
  const [rows, setRows] = useState([]);

  useEffect(() => {
    const checkToken = async () => {
      const token = localStorage.getItem("authToken");
      if (!token) return navigate("/");

      try {
        const response = await verifyToken(token);
        if (!response || !response.email) {
          localStorage.removeItem("authToken");
          return navigate("/");
        }
      } catch {
        localStorage.removeItem("authToken");
        navigate("/");
      }
    };

    checkToken();
  }, [navigate]);

  const handleSearch = async (e) => {
    const input = e.target.value;
    setRows(await getAllNames(input));
  };

  return (
    <Background>
      <div style={{ padding: "2rem" }}>
        <BackHome />
        <h2>Liste des personnes</h2>

        <input
          type="text"
          onChange={handleSearch}
          placeholder="Ex: PHI"
          style={{ padding: "0.5rem", width: "300px", fontSize: "1rem" }}
        />

        <table border="1" cellPadding="5" style={{ marginTop: "1rem" }}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nom de Famille</th>
              <th>Âge</th>
              <th>Genre</th>
            </tr>
          </thead>
          <tbody>
            {rows.map((person) => (
              <tr key={person.personid}>
                <td>{person.personid}</td>
                <td>{person.lastname}</td>
                <td>{person.age}</td>
                <td>{person.genre}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </Background>
  );
}

export default PersonPage;
