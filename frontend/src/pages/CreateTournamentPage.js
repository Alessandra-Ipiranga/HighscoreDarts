import Button from "../components/Button"
import ButtonGroup from "../components/ButtonGroup"
import Form from "../components/Form"
import Header from "../components/Header";
import Input from "../components/Input"
import Label from "../components/Label";
import Page from "../components/Page";
import WarningTag from "../components/WarningTag";
import {postTournament} from "../service/api-service";
import {useState} from "react";
import {useHistory} from "react-router";

const initialState = {
    groups: "",
    rounds: "",
}

export default function CreateTournamentPage(props) {

    const [tournament, setTournament] = useState(initialState)

    const history = useHistory();

    const handleNumberChange = event =>
        setTournament({...tournament, [event.target.name]: event.target.value})
    const clear = (event) => {
        event.preventDefault()
        setTournament(initialState)

    }
    const handleSubmit = (event) => {
        event.preventDefault()
        postTournament(tournament)
            .then(props.setTournament)
            .then( () => history.push("/groups"))
            .catch(error => console.log(error))

    }

    const validInput = (tournament.groups <= 20 && tournament.groups > 0) && (tournament.rounds <= 20 && tournament.rounds > 0)
    const inputInRange = (tournament.groups < 0 || tournament.groups > 20) || (tournament.rounds < 0 || tournament.rounds > 20)

    return (
        <Page>
            <div>
                <Header/>
                <Form onSubmit={handleSubmit}>
                    <Label>Bitte geben Sie die Anzahl der Gruppen ein! </Label>
                    <Input
                        type="number"
                        name="rounds"
                        value={tournament.rounds}
                        onChange={handleNumberChange}
                    />
                    <Label>Bitte geben Sie die Anzahl der Runden ein! </Label>
                    <Input
                        type="number"
                        name="groups"
                        value={tournament.groups}
                        onChange={handleNumberChange}
                    />
                    <ButtonGroup>
                        {(validInput) ?
                            <Button>Turnier erzeugen</Button> :
                            <p>Bitte alle Felder ausfüllen!</p>}
                        {((inputInRange)) &&
                        <WarningTag>Zahl nicht zulässig! <br/> Bitte überprüfen Sie Ihre Eingabe!</WarningTag>}
                        <Button type="button" onClick={clear}>Clear</Button>
                    </ButtonGroup>
                </Form>
            </div>
        </Page>
    );
}