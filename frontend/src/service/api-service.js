import axios from "axios";

export const getPlayer = player =>
    axios.get("api/HighscoreDarts/dart/"+player)
        .then(response => response.data)

export const getAllPlayer = () =>
    axios.get("api/HighscoreDarts/dart")
        .then(response => response.data)

export const postPlayer = (player) =>
    axios.post(`api/HighscoreDarts/dart`, player)
        .then(response => response.data)