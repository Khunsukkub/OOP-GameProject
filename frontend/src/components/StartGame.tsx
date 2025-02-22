"use client";

import React, { useState } from "react";

const StartGame: React.FC = () => {
    const [player1, setPlayer1] = useState<string>("");
    const [player2, setPlayer2] = useState<string>("");
    const [message, setMessage] = useState<string | null>(null);

    const startGame = async () => {
        const p1 = player1.trim() || "Player 1";
        const p2 = player2.trim() || "Player 2";

        try {
            const response = await fetch("http://localhost:8080/api/start", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ player1: p1, player2: p2 }),
            });

            const data = await response.json();
            setMessage(`${data.firstPlayer} will start first!`);
        } catch (error) {
            console.error("Error:", error);
            setMessage("Failed to start the game!");
        }
    };

    return (
        <div className="start-game-container">
            <h2>Enter Player Names</h2>
            <input
                type="text"
                placeholder="Player 1 Name"
                value={player1}
                onChange={(e) => setPlayer1(e.target.value)}
            />
            <input
                type="text"
                placeholder="Player 2 Name"
                value={player2}
                onChange={(e) => setPlayer2(e.target.value)}
            />
            <button onClick={startGame}>Start Game</button>
            {message && <p>{message}</p>}
        </div>
    );
};

export default StartGame;
