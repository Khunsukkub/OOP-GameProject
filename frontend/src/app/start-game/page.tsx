"use client";

import React, { useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";

const WaitingForPlayer: React.FC = () => {
    const router = useRouter();
    const searchParams = useSearchParams();

    const player1 = searchParams.get("player1") || "Player 1";
    const player2 = searchParams.get("player2") || "Player 2";
    const mode = searchParams.get("mode") || "PVP";

    useEffect(() => {
        const interval = setInterval(async () => {
            try {
                const response = await axios.get("http://localhost:8001/game/api/checkPlayers");
                const { ready } = response.data;

                if (ready) {
                    router.push(`/game?player1=${player1}&player2=${player2}&mode=${mode}`);
                }
            } catch (error) {
                console.error("❌ Failed to check players:", error);
            }
        }, 3000); // เช็คทุก 3 วินาที

        return () => clearInterval(interval); // clear เมื่อ component ถูก unmount
    }, []);

    return (
        <div style={{ textAlign: "center", marginTop: "100px" }}>
            <h1>🕒 Waiting for another player...</h1>
            <p>Player 1: {player1}</p>
            <p>Waiting for Player 2 to submit minions...</p>
        </div>
    );
};

export default WaitingForPlayer;
