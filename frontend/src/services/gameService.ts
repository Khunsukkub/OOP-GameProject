// services/gameService.ts

import axios from "axios";

// เปลี่ยน URL และพอร์ตตามที่ Backend ของคุณรันอยู่
// สมมติคุณระบุ server.port=8000 ใน application.properties
// ใช้พอร์ต 8000 ให้ตรงกับ Backend
const API_URL = "http://localhost:8001/game/api";

export const setGameMode = async (mode: string): Promise<string> => {
    const response = await axios.post(`${API_URL}/gameMode`, { mode });
    return response.data; // ✅ redirect URL อยู่ใน body แล้ว
};
export const createPlayer = async (playerName: string) => {
    const response = await axios.post(`${API_URL}/createPlayer`, playerName, {
        headers: { "Content-Type": "text/plain" }, // เพราะ backend รับ String ตรง ๆ
    });
    return response;
};

// ฟังก์ชันอื่น ๆ (getGameState, buyTile, buyMinion, endTurn)
export const getGameState = async () => {
    const response = await axios.get(`${API_URL}/api/game-state`);
    return response.data;
};

export const buyTile = async (playerId: string) => {
    const response = await axios.post(`${API_URL}/api/buy-tile`, { playerId });
    return response.data;
};

export const buyMinion = async (
    playerId: string,
    minion: { name: string; color: string; cost: number }
) => {
    const response = await axios.post(`${API_URL}/api/buy-minion`, {
        playerId,
        ...minion,
    });
    return response.data;
};

export const endTurn = async () => {
    const response = await axios.post(`${API_URL}/api/end-turn`);
    return response.data;
};
