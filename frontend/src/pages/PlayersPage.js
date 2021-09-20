import Page from "../components/Page"
import Input from "../components/Input";
import Form from "../components/Form";
import Button from "../components/Button";
import {useState} from "react";
import axios from "axios";

export default function PlayersPage(props) {

    const [name, setName] = useState('')

    const nameHandler = event => {
        setName(event.target.value)

    }

    const handleSubmit = (event) => {
        event.preventDefault();
    }

    const handleClick = () => {
        axios.post(`api/HighscoreDarts/dart/${name}`, "string")
            .then(() => setName(""))
            .catch(error => console.log(error))
    }

    return (
        <Page>
            <Form onSubmit={handleSubmit}>
                <Input
                    type="text"
                    name="group"
                    placeholder="Name eingeben"
                    value={name}
                    onChange={nameHandler}
                />
                <Button type="button" onClick={handleClick}>Add</Button>
            </Form>
        </Page>
    )
}