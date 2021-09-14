import Page from "../components/Page";
import Header from "../components/Header";
import Form from "../components/Form"
import Input from "../components/Input"
import Button from "../components/Button"
import ButtonGroup from "../components/ButtonGroup"
import {useState} from "react";
import {Link} from "react-router-dom";
import WarningTag from "../components/WarningTag";
import Label from "../components/Label";

const initialState = {
    group: "",
    round: "",
}

export default function NewTournamentPage() {

    const [number, setNumber] = useState(initialState)

    const handleNumberChange = event =>
        setNumber({...number, [event.target.name]: event.target.value})

    const clear = (event) => {
        event.preventDefault()
        setNumber(initialState)}

    return (
        <Page>
            <Form>
                <Header/>
                <Label>Bitte geben Sie die Anzahl der Gruppen ein! ( von 1 bis 20 ) </Label>
                <Input
                    type="number"
                    name="group"
                    value={number.group}
                    onChange={handleNumberChange}
                    min="1" max="20"
                />
                <Label>Bitte geben Sie die Anzahl der Runden ein! ( von 1 bis 20 ) </Label>
                <Input
                    type="number"
                    name="round"
                    value={number.round}
                    onChange={handleNumberChange}
                    min="1" max="20"
                />
                <ButtonGroup>
                    {((number.group <= 20 && number.group > 0) && (number.round <= 20 && number.round > 0)) ?
                    <Button><Link to={`/groups/${number.group}`}>Neues Turnier beginnen</Link></Button> : <p>Bitte alle Felder ausfüllen!</p>}
                    {(((number.group < 0 || number.group > 20) || (number.round < 0 || number.round > 20))) &&
                    <WarningTag>Zahl nicht zulässig! <br/> Bitte überprüfen Sie Ihre Eingabe!</WarningTag>}
                    <Button type="button" onClick = {clear}>Clear</Button>
                </ButtonGroup>
            </Form>
        </Page>
    );
}