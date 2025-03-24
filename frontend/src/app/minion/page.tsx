"use client";

import React, { useState, useEffect } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import axios from "axios";
import "./minion.css";

const MinionPage: React.FC = () => {
    const router = useRouter();
    const searchParams = useSearchParams();

    const player = searchParams.get("player") || "1"; // ตรวจสอบว่าเป็น Player 1 หรือ 2
    const player1Name = searchParams.get("player1") || "Player 1";
    const player2Name = searchParams.get("player2") || "Player 2";
    const mode = searchParams.get("mode") || "PVP";

    // State สำหรับเลือก Minion
    const [selectedMinions, setSelectedMinions] = useState<number>(1);
    const [minionNames, setMinionNames] = useState<Record<number, string>>({});
    const [minionDefense, setMinionDefense] = useState<Record<number, number>>({});
    const [minionCodes, setMinionCodes] = useState<Record<number, string>>({});
    const [selectedMinion, setSelectedMinion] = useState<number | null>(null);

    // สีของ Minion
    const minionColors = ["#FF5733", "#33FF57", "#3357FF", "#F4D03F", "#9B59B6"];

    // เมื่อ Player เปลี่ยน รีเซ็ตค่า Minion (ป้องกันข้อมูลค้างจาก Player ก่อนหน้า)
    useEffect(() => {
        setMinionNames({});
        setMinionDefense({});
        setMinionCodes({});
        setSelectedMinions(1);
    }, [player]);

    const handleMinionSelection = (num: number) => {
        setSelectedMinions(num);
    };

    const handleNameChange = (minionNumber: number, value: string) => {
        setMinionNames({ ...minionNames, [minionNumber]: value });
    };

    const handleDefenseChange = (minionNumber: number, value: string) => {
        const defValue = parseInt(value);
        if (defValue >= 1 && defValue <= 20) {
            setMinionDefense({ ...minionDefense, [minionNumber]: defValue });
        } else {
            alert("Defense value must be between 1-20");
        }
    };

    const handleMinionClick = (minionNumber: number) => {
        setSelectedMinion(minionNumber);
    };

    const handleCodeChange = (value: string) => {
        if (selectedMinion !== null) {
            setMinionCodes({ ...minionCodes, [selectedMinion]: value });
        }
    };

    const submitAll = async () => {
        // ตรวจสอบข้อมูลของแต่ละ minion
        for (let i = 1; i <= selectedMinions; i++) {
            if (!minionNames[i] || !minionDefense[i]) {
                alert(`Minion ${i} needs a name and defense value.`);
                return;
            }
        }

        const minions = Object.keys(minionNames).map((key, index) => ({
            name: minionNames[parseInt(key)],
            color: minionColors[index % minionColors.length],
            defense: minionDefense[parseInt(key)],
            code: minionCodes[parseInt(key)] || "",
            cost: 10 + index * 5,
        }));

        try {
            // ✅ ส่งข้อมูลไปยัง backend
            const res = await axios.post("http://localhost:8001/game/api/player/minions", {
                player: player,
                minions: minions,
            });

            // ✅ ใช้ URL ที่ backend ส่งกลับมา
            const redirectUrl = res.data;

            if (redirectUrl === "/game") {
                router.push(`/game?player1=${player1Name}&player2=${player2Name}&mode=${mode}`);
            } else if (redirectUrl === "/waitingForPlayer") {
                // 👉 Player 1 รอ Player 2
                router.push(`/waitingForPlayer?player1=${player1Name}&player2=${player2Name}&mode=${mode}`);
            } else {
                // เผื่อ fallback
                router.push(`/game?player1=${player1Name}&player2=${player2Name}&mode=${mode}`);
            }
        } catch (error) {
            console.error("Error submitting minions:", error);
            alert("Error submitting minions. Please try again.");
        }
    };
    return (
        <div className="container">
            <h1>Select Your Minions</h1>
            <h2>{player === "1" ? player1Name : player2Name}</h2>

            <div id="minionSelection">
                <label>Choose number of Minions (1-5):</label>
                <input
                    type="number"
                    min="1"
                    max="5"
                    value={selectedMinions}
                    onChange={(e) => handleMinionSelection(parseInt(e.target.value))}
                />
            </div>

            <div className="minion-list">
                {[...Array(selectedMinions)].map((_, i) => {
                    const minionNumber = i + 1;
                    return (
                        <div key={minionNumber} className="minion-row">
                            <div
                                className="minion-icon"
                                style={{
                                    backgroundColor: minionColors[(minionNumber - 1) % minionColors.length],
                                }}
                            />
                            <button onClick={() => handleMinionClick(minionNumber)}>
                                Minion {minionNumber}
                            </button>
                            <input
                                type="text"
                                className="minion-name-input"
                                placeholder={`Enter name for Minion ${minionNumber}`}
                                value={minionNames[minionNumber] || ""}
                                onChange={(e) => handleNameChange(minionNumber, e.target.value)}
                            />
                            <input
                                type="number"
                                className="minion-defense-input"
                                placeholder="Def: 1-20"
                                min="1"
                                max="20"
                                value={minionDefense[minionNumber] || ""}
                                onChange={(e) => handleDefenseChange(minionNumber, e.target.value)}
                            />
                        </div>
                    );
                })}
            </div>

            {selectedMinion !== null && (
                <div id="minionDetails">
                    <h2>{minionNames[selectedMinion] || `Minion ${selectedMinion}`} Code</h2>
                    <textarea
                        placeholder="Write your minion's code here..."
                        value={minionCodes[selectedMinion] || ""}
                        onChange={(e) => handleCodeChange(e.target.value)}
                    />
                </div>
            )}
            <button onClick={() => router.back()}>Back ⬅️</button>
            <button onClick={submitAll}>Submit All Minions 🚀</button>
        </div>
    );
};

export default MinionPage;
