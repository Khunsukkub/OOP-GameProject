"use client";

import React from "react";
import "./tile-shop.css"; // <- สร้างไฟล์นี้ไว้จัด style ด้านซ้าย

type Tile = {
    id: number;
    price: number;
    q: number;
    r: number;
};

type TileShopProps = {
    tiles: Tile[];
    onBuyTile: (tileId: number) => void;
    onClose: () => void;
};

const TileShop: React.FC<TileShopProps> = ({ tiles, onBuyTile, onClose }) => {
    return (
        <div className="tile-shop">
            <h2>Available Tiles</h2>
            <div className="tile-list">
                {tiles.length > 0 ? (
                    tiles.map((tile) => (
                        <button
                            key={tile.id}
                            className="tile-option"
                            onClick={() => onBuyTile(tile.id)}
                        >
                            Tile ({tile.q}, {tile.r}) - 💰 ${tile.price}
                        </button>
                    ))
                ) : (
                    <p>No tiles available</p>
                )}
            </div>
            <button className="close-button" onClick={onClose}>CLOSE ❌</button>
        </div>
    );
};

export default TileShop;
