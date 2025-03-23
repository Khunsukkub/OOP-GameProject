import axios from "axios";
const API_URL = "http://localhost:8080/game"; // ปรับ URL ตามที่ backend ของคุณรันอยู่

export const getGameState = async () => {
    const response = await axios.get(`${API_URL}/api/game-state`);
    return response.data;
};

export const buyTile = async (playerId: string) => {
    const response = await axios.post(`${API_URL}/api/buy-tile`, { playerId });
    return response.data;
};

export const buyMinion = async (playerId: string, minion: { name: string; color: string; cost: number }) => {
    const response = await axios.post(`${API_URL}/api/buy-minion`, { playerId, ...minion });
    return response.data;
};

export const endTurn = async () => {
    const response = await axios.post(`${API_URL}/api/end-turn`);
    return response.data;
};
