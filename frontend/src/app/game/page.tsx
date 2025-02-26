"use client";

import React, { useState, useEffect } from "react";
import GameBoard from "@/components/GameBoard";
import PlayerInfo from "@/components/PlayerInfo";
import MinionShop from "@/components/MinionShop";
import "./game.css";

const GamePage: React.FC = () => {
    const [turn, setTurn] = useState<number>(1);
    const [currentPlayer, setCurrentPlayer] = useState<string>("Player 1");
    const [showShop, setShowShop] = useState<boolean>(false);

    // **ข้อมูลมินเนี่ยนของผู้เล่น**
    const [playerData, setPlayerData] = useState<Record<string, {
        money: number;
        minions: { name: string; color: string; cost: number }[];
    }>>({
        "Player 1": { money: 100, minions: [] },
        "Player 2": { money: 100, minions: [] },
    });

    useEffect(() => {
        // โหลด Minion ของผู้เล่น 1 และ 2 จาก localStorage
        const minionsP1 = localStorage.getItem("selectedMinions-1");
        const minionsP2 = localStorage.getItem("selectedMinions-2");

        setPlayerData({
            "Player 1": { money: 100, minions: minionsP1 ? JSON.parse(minionsP1) : [] },
            "Player 2": { money: 100, minions: minionsP2 ? JSON.parse(minionsP2) : [] },
        });
    }, []);

    const endTurn = () => {
        setTurn(turn + 1);
        setCurrentPlayer((prev) => (prev === "Player 1" ? "Player 2" : "Player 1"));
    };

    const buyTile = () => {
        if (playerData[currentPlayer].money >= 20) {
            setPlayerData((prev) => ({
                ...prev,
                [currentPlayer]: {
                    ...prev[currentPlayer],
                    money: prev[currentPlayer].money - 20,
                },
            }));
            alert(`${currentPlayer} bought a tile!`);
        } else {
            alert(`${currentPlayer} doesn't have enough money to buy a tile!`);
        }
    };

    const openMinionShop = () => {
        setShowShop(true);
    };

    const closeMinionShop = () => {
        setShowShop(false);
    };

    const buyMinion = (name: string, color: string, cost: number) => {
        if (playerData[currentPlayer].money >= cost) {
            setPlayerData((prev) => ({
                ...prev,
                [currentPlayer]: {
                    ...prev[currentPlayer],
                    money: prev[currentPlayer].money - cost,
                    minions: [...prev[currentPlayer].minions, { name, color, cost }],
                },
            }));
            alert(`${currentPlayer} bought a ${name} minion!`);
            setShowShop(false);
        } else {
            alert(`${currentPlayer} doesn't have enough money to buy ${name}!`);
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
                <button onClick={openMinionShop}>Buy Minion 🤖</button>
            </div>

            <div className="game-content">
                <GameBoard minions={[]} />

                {showShop && (
                    <MinionShop
                        minionList={playerData[currentPlayer].minions}
                        onClose={closeMinionShop}
                        onBuyMinion={buyMinion}
                    />
                )}
            </div>

            <button className="end-turn" onClick={endTurn}>
                End Turn 🔄
            </button>
        </div>
    );
};

export default GamePage;
