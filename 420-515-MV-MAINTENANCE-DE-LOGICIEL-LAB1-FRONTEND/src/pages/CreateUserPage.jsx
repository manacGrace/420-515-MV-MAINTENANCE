import { useState } from "react";
import { registerUser } from "../utils/apiService";
import { useNavigate } from "react-router-dom";
import Background from "../Components/Background";
import "../css/HomePage.css";

function CreateUserPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleRegister = async () => {
    const result = await registerUser(email, password);
    if (result?.message) {
      setMessage(result.message);
      navigate("/");
    } else if (result?.error) {
      setMessage(result.error);
    } else {
      setMessage("Une erreur est survenue");
    }
  };

  return (
    <Background>
      <h1 className="title">Créer un compte</h1>
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
        <button className="btn neon" onClick={handleRegister}>
          S'inscrire
        </button>
      </div>

      {message && <p className="subtitle">{message}</p>}
    </Background>
  );
}

export default CreateUserPage;
