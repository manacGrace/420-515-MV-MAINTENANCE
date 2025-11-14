import { Link } from "react-router-dom";
import { useState } from "react";
import Background from "../Components/Background";
import "../css/HomePage.css";
import reda from "../assets/reda.png";

function HomePage() {
  const [showImage, setShowImage] = useState(false);

  const toggleImage = () => {
    setShowImage(!showImage);
  };

  return (
    <Background>
      <h1 className="title">Main Page</h1>
      <p className="subtitle">Choisis une section :</p>

      <div className="menu">
        <Link to="/persons" className="btn neon">
          Voir les personnes
        </Link>
        <Link to="/series" className="btn neon">
          Voir les séries
        </Link>

        <Link to="/playlist" className="btn neon">
          Voir vos Recommendations
        </Link>

        <Link to="/trend" className="btn neon">
          Voir les Trends
        </Link>
      </div>

      <button className="floating-btn" onClick={toggleImage}></button>

      {showImage && (
        <div className="overlay" onClick={toggleImage}>
          <img src={reda} alt="Big preview" className="overlay-img" />
        </div>
      )}
    </Background>
  );
}

export default HomePage;
