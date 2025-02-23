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

    // **ข้อมูลมินเนี่ยนที่ผู้เล่นเลือก**
    const [playerData, setPlayerData] = useState<Record<string, {
        money: number;
        minions: { name: string; color: string; cost: number }[];
    }>>({
        "Player 1": { money: 100, minions: [] },
        "Player 2": { money: 100, minions: [] },
    });

    useEffect(() => {
        // โหลด Minion ที่ผู้เล่นเลือกไว้จาก LocalStorage
        const storedMinions = localStorage.getItem("selectedMinions");
        if (storedMinions) {
            setPlayerData({
                "Player 1": { money: 100, minions: JSON.parse(storedMinions) },
                "Player 2": { money: 100, minions: [] }, // ผู้เล่น 2 ยังไม่มี Minion
            });
        }
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
    const openMinionShop = () => {
        console.log("Minions Available:", playerData[currentPlayer].minions);
        setShowShop(true);
    };


    const closeMinionShop = () => {
        setShowShop(false);
    };

    const buyMinion = (name: string, color: string, cost: number) => {
        if (playerData[currentPlayer].money >= cost) {
            setPlayerData({
                ...playerData,
                [currentPlayer]: {
                    ...playerData[currentPlayer],
                    money: playerData[currentPlayer].money - cost,
                    minions: [...playerData[currentPlayer].minions, { name, color, cost }],
                },
            });
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
