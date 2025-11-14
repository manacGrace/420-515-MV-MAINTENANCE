import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import PersonPage from "./pages/PersonPage";  
import SeriesPage from "./pages/SeriesPage";
import PlaylistPage from "./pages/PlaylistPage";
import CreateUserPage from "./pages/CreateUserPage";
import LoginPage from "./pages/LoginPage";
import TrendPage from "./pages/TrendPage";
import "./css/Background.css";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/creercompte" element={<CreateUserPage/>}/>
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/persons" element={<PersonPage />} />
        <Route path="/series" element={<SeriesPage />} />
        <Route path="/playlist" element={<PlaylistPage />} />
        <Route path="/trend" element={<TrendPage />} />
      </Routes>
    </Router>
  );
}

export default App;
