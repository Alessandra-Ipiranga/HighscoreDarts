import Button from "../components/Button";
import Form from "../components/Form";
import Page from "../components/Page"
import {useState} from "react";
import Header from "../components/Header";
import Select from "../components/Select";
import Label from "../components/Label";
import {useHistory} from "react-router";

export default function GroupsPage({tournament}) {

    const [value, setValue] = useState('');
    const [groupName] = useState([])
    const [groupId] = useState([])

    const history = useHistory()
    const handleChange = (event) => {
        setValue(event.target.value)
        console.log(event.target.value)
    }

    const handleSubmit = (event) => {
        event.preventDefault()
        history.push(`/addPlayers/${tournament.id}/${value}`)
        //alert('You chose group number: ' + value)

    }

    const groupsList = tournament.groups.map(groupName =>
        <option key={groupId.id}> {groupName.name}</option>)

    return (
        <Page>
            <Form onSubmit={handleSubmit}>
                <Header/>
                <Label>Auswahl der Gruppe</Label>
                <Select value={groupName.name} onChange={handleChange}>
                    {groupsList}
                </Select>
                <Button type="submit" value="Submit"> Zur Gruppe </Button>
            </Form>
        </Page>
    )
}