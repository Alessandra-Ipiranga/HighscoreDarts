import Header from "../components/Header";
import List from "../components/List";
import Page from "../components/Page";
import Ul from "../components/Ul";
import {deletePlayer, getAllPlayer} from "../service/api-service";
import {useEffect, useState} from "react";

export default function PlayersListPage() {

    const [player, setPlayer] = useState([])

    useEffect(() => {
        getAllPlayer()
            .then(setPlayer)
    }, [])

    useEffect(() => {
        deletePlayer(player)
    }, [])

    const handleClick = (player) => {
        deletePlayer(player)
    }

    const playersList = player.map(player =>
        <List key={player.id}> {player.name}
            <button onClick={() => handleClick(player)}  >Delete</button>
            <button onClick={() => handleClick(player)}  >Update</button>
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