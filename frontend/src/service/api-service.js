import axios from "axios";

export const getPlayer = () =>
    axios.get("/").then(response => response.data)

export const postPlayer = (name) =>
    axios.post(`api/HighscoreDarts/dart`,  name)