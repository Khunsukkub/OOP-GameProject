import axios from "axios";

const API_URL = "http://localhost:8001/game/api";

export const createPlayer = async (playerName: string) => {
    return await axios.post(`${API_URL}/createPlayer`, playerName, {
        headers: { "Content-Type": "application/json" }
    });
};

export const searchRoom = async (roomCode: string) => {
    return await axios.post(`${API_URL}/roomSearching`, roomCode, {
        headers: { "Content-Type": "application/json" }
    });
};
