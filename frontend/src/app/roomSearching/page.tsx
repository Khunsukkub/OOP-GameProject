"use client";

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";
import { usePlayer } from "@/app/context/PlayerContext";
import { PlayerProvider } from "@/app/context/PlayerContext"; // import PlayerProvider

// สร้างช่องทางสื่อสารระหว่างแท็บ
const gameChannel = new BroadcastChannel("game_channel");

function RoomSearchingPage() {
    const router = useRouter();
    const searchParams = useSearchParams();
    const { players } = usePlayer();  // ใช้ players ที่ได้จาก context

    const playerName = searchParams.get("name") || "Anonymous";
    const mode = searchParams.get("mode") || "PVP";
    const [joined, setJoined] = useState(false);

    const currentPlayer = players[0]?.name === playerName ? 1 : 2;

    // ฟังข้อความจากแท็บอื่นๆ
    useEffect(() => {
        const handleMessage = (event: MessageEvent) => {
            if (event.data.action === "START_GAME") {
                const { player1, player2, mode } = event.data;
                router.push(`/minion?player=${currentPlayer}&player1=${player1}&player2=${player2}&mode=${mode}`);
            }
        };

        gameChannel.addEventListener("message", handleMessage);
        return () => gameChannel.removeEventListener("message", handleMessage);
    }, [router]);

    const checkPlayerCount = async () => {
        try {
            const res = await axios.get("http://localhost:8080/kombat/player-count");
            const count = res.data;
            console.log("🔁 Checking player count:", count);

            // ตรวจสอบว่า players มีข้อมูลครบ 2 คน
            if (count >= 2) {
                const player1 = players[0]?.name;
                const player2 = players[1]?.name;
                console.log(players)

                if (player1 && player2) {
                    console.log(`${player1} ${player2} have been joined`);

                    // ส่งข้อความไปยังแท็บอื่นๆ
                    gameChannel.postMessage({
                        action: "START_GAME",
                        player1: player1,
                        player2: player2,
                        mode,
                    });

                    // ปรับ URL ให้ตรงกับ player ที่เป็นเจ้าของแท็บ
                    router.push(`/minion?player=${currentPlayer}&player1=${player1}&player2=${player2}&mode=${mode}`);
                }
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
    }, [joined]);

    return (
        <div className="room-search-container">
            <h1>🔍 Searching for Room...</h1>
            <p>Please wait while we match you with another player.</p>
        </div>
    );
}

// ห่อ RoomSearchingPage ด้วย PlayerProvider
const RoomSearchingPageWithProvider: React.FC = () => {
    return (
        <PlayerProvider>
            <RoomSearchingPage />
        </PlayerProvider>
    );
};

export default RoomSearchingPageWithProvider;
