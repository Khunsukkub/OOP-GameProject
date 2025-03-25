"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";
import {createPlayer} from "@/services/playerService";
import StartGamePage from "@/app/start-game/page";

export default function RoomSearchingPage() {
    const router = useRouter();
    const searchParams = useSearchParams();

    const playerName = searchParams.get("name") || "Anonymous";
    const mode = searchParams.get("mode") || "PVP";

    const [joined, setJoined] = useState(false);

    const checkPlayerCount = async () => {
        try {
            const res = await axios.get("http://localhost:8080/kombat/player-count");
            const count = res.data;

            console.log("🔁 Checking player count:", count);
            if (count >= 2) {
                // ไปหน้า minion พร้อมส่ง player1 = คนแรก, player2 = คนล่าสุด
                const players = ;

                const player1 = players[0]?.name || "Player 1";
                const player2 = players[1]?.name || "Player 2";

                router.push(`/minion?player=2&player1=${player1}&player2=${player2}&mode=${mode}`);
            }
        } catch (err) {
            console.error("Error checking player count:", err);
        }
    };

    useEffect(() => {
        if (!joined) {
            setJoined(true);
        }

        const interval = setInterval(checkPlayerCount, 1000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div className="room-search-container">
            <h1>🔍 Searching for Room...</h1>
            <p>Please wait while we match you with another player.</p>
        </div>
    );
}
