"use client";

import React, { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";
import "./start-game.css";
import {roomSearching} from "@/services/gameService";

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
            await roomSearching(trimmedName);
            router.push(`/roomSearching?name=${trimmedName}&mode=${mode}`);
        } catch (error: any) {
            if (error.response?.status === 409) {
                alert("ชื่อซ้ำ หรือมีผู้เล่นครบแล้ว กรุณาเปลี่ยนชื่อหรือเริ่มเกมใหม่");
            } else {
                alert("Error creating player. Please try again.");
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
