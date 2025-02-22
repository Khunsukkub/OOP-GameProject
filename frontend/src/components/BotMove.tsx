"use client";

import React, { useState, useEffect } from "react";

interface BotMoveProps {
    mode: "PVB" | "BVB" | "PVP"; // รับค่าโหมด
}

const BotMove: React.FC<BotMoveProps> = ({ mode }) => {
    const [botAction, setBotAction] = useState<string | null>(null);

    const botMove = (): string => {
        const actions = ["Attack", "Defend", "Move"];
        return actions[Math.floor(Math.random() * actions.length)];
    };

    useEffect(() => {
        if (mode === "PVB" || mode === "BVB") {
            const action = botMove();
            setBotAction(action);
        }
    }, [mode]);

    return (
        <div className="bot-container">
            {botAction && <p>🤖 Bot chose to: <strong>{botAction}</strong></p>}
        </div>
    );
};

export default BotMove;
