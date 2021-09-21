import Button from "../components/Button";
import Form from "../components/Form";
import Header from "../components/Header";
import Input from "../components/Input";
import Page from "../components/Page"
import {postPlayer} from "../service/api-service";
import {useState} from "react";

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
            <div>
            <Header/>
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
            </div>
        </Page>
    )
}