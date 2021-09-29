import axios from "axios";

export const getPlayer = player =>
    axios.get(`api/HighscoreDarts/player/${player.id}`, player)
        .then(response => response.data)

export const getAllPlayer = (player) =>
    axios.get("api/HighscoreDarts/player", player)
        .then(response => response.data)

export const postPlayer = (tournamentId, groupName, player) =>
    axios.post(`api/HighscoreDarts/player/tournament/${tournamentId}/group/${groupName}`, player)
        .then(response => response.data)

export const deletePlayer = (player) =>
    axios.delete(`api/HighscoreDarts/player/${player.id}`, player)
        .then(response => response.data)

export const getTournament = (tournament) =>
    axios.post(`api/HighscoreDarts/tournament/${tournament.id}`, null)
        .then(response => response.data)

export const postTournament = (tournament) =>
    axios.post(`api/HighscoreDarts/tournament/rounds/${tournament.rounds}/groups/${tournament.groups}`, null)
        .then(response => response.data)