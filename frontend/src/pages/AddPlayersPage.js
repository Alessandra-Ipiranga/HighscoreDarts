import Button from "../components/Button";
import Form from "../components/Form";
import Input from "../components/Input";
import Label from "../components/Label";
import Page from "../components/Page"
import {postPlayer} from "../service/api-service";
import {useState} from "react";
import ButtonGroup from "../components/ButtonGroup";
import Header from "../components/Header";
import {useParams} from "react-router";

export default function AddPlayersPage() {

    const {tournamentId, groupName} = useParams()
    const [player, setPlayer] = useState()

    const nameHandler = event =>
        setPlayer(event.target.value)

    const handleSubmit = (event) => {
        event.preventDefault()
        postPlayer(tournamentId, groupName, {name: player, id: 0})
            .then(() => setPlayer(""))
            .catch(error => console.error(error))
    }



    return (
        <Page>
            <Form onSubmit={handleSubmit}>
                <Header/>
                <Label>Geben Sie den Namen des Spielers ein</Label>
                <Input
                    type="text"
                    name="player"
                    value={player}
                    onChange={nameHandler}
                />
                <ButtonGroup>
                    <Button>Player hinzufügen</Button>
                    <Button>start match</Button>
                </ButtonGroup>
            </Form>
        </Page>
    )
}