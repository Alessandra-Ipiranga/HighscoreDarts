import styled from 'styled-components/macro'
import Page from "../components/Page";
import Header from "../components/Header";
import Form from "../components/Form"
import Input from "../components/Input"
import Button from "../components/Button"
import {useState} from "react";

const initialState = {
    group: '',
    round: '',
}

export default function NewTournamentPage() {

    const [number, setNumber] = useState(initialState)

    const clear = (event) => {
        event.preventDefault()
        setNumber(initialState)
    }

    const handleNumberChange = event =>
        setNumber({ ...number, [event.target.name]: event.target.value })

    return (
        <Page>
            <Form>
                <Header/>
                <Input
                    type="text"
                    name="group"
                    placeholder="Gruppenanzahl"
                    value={number.group}
                    onChange={handleNumberChange}
                />
                <Input
                    type="text"
                    name="round"
                    placeholder="Rundenanzahl"
                    value={number.round}
                    onChange={handleNumberChange}
                />
                <ButtonGroup>
                    <Button>Neues Turnier beginnen</Button>
                    <Button onClick={clear}>Löschen</Button>
                </ButtonGroup>
            </Form>
        </Page>
    );
}

const ButtonGroup = styled.div`
  display: flex;
  justify-content: space-between;
`

