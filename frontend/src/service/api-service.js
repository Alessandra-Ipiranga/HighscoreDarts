import axios from "axios";

export const getPlayer = () =>
    axios.get("/").then(response => response.data)
