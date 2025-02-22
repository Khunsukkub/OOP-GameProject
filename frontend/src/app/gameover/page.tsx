"use client";

import React from "react";
import { useRouter, useSearchParams } from "next/navigation";
import "./gameover.css"; // นำเข้า CSS

const GameOverPage = () => {
    const router = useRouter();
    const searchParams = useSearchParams();
    const winner = searchParams.get("winner") || "Player X"; // รับค่าผู้ชนะจาก URL

    return (
        <div className="gameover-container">
            <div className="trophy">🏆</div>
            <div className="winner">{winner} Wins!</div>
            <button className="restart-button" onClick={() => router.push("/")}>
                Play Again
            </button>
        </div>
    );
};

export default GameOverPage;
