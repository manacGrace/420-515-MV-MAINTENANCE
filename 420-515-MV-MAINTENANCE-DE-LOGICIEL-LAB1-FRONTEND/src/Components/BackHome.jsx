import { Link } from "react-router-dom";

function BackHome() {
  return (
    <div style={{ textAlign: "center", marginTop: "2rem" }}>
      <Link to="/homepage">
        <button
          style={{
            fontSize: "1.2rem",
            padding: "12px 30px",
            border: "2px solid #0ff",
            background: "transparent",
            color: "#0ff",
            borderRadius: "8px",
            cursor: "pointer",
            transition: "all 0.3s ease",
            textTransform: "uppercase",
            letterSpacing: "2px",
            boxShadow: "0 0 8px #0ff",
          }}
          onMouseOver={(e) => {
            e.target.style.background = "rgba(0, 255, 255, 0.1)";
            e.target.style.boxShadow = "0 0 15px #0ff, 0 0 30px #0ff";
            e.target.style.color = "#fff";
          }}
          onMouseOut={(e) => {
            e.target.style.background = "transparent";
            e.target.style.boxShadow = "0 0 8px #0ff";
            e.target.style.color = "#0ff";
          }}
          onMouseDown={(e) => {
            e.target.style.transform = "scale(0.95)";
          }}
          onMouseUp={(e) => {
            e.target.style.transform = "scale(1)";
          }}
        >
          Retour à l'accueil
        </button>
      </Link>
    </div>
  );
}

export default BackHome;
