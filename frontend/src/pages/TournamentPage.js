import Button from "../components/Button"
import ButtonGroup from "../components/ButtonGroup"
import Form from "../components/Form"
import Header from "../components/Header";
import Input from "../components/Input"
import Label from "../components/Label";
import Page from "../components/Page";
import WarningTag from "../components/WarningTag";
import {Link} from "react-router-dom";
import {useState} from "react";

const initialState = {
    group: "",
    round: "",
}

export default function TournamentPage() {

    const [number, setNumber] = useState(initialState)

    const handleNumberChange = event =>
        setNumber({...number, [event.target.name]: event.target.value})

    const clear = (event) => {
        event.preventDefault()
        setNumber(initialState)
    }

    const render = (event) => {
        event.preventDefault()
    }

    return (
        <Page>
            <div>
                <Header/>
                <Form>
                    <Label>Bitte geben Sie die Anzahl der Gruppen ein! </Label>
                    <Input
                        type="number"
                        name="group"
                        value={number.group}
                        onChange={handleNumberChange}
                    />
                    <Label>Bitte geben Sie die Anzahl der Runden ein! </Label>
                    <Input
                        type="number"
                        name="round"
                        value={number.round}
                        onChange={handleNumberChange}
                    />
                    <ButtonGroup>
                        {((number.group <= 20 && number.group > 0) && (number.round <= 20 && number.round > 0)) ?
                            <Button onClick={render}><Link to={`/groups/${number.group}`}>Turnier erzeugen</Link>
                            </Button> : <p>Bitte alle Felder ausfüllen!</p>}
                        {(((number.group < 0 || number.group > 20) || (number.round < 0 || number.round > 20))) &&
                        <WarningTag>Zahl nicht zulässig! <br/> Bitte überprüfen Sie Ihre Eingabe!</WarningTag>}
                        <Button type="button" onClick={clear}>Clear</Button>
                    </ButtonGroup>
                </Form>
            </div>
        </Page>
    );
}