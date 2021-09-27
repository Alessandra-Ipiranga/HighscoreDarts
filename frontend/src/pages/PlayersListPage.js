import Header from "../components/Header";
import List from "../components/List";
import Page from "../components/Page";
import Ul from "../components/Ul";
import {deletePlayer, getAllPlayer, putPlayer} from "../service/api-service";
import {useEffect, useState} from "react";

export default function PlayersListPage() {

    const [player, setPlayer] = useState([])

    useEffect(() => {
        getAllPlayer()
            .then(setPlayer)
    }, [])

    const handleClickDelete = (player) => {
        deletePlayer(player).then(getAllPlayer).then(setPlayer)
    }

    const handleClickUpdate = (player) => {
        putPlayer(player).then(getAllPlayer).then(setPlayer)
    }

    const playersList = player.map(player =>
        <List key={player.id}> {player.name}
            <button onClick={() => handleClickDelete(player)}>Delete</button>
            <button onClick={() => handleClickUpdate(player)}>Update</button>
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