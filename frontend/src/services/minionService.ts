import axios from "axios";

const API_URL = "http://localhost:8001/game/api";

export const submitMinions = async (player: string, minions: any[]) => {
    return await axios.post(`${API_URL}/player/minions`, { player, minions });
};

export const numbersMinionSetting = async (number: number) => {
    return await axios.post(`${API_URL}/NumbersMinionSetting`, number, {
        headers: { "Content-Type": "application/json" }
    });
};

export const configureMinionById = async (
    id: string,
    minionName: string,
    HP: string,
    DEF: string,
    spawnCost: string,
    strategy: string,
    playerNumber: string
) => {
    return await axios.post(`${API_URL}/MinionSetting/${id}`, null, {
        params: { minionName, HP, DEF, spawnCost, strategy, playerNumber }
    });
};
