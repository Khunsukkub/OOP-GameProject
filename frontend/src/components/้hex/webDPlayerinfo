"use client";

import React from "react";
import "./player-info.css";

type Minion = {
    name: string;
    color: string;
    cost: number;
};

type PlayerInfoProps = {
    playerData: Record<string, { money: number; minions: Minion[] }>;
};

const PlayerInfo: React.FC<PlayerInfoProps> = ({ playerData }) => {
    return (
        <div className="player-info">
            {Object.entries(playerData).map(([player, data]) => (
                <div key={player} className="player-card">
                    <h3>{player}: 💰 {data.money}, Minions: {data.minions.length}</h3>
                    <div className="minion-list">
                        {data.minions.map((minion, index) => (
                            <div key={index} className="minion-item">
                                <div className="minion-icon" style={{ backgroundColor: minion.color }} />
                                <span>{minion.name} (${minion.cost})</span>
                            </div>
                        ))}
                    </div>
                </div>
            ))}
        </div>
    );
};

export default PlayerInfo;
