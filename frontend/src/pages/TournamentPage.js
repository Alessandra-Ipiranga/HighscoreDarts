import Button from "../components/Button"
import ButtonGroup from "../components/ButtonGroup"
import Form from "../components/Form"
import Header from "../components/Header";
import Input from "../components/Input"
import Label from "../components/Label";
import Page from "../components/Page";
import WarningTag from "../components/WarningTag";
import {Link} from "react-router-dom";
import {postTournament} from "../service/api-service";
import {useState} from "react";

const initialState = {
    groups: "",
    rounds: "",
}

export default function TournamentPage() {

    const [tournament, setTournament] = useState(initialState)
    const [groups, setGroups] = useState()
    const [rounds, setRounds] = useState()

    const handleNumberChange = event =>
        setTournament({...tournament, [event.target.name]: event.target.value})

    const clear = (event) => {
        event.preventDefault()
        setTournament(initialState)
    }

    const handleClick = () => {
        postTournament(tournament.rounds)
            .catch(error => console.log(error))
    }

    return (
        <Page>
            <div>
                <Header/>
                <Form>
                    <Label>Bitte geben Sie die Anzahl der Gruppen ein! </Label>
                    <Input
                        type="number"
                        name="groups"
                        value={groups}
                        onChange={handleNumberChange}
                    />
                    <Label>Bitte geben Sie die Anzahl der Runden ein! </Label>
                    <Input
                        type="number"
                        name="rounds"
                        value={rounds}
                        onChange={handleNumberChange}
                    />
                    <ButtonGroup>
                        {((tournament.groups <= 20 && tournament.groups > 0) && (tournament.rounds <= 20 && tournament.rounds > 0)) ?
                            <Button onClick={handleClick}><Link to={`/groups/${tournament.groups}`}>Turnier erzeugen</Link>
                            </Button> : <p>Bitte alle Felder ausfüllen!</p>}
                        {(((tournament.groups < 0 || tournament.groups > 20) || (tournament.rounds < 0 || tournament.rounds > 20))) &&
                        <WarningTag>Zahl nicht zulässig! <br/> Bitte überprüfen Sie Ihre Eingabe!</WarningTag>}
                        <Button type="button" onClick={clear}>Clear</Button>
                    </ButtonGroup>
                </Form>
            </div>
        </Page>
    );
}