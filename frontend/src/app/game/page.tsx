"use client";

import React, { useState, useEffect } from "react";
import GameBoard from "@/components/GameBoard";
import PlayerInfo from "@/components/PlayerInfo";
import MinionShop from "@/components/MinionShop";
import TileShop from "@/components/TileShop";
import { getGameState, buyTile, buyMinion, endTurn } from "@/services/gameService";
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
    const [showTileShop, setShowTileShop] = useState<boolean>(false);

    const [availableTiles, setAvailableTiles] = useState<
        { id: number; price: number; q: number; r: number }[]
    >([
        { id: 1, price: 30, q: 1, r: 1 },
        { id: 2, price: 50, q: 2, r: 2 },
        { id: 3, price: 80, q: 3, r: 3 },
    ]);

    useEffect(() => {
        async function fetchGameState() {
            try {
                const state = await getGameState();
                console.log("🎯 Game state:", state);
                setGameState(state);
            } catch (error) {
                console.error("Error fetching game state:", error);
            }
        }
        fetchGameState();
    }, []);

    const handleBuyTile = () => {
        setShowTileShop(true);
    };

    const handleConfirmBuyTile = (tileId: number) => {
        alert(`${currentPlayer} bought tile ID: ${tileId}`);
        setShowTileShop(false);
        // คุณสามารถเรียก buyTile() จาก backend ได้ที่นี่ถ้ามีระบบรองรับ
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
                <GameBoard
                    minions={Object.entries(playerData).flatMap(([_, data]) =>
                        data.minions.map((m, index) => ({
                            id: index,
                            name: m.name,
                            color: m.color,
                            position: { q: index % 8, r: Math.floor(index / 8) },
                        }))
                    )}
                />

                {showShop && (
                    <MinionShop
                        minionList={playerData?.[currentPlayer]?.minions || []}
                        onClose={() => setShowShop(false)}
                        onBuyMinion={handleBuyMinion}
                    />
                )}

                {showTileShop && (
                    <TileShop
                        tiles={availableTiles}
                        onBuyTile={handleConfirmBuyTile}
                        onClose={() => setShowTileShop(false)}
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
