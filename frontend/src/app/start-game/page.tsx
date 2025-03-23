"use client";

import React, { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import "./start-game.css";
import {createPlayer} from "@/services/gameService"; // นำเข้า CSS

const StartGamePage: React.FC = () => {
    const router = useRouter();
    const searchParams = useSearchParams();

    const [player1, setPlayer1] = useState<string>("");
    const [player2, setPlayer2] = useState<string>("");
    const [mode, setMode] = useState<string>("PVP");

    useEffect(() => {
        const gameMode = searchParams.get("mode") || "PVP";
        setMode(gameMode);
    }, [searchParams]);

    const startGame = async () => {
        const p1 = player1.trim() || "Player 1";
        const p2 = player2.trim() || "Player 2";

        try {
            // ส่งชื่อไป backend สร้าง Player
            await createPlayer(p1);
            await createPlayer(p2);

            // แล้วค่อยไปหน้าเลือก Minion
            router.push(`/minion?player=1&player1=${p1}&player2=${p2}&mode=${mode}`);
        } catch (error) {
            console.error("❌ Error creating players:", error);
            alert("Error creating players. Please try again.");
        }
    };

    return (
        <div className="container">
            <h1>Enter Player Names ({mode})</h1>
            <input
                type="text"
                placeholder="PLAYER 1 Name"
                value={player1}
                onChange={(e) => setPlayer1(e.target.value)}
            />
            <input
                type="text"
                placeholder="PLAYER 2 Name"
                value={player2}
                onChange={(e) => setPlayer2(e.target.value)}
            />
            <button onClick={startGame}>Start Game 🎮</button>
            <button onClick={() => router.push("/mode")}>Back ⬅️</button>


        </div>
    );
};

export default StartGamePage;
