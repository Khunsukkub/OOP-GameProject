"use client";

import React from "react";
import { useRouter } from "next/navigation";

import "@/app/game/game.css";
import "@/app/minion/minion.css";
import "@/app/mode/mode.css";
import "@/app/start-game/start-game.css";





const StartPage: React.FC = () => {
    const router = useRouter();

    const startGame = () => {
        router.push("/mode"); // เปลี่ยนไปหน้าที่เลือกโหมด (เปลี่ยนจาก model.html เป็น /mode)
    };

    const exitGame = () => {
        if (typeof window !== "undefined") {
            window.close(); // ปิดหน้าเว็บ
        }
    };

    return (
        <div className="container">
            <h1>K.O.M.B.A.T</h1>
            <button onClick={startGame}>Play 🎮</button>
            <button onClick={exitGame}>Exit 🎮</button>
        </div>
    );
};

export default StartPage;
