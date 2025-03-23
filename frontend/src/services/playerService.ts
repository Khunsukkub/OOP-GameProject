// import axios from "axios";
//
// const API_URL = "http://localhost:3000/api/players"; // เปลี่ยนเป็น URL จริงของ Backend
//
// // ✅ สร้างผู้เล่นใหม่
// export const createPlayer = async (playerName: string) => {
//     try {
//         const response = await axios.post(`${API_URL}`, { name: playerName });
//         return response.data; // คืนค่า Player ID ที่สร้างใหม่
//     } catch (error) {
//         console.error("Error creating player:", error);
//         throw error;
//     }
// };
//
// // ✅ ดึงข้อมูลผู้เล่น
// export const getPlayer = async (playerId: string) => {
//     try {
//         const response = await axios.get(`${API_URL}/${playerId}`);
//         return response.data; // คืนค่าข้อมูลผู้เล่น
//     } catch (error) {
//         console.error("Error fetching player:", error);
//         throw error;
//     }
// };
//
// // ✅ อัปเดตข้อมูลผู้เล่น (เปลี่ยนชื่อ, อัปเดตเงิน ฯลฯ)
// export const updatePlayer = async (playerId: string, updatedData: any) => {
//     try {
//         const response = await axios.put(`${API_URL}/${playerId}`, updatedData);
//         return response.data; // คืนค่าผลลัพธ์ที่อัปเดตแล้ว
//     } catch (error) {
//         console.error("Error updating player:", error);
//         throw error;
//     }
// };
//
// // ✅ ลบผู้เล่น
// export const deletePlayer = async (playerId: string) => {
//     try {
//         const response = await axios.delete(`${API_URL}/${playerId}`);
//         return response.data; // คืนค่าผลลัพธ์หลังลบสำเร็จ
//     } catch (error) {
//         console.error("Error deleting player:", error);
//         throw error;
//     }
// };
//
// // createPlayer(name) → สร้างผู้เล่นใหม่ (POST)
// // getPlayer(playerId) → ดึงข้อมูลผู้เล่นจาก Backend (GET)
// // updatePlayer(playerId, data) → อัปเดตข้อมูลผู้เล่น (PUT)
// // deletePlayer(playerId) → ลบผู้เล่น (DELETE)
