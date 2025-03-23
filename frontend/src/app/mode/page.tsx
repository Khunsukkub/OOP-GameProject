"use client";

import React from "react";
import { useRouter } from "next/navigation";
import { setGameMode } from "@/services/gameService";
import "./mode.css";

const ModeSelection: React.FC = () => {
    const router = useRouter();

    const selectMode = async (mode: string) => {
        const gameMode =
            mode === "PVP" ? "PlayerVSPlayer" :
                mode === "PVB" ? "PlayerVSBot" :
                    mode === "BVB" ? "BotVSBot" : "PlayerVSPlayer";

        try {
            const redirectPath = await setGameMode(gameMode); // 🔁 รับ path เช่น "/createPlayer"
            router.push(redirectPath); // ✅ frontend redirect เอง
        } catch (error) {
            console.error("🚨 setGameMode failed:", error);
            router.push(`/start-game?mode=${mode}`);
        }
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
