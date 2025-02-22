import React from "react";

interface PlayerInfoProps {
    playerData: Record<string, { money: number; minions: string[] }>;
}

const PlayerInfo: React.FC<PlayerInfoProps> = ({ playerData }) => {
    return (
        <div className="player-info">
            {Object.entries(playerData).map(([player, data]) => (
                <div key={player}>
                    {player}: 💰 {data.money}, Minions: {data.minions.length}
                </div>
            ))}
        </div>
    );
};

export default PlayerInfo;
