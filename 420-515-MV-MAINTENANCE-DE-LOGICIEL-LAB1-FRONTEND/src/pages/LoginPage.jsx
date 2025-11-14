import { useState } from "react";
import { loginUser } from "../utils/apiService";
import { useNavigate, Link } from "react-router-dom";
import Background from "../Components/Background";
import "../css/HomePage.css";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    const result = await loginUser(email, password);
    if (result?.token) {
      localStorage.setItem("userEmail", email);
      localStorage.setItem("authToken", result.token);
      setMessage("Login successful!");
      navigate("/homepage");
      console.log("naviger ");
    } else if (result?.error) {
      setMessage(result.error);
    } else {
      setMessage("Une erreur est survenue");
    }
  };

  return (
    <Background>
      <h1 className="title">Se connecter</h1>
      <p className="subtitle">Entrez vos informations :</p>

      <div className="menu">
        <input
          type="email"
          placeholder="Email"
          className="btn neon"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Mot de passe"
          className="btn neon"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button className="btn neon" onClick={handleLogin}>
          Se connecter
        </button>
        <Link
          to="/creercompte"
          className="btn neon"
          style={{ fontSize: "0.8em", padding: "8px 16px" }}
        >
          Créer un compte
        </Link>
      </div>

      {message && <p className="subtitle">{message}</p>}
    </Background>
  );
}

export default LoginPage;
