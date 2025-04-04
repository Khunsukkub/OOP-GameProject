import React, { useEffect, useRef } from "react";

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
    const gridRef = useRef<HTMLDivElement>(null);
    const size = 30;
    const rows = 8;
    const cols = 8;

    const createHexGrid = () => {
        if (!gridRef.current) return;
        const grid = gridRef.current;
        grid.innerHTML = ""; // เคลียร์ grid ก่อนสร้างใหม่

        // สร้าง hex grid
        for (let r = 0; r < rows; r++) {
            for (let q = 0; q < cols; q++) {
                const x = size * 1.5 * q;
                const y = size * Math.sqrt(3) * (r - 0.5 * (q % 2));
                const hex = document.createElement("div");
                hex.className = "hex";
                hex.style.transform = `translate(${x}px, ${y}px)`;
                grid.appendChild(hex);
            }
        }

        // วาง minions ที่ตำแหน่งที่กำหนด
        minions.forEach((minion) => {
            placeMinion(minion);
        });
    };

    const placeMinion = (minion: Minion) => {
        if (!gridRef.current) return;
        const { q, r } = minion.position;
        const x = size * 1.5 * q;
        const y = size * Math.sqrt(3) * (r - 0.5 * (q % 2));
        const minionElement = document.createElement("div");
        minionElement.className = "minion";
        minionElement.style.backgroundColor = minion.color;
        minionElement.style.transform = `translate(${x}px, ${y}px)`;
        minionElement.innerText = minion.name[0]; // ใช้ตัวอักษรแรกของชื่อเป็นสัญลักษณ์
        gridRef.current.appendChild(minionElement);
    };

    // เมื่อ minions เปลี่ยน ให้สร้าง grid ใหม่
    useEffect(() => {
        createHexGrid();
    }, [minions]);

    return <div className="hex-grid" ref={gridRef} id="gameBoard"></div>;
};

export default GameBoard;
