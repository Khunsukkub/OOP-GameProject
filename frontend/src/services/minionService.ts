// import axios from "axios";
//
// const API_URL = "http://localhost:5000/api/minions"; // ✅ URL ของ Backend
//
// // ✅ ดึงข้อมูล Minion ทั้งหมดของผู้เล่น
// export const getMinions = async (playerId: string) => {
//     try {
//         const response = await axios.get(`${API_URL}?playerId=${playerId}`);
//         return response.data;
//     } catch (error) {
//         console.error("Error fetching minions:", error);
//         throw error;
//     }
// };
//
// // ✅ ส่งข้อมูล Minion ไปที่ Backend
// export const createMinions = async (playerId: string, minions: any[]) => {
//     try {
//         const response = await axios.post(API_URL, { playerId, minions });
//         return response.data;
//     } catch (error) {
//         console.error("Error creating minions:", error);
//         throw error;
//     }
// };
//
// // ✅ อัปเดตข้อมูล Minion
// export const updateMinion = async (minionId: string, updatedData: any) => {
//     try {
//         const response = await axios.put(`${API_URL}/${minionId}`, updatedData);
//         return response.data;
//     } catch (error) {
//         console.error("Error updating minion:", error);
//         throw error;
//     }
// };
