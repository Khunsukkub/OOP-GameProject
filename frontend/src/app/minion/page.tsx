"use client";

import React, { useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import "./minion.css";

const MinionPage: React.FC = () => {
    const router = useRouter();
    const searchParams = useSearchParams();

    const [selectedMinions, setSelectedMinions] = useState<number>(1);
    const [minionNames, setMinionNames] = useState<Record<number, string>>({});
    const [minionDefense, setMinionDefense] = useState<Record<number, number>>({});
    const [minionCodes, setMinionCodes] = useState<Record<number, string>>({});
    const [selectedMinion, setSelectedMinion] = useState<number | null>(null);

    // เพิ่มอาร์เรย์สีสำหรับ Minion 1-5
    const minionColors = ["#FF5733", "#33FF57", "#3357FF", "#F4D03F", "#9B59B6"];

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

    const submitAll = () => {
        for (let i = 1; i <= selectedMinions; i++) {
            if (!minionNames[i] || !minionDefense[i]) {
                alert(`Minion ${i} needs name and defense value.`);
                return;
            }
        }

        // แปลงข้อมูลเป็น Array ของ Minion
        const minions = Object.keys(minionNames).map((key, index) => ({
            name: minionNames[parseInt(key)],
            color: minionColors[index % minionColors.length],
            cost: 10 + index * 5, // ราคา Minion เริ่มต้น 10 เพิ่มขึ้นทีละ 5
        }));

        // บันทึกค่า Minion ที่เลือกไว้ใน LocalStorage
        localStorage.setItem("selectedMinions", JSON.stringify(minions));

        alert("All minion codes submitted! Redirecting to the game...");
        router.push(`/game?player1=${searchParams.get("player1")}&player2=${searchParams.get("player2")}&mode=${searchParams.get("mode")}`);
    };



    return (
        <div className="container">
            <h1>Select Your Minions</h1>

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
                            {/* วงกลมสีแทน Minion แต่ละตัว */}
                            <div
                                className="minion-icon"
                                style={{
                                    backgroundColor:
                                        minionColors[(minionNumber - 1) % minionColors.length],
                                }}
                            />

                            {/* ปุ่มกดเพื่อเปิดโค้ด Minion */}
                            <button onClick={() => handleMinionClick(minionNumber)}>
                                Minion {minionNumber}
                            </button>

                            {/* ช่องกรอกชื่อ Minion */}
                            <input
                                type="text"
                                className="minion-name-input"
                                placeholder={`Enter name for Minion ${minionNumber}`}
                                value={minionNames[minionNumber] || ""}
                                onChange={(e) => handleNameChange(minionNumber, e.target.value)}
                            />

                            {/* ช่องกรอก Defense */}
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

            {/* ถ้าเลือก Minion (กดปุ่ม) จะแสดงช่องโค้ด */}
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
