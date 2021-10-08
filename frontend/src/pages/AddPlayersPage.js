import Button from "../components/Button";
import Form from "../components/Form";
import Input from "../components/Input";
import Label from "../components/Label";
import Page from "../components/Page"
import {postPlayer} from "../service/api-service";
import {useState} from "react";
import ButtonGroup from "../components/ButtonGroup";
import {useHistory, useParams} from "react-router";
import List from "../components/List";
import Header from "../components/Header";

export default function AddPlayersPage() {

    const {tournamentId, groupName} = useParams()
    const [player, setPlayer] = useState()
    const [playersList, setPlayersList] = useState([{}])
    const history = useHistory()

    const nameHandler = event =>
        setPlayer(event.target.value)

    const handleSubmit = (event) => {
        event.preventDefault()
        postPlayer(tournamentId, groupName, {name: player, id: 0})
            .then(player => setPlayersList([...playersList, player]))
            .then(() => setPlayer(""))
            .catch(error => console.error(error))
            .finally( () => console.log(playersList))
    }
    const startMatch = () => {
        history.push(`/match/${tournamentId}/${groupName}`)
    }

    return (
        <Page>
            <Form onSubmit={handleSubmit}>
                <Header/>
                <Label>Geben Sie den Namen der Spieler ein</Label>
                <Input
                    type="text"
                    name="player"
                    value={player}
                    onChange={nameHandler}
                />
                <ButtonGroup>
                    <Button>Player hinzufügen</Button>
                    <Button type="button" onClick = {startMatch}>start match</Button>
                </ButtonGroup>
                <h2>Teilnehmer</h2>
                {playersList.length > 0 && <ol>
                {playersList.map(player =>
                    (<List key={player.id}> {player.name}
                    </List>))}
            </ol>}
            </Form>
        </Page>
    )
}