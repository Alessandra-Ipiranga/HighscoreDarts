import axios from "axios";

export const getPlayer = player =>
    axios.get("api/HighscoreDarts/dart/"+player)
        .then(response => response.data)

export const getAllPlayer = (player) =>
    axios.get("api/HighscoreDarts/dart", player)
        .then(response => response.data)

export const postPlayer = (player) =>
    axios.post(`api/HighscoreDarts/dart`, player)
        .then(response => response.data)

export const deletePlayer = (player) =>
    axios.delete(`api/HighscoreDarts/dart/`+player.name, player)
        .then(response => response.data)

export const putPlayer = (player) =>
    axios.put(`api/HighscoreDarts/dart/`+player.name, player)
        .then(response => response.data)