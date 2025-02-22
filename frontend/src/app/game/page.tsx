"use client";

import React, { useState, useEffect } from "react";
import GameBoard from "@/components/GameBoard";
import PlayerInfo from "@/components/PlayerInfo";
import "./game.css";

const GamePage: React.FC = () => {
    const [turn, setTurn] = useState<number>(1);
    const [currentPlayer, setCurrentPlayer] = useState<string>("Player 1");
    const [playerData, setPlayerData] = useState<Record<string, { money: number; minions: string[] }>>({
        "Player 1": { money: 100, minions: [] },
        "Player 2": { money: 100, minions: [] },
    });

    useEffect(() => {
        setCurrentPlayer("Player 1");
    }, []);

    const endTurn = () => {
        setTurn(turn + 1);
        setCurrentPlayer(currentPlayer === "Player 1" ? "Player 2" : "Player 1");
    };

    const buyTile = () => {
        if (playerData[currentPlayer].money >= 20) {
            setPlayerData({
                ...playerData,
                [currentPlayer]: {
                    ...playerData[currentPlayer],
                    money: playerData[currentPlayer].money - 20,
                },
            });
            alert(`${currentPlayer} bought a tile!`);
        } else {
            alert(`${currentPlayer} doesn't have enough money to buy a tile!`);
        }
    };

    const buyMinion = () => {
        if (playerData[currentPlayer].money >= 30) {
            setPlayerData({
                ...playerData,
                [currentPlayer]: {
                    ...playerData[currentPlayer],
                    money: playerData[currentPlayer].money - 30,
                    minions: [...playerData[currentPlayer].minions, `Minion ${playerData[currentPlayer].minions.length + 1}`],
                },
            });
            alert(`${currentPlayer} bought a Minion!`);
        } else {
            alert(`${currentPlayer} doesn't have enough money to buy a Minion!`);
        }
    };

    return (
        <div className="game-container">
            <h1>🎮 KOMBAT Game</h1>
            <h2>Turn: {turn}</h2>
            <h3>Current Player: {currentPlayer}</h3>

            <PlayerInfo playerData={playerData} />

            <div className="top-buttons">
                <button onClick={buyTile}>Buy Tile 🏠</button>
                <button onClick={buyMinion}>Buy Minion 🤖</button>
            </div>

            <GameBoard />

            <button className="end-turn" onClick={endTurn}>
                End Turn 🔄
            </button>
        </div>
    );
};

export default GamePage;
