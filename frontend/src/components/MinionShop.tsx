"use client";

import React from "react";
import "./minion-shop.css";

type MinionShopProps = {
    minionList: { name: string; color: string; cost: number }[];
    onClose: () => void;
    onBuyMinion: (name: string, color: string, cost: number) => void;
};

const MinionShop: React.FC<MinionShopProps> = ({ minionList, onClose, onBuyMinion }) => {
    return (
        <div className="minion-shop">
            <h2>Select Your Minion</h2>
            <div className="minion-list">
                {minionList.length > 0 ? (
                    minionList.map((minion, index) => (
                        <button
                            key={index}
                            className="minion-option"
                            style={{ backgroundColor: minion.color }}
                            onClick={() => onBuyMinion(minion.name, minion.color, minion.cost)}
                        >
                            <div className="minion-icon" style={{ backgroundColor: minion.color }} />
                            {minion.name} - ${minion.cost}
                        </button>
                    ))
                ) : (
                    <p>No Minions Available</p>
                )}
            </div>
            <button className="close-button" onClick={onClose}>Close ❌</button>
        </div>
    );
};

export default MinionShop;
