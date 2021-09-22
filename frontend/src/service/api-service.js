import axios from "axios";

export const getPlayer = () =>
    axios.get("/").then(response => response.data)

export const postPlayer = (player) =>
    axios.post(`api/HighscoreDarts/dart`, player)