import React, { useEffect } from "react";

interface Minion {
    id: number;
    name: string;
    color: string;
    position: { q: number; r: number };
}

interface GameBoardProps {
    minions: Minion[];
}

const GameBoard: React.FC<GameBoardProps> = ({ minions }) => {
    useEffect(() => {
        createHexGrid();
    }, []);

    const createHexGrid = () => {
        const grid = document.getElementById("gameBoard");
        if (!grid) return;

        grid.innerHTML = ""; // เคลียร์ก่อนสร้างใหม่

        const rows = 8;
        const cols = 8;
        const size = 30;

        for (let r = 0; r < rows; r++) {
            for (let q = 0; q < cols; q++) {
                const x = size * 1.5 * q;
                const y = size * Math.sqrt(3) * (r + 0.5 * (q % 2));

                const hex = document.createElement("div");
                hex.className = "hex";
                hex.style.transform = `translate(${x}px, ${y}px)`;
                grid.appendChild(hex);
            }
        }

        // **แสดง Minion ที่ตำแหน่งของพวกเขา**
        minions.forEach((minion) => {
            placeMinion(minion);
        });
    };

    const placeMinion = (minion: Minion) => {
        const grid = document.getElementById("gameBoard");
        if (!grid) return;

        const size = 30;
        const { q, r } = minion.position;

        const x = size * 1.5 * q;
        const y = size * Math.sqrt(3) * (r + 0.5 * (q % 2));

        const minionElement = document.createElement("div");
        minionElement.className = "minion";
        minionElement.style.backgroundColor = minion.color;
        minionElement.style.transform = `translate(${x}px, ${y}px)`;
        minionElement.innerText = minion.name[0]; // ใช้อักษรตัวแรกของชื่อ

        grid.appendChild(minionElement);
    };

    return <div className="hex-grid" id="gameBoard"></div>;
};

export default GameBoard;