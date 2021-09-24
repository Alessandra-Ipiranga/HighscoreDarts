import axios from "axios";

export const getPlayer = player =>
    axios.get("api/HighscoreDarts/player/"+player)
        .then(response => response.data)

export const getAllPlayer = (player) =>
    axios.get("api/HighscoreDarts/player", player)
        .then(response => response.data)

export const postPlayer = (player) =>
    axios.post(`api/HighscoreDarts/player`, player)
        .then(response => response.data)

export const deletePlayer = (player) =>
    axios.delete(`api/HighscoreDarts/player/`+player.name, player)
        .then(response => response.data)

export const putPlayer = (player) =>
    axios.put(`api/HighscoreDarts/player/`+player.name, player)
        .then(response => response.data)