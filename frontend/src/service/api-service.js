import axios from "axios";

export const addMatch = (matchId, round, match) =>
    axios.put(`/api/HighscoreDarts/match/${matchId}/round/${round}`, match)
        .then(response => response.data)

export const findPlayer = (id, player) =>
    axios.get(`/api/HighscoreDarts/player/${player.id}`, player)
        .then(response => response.data)

export const updatePlayer = (id, player) =>
    axios.put(`/api/HighscoreDarts/player/${player.id}`, player)
        .then(response => response.data)

export const deletePlayer = (id, player) =>
    axios.delete(`/api/HighscoreDarts/player/${player.id}`)
        .then(response => response.data)

export const postPlayer = (tournamentId, groupName, player) =>
    axios.post(`/api/HighscoreDarts/player/tournament/${tournamentId}/group/${groupName}`, player)
        .then(response => response.data)
        .then(dto => dto.player)

export const getAllPlayer = (player) =>
    axios.get("/api/HighscoreDarts/player", player)
        .then(response => response.data)

export const findTournament = (tournamentId) =>
    axios.get(`/api/HighscoreDarts/tournament/${tournamentId}`)
        .then(response => response.data)

export const startTournament = (tournamentId) =>
    axios.put(`/api/HighscoreDarts/tournament/${tournamentId}/start`, null)
        .then(response => response.data)

export const finishTournament = (tournamentId) =>
    axios.put(`/api/HighscoreDarts/tournament/${tournamentId}/finish`, null)
        .then(response => response.data)

export const postTournament = (tournamentRounds, tournamentGroups) =>
    axios.post(`api/HighscoreDarts/tournament/rounds/${tournamentRounds}/groups/${tournamentGroups}`, null)
        .then(response => response.data)