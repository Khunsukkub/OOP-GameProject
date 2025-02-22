import React, { useEffect } from "react";

const GameBoard: React.FC = () => {
    useEffect(() => {
        createHexGrid();
    }, []);

    const createHexGrid = () => {
        const grid = document.getElementById("gameBoard");
        if (!grid) return;

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
    };

    return <div className="hex-grid" id="gameBoard"></div>;
};

export default GameBoard;
