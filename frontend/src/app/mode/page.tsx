"use client";

import React from "react";
import { useRouter } from "next/navigation";
import "./mode.css";

const ModeSelection: React.FC = () => {
    const router = useRouter();

    const selectMode = (mode: string) => {
        // ✅ เลือกโหมดแล้วไปหน้ากรอกชื่อ พร้อมส่งโหมดไปด้วย
        router.push(`/start-game?mode=${mode}`);
    };

    return (
        <div className="container">
            <h1>Select Mode</h1>
            <div className="mode-buttons">
                <button onClick={() => selectMode("PVP")}>
                    🎮 PLAYER VS PLAYER 🎮
                </button>
                <button onClick={() => selectMode("PVB")}>
                    🎮 PLAYER VS BOT 🤖
                </button>
                <button onClick={() => selectMode("BVB")}>
                    🤖 BOT VS BOT 🤖
                </button>
            </div>
            <button className="back-button" onClick={() => router.push("/")}>
                Back ⬅️
            </button>
        </div>
    );
};

export default ModeSelection;
