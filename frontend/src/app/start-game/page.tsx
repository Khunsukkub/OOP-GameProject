"use client";

import React, { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";
import "./start-game.css";

const StartGamePage: React.FC = () => {
    const router = useRouter();
    const searchParams = useSearchParams();

    const mode = searchParams.get("mode") || "PVP";
    const [playerName, setPlayerName] = useState<string>("");

    const handleStart = async () => {
        const trimmedName = playerName.trim();
        if (!trimmedName) {
            alert("กรุณากรอกชื่อผู้เล่น");
            return;
        }

        try {
            // ส่งข้อมูลชื่อผู้เล่นไปยัง backend
            const response = await axios.post("http://localhost:8080/kombat/createPlayer", {
                playerName: trimmedName,
            });


            // รับข้อมูล player จาก response
            const createdPlayer = response.data.player;
            console.log("Player created:", createdPlayer);

            // ถ้า response มี redirectUrl หมายความว่าผู้เล่นครบตามจำนวนแล้ว
            if (response.data.redirectUrl) {
                router.push(`${response.data.redirectUrl}?name=${trimmedName}&mode=${mode}`);
            } else {
                // ถ้ายังไม่ครบให้ไปที่หน้า roomSearching เพื่อรอคู่
                router.push(`/roomSearching?name=${trimmedName}&mode=${mode}`);
            }
        } catch (error: any) {
            if (error.response?.status === 409) {
                alert("ชื่อซ้ำ หรือมีผู้เล่นครบแล้ว กรุณาเปลี่ยนชื่อหรือเริ่มเกมใหม่");
            } else {
                alert("เกิดข้อผิดพลาดในการสร้างผู้เล่น กรุณาลองใหม่อีกครั้ง");
                console.error(error);
            }
        }
    };


    return (
        <div className="container">
            <h1>Enter Player Name ({mode})</h1>
            <input
                type="text"
                placeholder="Your Name"
                value={playerName}
                onChange={(e) => setPlayerName(e.target.value)}
            />
            <button onClick={handleStart}>Start Game 🎮</button>
            <button onClick={() => router.push("/mode")}>Back ⬅️</button>
        </div>
    );
};

export default StartGamePage;
