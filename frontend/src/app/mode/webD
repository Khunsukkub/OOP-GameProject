"use client";

import React from "react";
import { useRouter } from "next/navigation";
import "./mode.css"; // นำเข้า CSS

const ModeSelection: React.FC = () => {
    const router = useRouter();

    const selectMode = (mode: string) => {
        router.push(`/start-game?mode=${mode}`); // เปลี่ยนไปหน้าเริ่มเกมพร้อมส่งโหมด
    };

    return (
        <div className="container">
            <h1>Select Mode</h1>

            <div className="mode-buttons">
                <button onClick={() => selectMode("PVP")}>🎮 PLAYER VS PLAYER 🎮</button>
                <button onClick={() => selectMode("PVB")}>🎮 PLAYER VS BOT 🤖</button>
                <button onClick={() => selectMode("BVB")}>🤖 BOT VS BOT 🤖</button>
            </div>

            <button className="back-button" onClick={() => router.push("/")}>Back ⬅️</button>
        </div>
    );
};

export default ModeSelection;
