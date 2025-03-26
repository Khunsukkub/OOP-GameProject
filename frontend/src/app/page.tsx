"use client";

import React from "react";
import { useRouter } from "next/navigation";
import {usePlayer} from "@/app/context/PlayerContext";
import {PlayerProvider} from "@/app/context/PlayerContext";

// ถ้าต้องการใช้ CSS เหล่านี้จริง ให้คงไว้
// หากไม่จำเป็น ให้ลบออกเพื่อลดการ import ไม่จำเป็น
import "@/app/game/game.css";
import "@/app/minion/minion.css";
import "@/app/mode/mode.css";
import "@/app/start-game/start-game.css";

const StartPage: React.FC = () => {
    const router = useRouter();
    const { resetPlayers } = usePlayer();

    const handleStartGame = () => {
        // นำทางไปยังหน้า /mode
        router.push("/mode");
    };

    const handleExitGame = () => {
        // ถ้าต้องการให้มีการยืนยันก่อนออก
        if (typeof window !== "undefined") {
            const confirmExit = confirm("Are you sure you want to exit the game?");
            if (confirmExit) {
                // เบราว์เซอร์บางตัวอาจบล็อก window.close() หากแท็บนี้เปิดโดยผู้ใช้
                window.close();
            }
        }
    };

    return (
        <div className="container">
            <h1>K.O.M.B.A.T</h1>
            <button onClick={handleStartGame}>Play 🎮</button>
            <button onClick={handleExitGame}>Exit 🎮</button>
        </div>
    );
};

// ห่อ RoomSearchingPage ด้วย PlayerProvider
const StartPagePageWithProvider: React.FC = () => {
    return (
        <PlayerProvider>
            <StartPage />
        </PlayerProvider>
    );
};

export default StartPagePageWithProvider;
