"use client";

import React, { useState, useEffect } from "react";
import GameBoard from "@/components/GameBoard";
import PlayerInfo from "@/components/PlayerInfo";
import MinionShop from "@/components/MinionShop";
import { getGameState, buyTile, buyMinion, endTurn } from "@/services/GameService";
import "./game.css";

const GamePage: React.FC = () => {
    const [gameState, setGameState] = useState<{
        turn: number;
        currentPlayer: string;
        playerData: Record<string, { money: number; minions: { name: string; color: string; cost: number }[] }>;
    }>({
        turn: 1,
        currentPlayer: "Player 1",
        playerData: {
            "Player 1": { money: 100, minions: [] },
            "Player 2": { money: 100, minions: [] },
        },
    });
    const { turn, currentPlayer, playerData } = gameState;
    const [showShop, setShowShop] = useState<boolean>(false);

    // เมื่อ component ถูก mount, เรียก API เพื่อดึงสถานะเกมล่าสุดจาก backend
    useEffect(() => {
        async function fetchGameState() {
            try {
                const state = await getGameState();
                setGameState(state);
            } catch (error) {
                console.error("Error fetching game state:", error);
            }
        }
        fetchGameState();
    }, []);

    const handleBuyTile = async () => {
        try {
            const updatedState = await buyTile(currentPlayer);
            setGameState(updatedState);
            alert(`${currentPlayer} bought a tile!`);
        } catch (error) {
            alert(`${currentPlayer} doesn't have enough money to buy a tile!`);
        }
    };

    const handleBuyMinion = async (name: string, color: string, cost: number) => {
        try {
            const updatedState = await buyMinion(currentPlayer, { name, color, cost });
            setGameState(updatedState);
            alert(`${currentPlayer} bought a ${name} minion!`);
            setShowShop(false);
        } catch (error) {
            alert(`${currentPlayer} doesn't have enough money to buy ${name}!`);
        }
    };

    const handleEndTurn = async () => {
        try {
            const updatedState = await endTurn();
            setGameState(updatedState);
        } catch (error) {
            console.error("Error ending turn:", error);
        }
    };

    return (
        <div className="game-container">
            <h1>🎮 KOMBAT Game</h1>
            <h2>Turn: {turn}</h2>
            <h3>Current Player: {currentPlayer}</h3>

            <PlayerInfo playerData={playerData} />

            <div className="top-buttons">
                <button onClick={handleBuyTile}>Buy Tile 🏠</button>
                <button onClick={() => setShowShop(true)}>Buy Minion 🤖</button>
            </div>

            <div className="game-content">
                <GameBoard minions={[]} />

                {showShop && (
                    <MinionShop
                        minionList={playerData[currentPlayer].minions}
                        onClose={() => setShowShop(false)}
                        onBuyMinion={handleBuyMinion}
                    />
                )}
            </div>

            <button className="end-turn" onClick={handleEndTurn}>
                End Turn 🔄
            </button>
        </div>
    );
};

export default GamePage;
