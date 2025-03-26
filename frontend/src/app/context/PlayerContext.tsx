import React, { createContext, useContext, useState, ReactNode, useEffect } from "react";

// กำหนด type ให้กับ Player
export interface Player {
    name: string;
    budget: number;
    ownHex: any[];
    ownMinion: any[];
    MinionNumber: number;
    HexNumber: number;
    Number: number;
}

// สร้าง Context ที่เก็บ array ของ players พร้อมกับฟังก์ชันในการเพิ่ม player
interface PlayerContextType {
    players: Player[];
    addPlayer: (player: Player) => void;
    resetPlayers: () => void; // ฟังก์ชันในการรีเซ็ต player
}

const PlayerContext = createContext<PlayerContextType | undefined>(undefined);

export const usePlayer = () => {
    const context = useContext(PlayerContext);
    if (!context) {
        throw new Error("usePlayer must be used within a PlayerProvider");
    }
    return context;
};

export const PlayerProvider = ({ children }: { children: ReactNode }) => {
    const [players, setPlayers] = useState<Player[]>(() => {
        const savedPlayers = localStorage.getItem("players");
        return savedPlayers ? JSON.parse(savedPlayers) : [];
    });

    const [isReset, setIsReset] = useState(false);

    // ลบข้อมูลทั้งหมดใน localStorage เมื่อรีเซ็ต players
    useEffect(() => {
        if (isReset) {
            const [players, setPlayers] = useState<Player[]>([]); // ใช้ useState เก็บ players
        }
    }, [isReset]);

    useEffect(() => {
        // ทุกครั้งที่ players เปลี่ยนแปลง ให้เก็บค่าลงใน localStorage
        localStorage.setItem("players", JSON.stringify(players));
    }, [players]);

    const addPlayer = (player: Player) => {
        setPlayers((prevPlayers) => [...prevPlayers, player]);
    };

    const resetPlayers = () => {
        setPlayers([]); // รีเซ็ต players
        setIsReset(true); // ตั้งค่าสถานะการเริ่มใหม่
    };

    return (
        <PlayerContext.Provider value={{ players, addPlayer, resetPlayers }}>
            {children}
        </PlayerContext.Provider>
    );
};
