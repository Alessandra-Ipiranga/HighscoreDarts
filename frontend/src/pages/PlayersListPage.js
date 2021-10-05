import Header from "../components/Header";
import List from "../components/List";
import Page from "../components/Page";
import Ul from "../components/Ul";
import {deletePlayer, getAllPlayer} from "../service/api-service";
import {useEffect, useState} from "react";

export default function PlayersListPage({setTournament}) {

    const [player, setPlayer] = useState([])

    useEffect(() => {
        getAllPlayer()
            .then(setPlayer)
    }, [])

    const handleClickDelete = (player) => {
        deletePlayer(player)
            .then(getAllPlayer)
            .then(setPlayer)
    }

    const playersList = player.map(player =>
        <List key={player.id}> {player.name}
            <button onClick={() => handleClickDelete(player)}>Delete</button>
        </List>)

    return (
        <Page>
            <div>
                <Header/>
                <Ul>
                    {playersList}
                </Ul>
            </div>
        </Page>
    )
}