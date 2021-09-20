import Page from "../components/Page"
import Input from "../components/Input";
import Form from "../components/Form";
import Button from "../components/Button";
import {useState} from "react";
import {postPlayer} from "../service/api-service";
import Header from "../components/Header";

export default function PlayersPage() {

    const [name, setName] = useState('')

    const nameHandler = event => {
        setName(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault();
    }

    const handleClick = () => {
        postPlayer(name)
            .then(() => setName(""))
            .catch(error => console.log(error))
    }

    return (
        <Page>
            <Form onSubmit={handleSubmit}>
            <Header/>
                <Input
                    type="text"
                    name="group"
                    placeholder="Name eingeben"
                    value={name}
                    onChange={nameHandler}
                />
                <Button type="button" onClick={handleClick}>Add</Button>
                <p>{name}</p>
            </Form>
        </Page>
    )
}