import React, { createContext, useContext, useState, ReactNode , useEffect } from "react";

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
    players: Player[]; // เก็บ array ของ players
    addPlayer: (player: Player) => void; // ฟังก์ชันในการเพิ่ม player
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

    useEffect(() => {
        // ลบข้อมูลจาก localStorage เมื่อคอมโพเนนต์ถูก unmount
        return () => {
            localStorage.clear();
        };
    }, []);

    useEffect(() => {
        // ทุกครั้งที่ players เปลี่ยนแปลง ให้เก็บค่าลงใน localStorage
        localStorage.setItem("players", JSON.stringify(players));
    }, [players]);

    const addPlayer = (player: Player) => {
        setPlayers((prevPlayers) => [...prevPlayers, player]);
    };

    return (
        <PlayerContext.Provider value={{ players, addPlayer }}>
            {children}
        </PlayerContext.Provider>
    );
};
