// services/gameService.ts

import axios from "axios";

// เปลี่ยน URL และพอร์ตตามที่ Backend ของคุณรันอยู่
// สมมติคุณระบุ server.port=8000 ใน application.properties
// ใช้พอร์ต 8000 ให้ตรงกับ Backend
const API_URL = "http://localhost:8001/game/api";

// ✅ แก้ให้เหลือแบบนี้ทั้งหมด:



export const getGameState = async () => {
    const response = await axios.get(`${API_URL}/game-state`);
    return response.data;
};

export const buyTile = async (playerId: string) => {
    const response = await axios.post(`${API_URL}/buy-tile`, { playerId });
    return response.data;
};

export const buyMinion = async (
    playerId: string,
    minion: { name: string; color: string; cost: number }
) => {
    const response = await axios.post(`${API_URL}/buy-minion`, {
        playerId,
        ...minion,
    });
    return response.data;
};

export const endTurn = async () => {
    const response = await axios.post(`${API_URL}/end-turn`);
    return response.data;
};


