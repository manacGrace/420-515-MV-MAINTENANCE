import axios from "axios";

const API_URL = "http://localhost:8888/api";

export const getAllNames = async (lastname) => {
  try {
    const result = await axios.get(`${API_URL}/persons/getPersons/${lastname}`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération des personnes:", err);
  }
  return null;
};

export const getAllSeries = async () => {
  try {
    const result = await axios.get(`${API_URL}/series/all`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération des séries:", err);
  }
  return null;
};

export const getTop10 = async () => {
  try {
    const result = await axios.get(`${API_URL}/series/top10`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération du top 10:", err);
  }
  return null;
};

export const getTopQuality = async () => {
  try {
    const result = await axios.get(`${API_URL}/series/trending`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération du top trending:", err);
  }
  return null;
};

export const getSeriesByGenre = async (genre) => {
  try {
    const result = await axios.get(`${API_URL}/series/genre/${genre}`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération des séries par genre:", err);
  }
  return null;
};

export const getSeriesByScore = async (score) => {
  try {
    const result = await axios.get(`${API_URL}/series/score/${score}`);
    return Array.isArray(result.data) ? result.data : [];
  } catch (err) {
    console.error("Erreur lors de la récupération des séries par score:", err);
  }
  return null;
};

export const rateSeries = async (seriesId, rating, email) => {
  try {
    const result = await axios.post(`${API_URL}/series/rate`, {
      seriesId,
      rating,
      email,
    });
    return result.data;
  } catch (err) {
    console.error("Erreur lors de l'ajout du rating:", err);
  }
  return null;
};

export const getPlaylist = async () => {
  try {
    const result = await axios.get(`${API_URL}/playlist/add`);
    return result.data;
  } catch (err) {
    console.error("Erreur lors de la récupération de la playlist:", err);
  }
  return null;
};

export const registerUser = async (email, password) => {
  try {
    const result = await axios.post(`${API_URL}/auth/register`, {
      email,
      password,
    });
    return result.data;
  } catch (err) {
    console.error("Erreur lors de l'inscription:", err);
  }
  return null;
};

export const loginUser = async (email, password) => {
  try {
    const result = await axios.post(`${API_URL}/auth/login`, null, {
      params: { email, password },
    });
    return result.data;
  } catch (err) {
    console.error("Erreur lors de la connexion:", err);
  }
  return null;
};

export const verifyToken = async (token) => {
  try {
    const result = await axios.post(`${API_URL}/auth/verify`, null, {
      headers: { Authorization: `Bearer ${token}` },
    });
    return result.data;
  } catch (err) {
    console.error("Erreur lors de la vérification du token:", err);
  }
  return null;
};
