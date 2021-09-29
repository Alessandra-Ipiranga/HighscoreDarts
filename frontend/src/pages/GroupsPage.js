import Button from "../components/Button";
import Page from "../components/Page"

import Form from "../components/Form";
import Ul from "../components/Ul";
import {useEffect} from "react";
import {getAllPlayer, getTournament} from "../service/api-service";

export default function GroupsPage(props) {
    //const {number} = useParams()

    useEffect(() => {
        getTournament()
    }, [])

    return (
        <Page>
            <Form>
                <Ul>{props.groups}</Ul>
                <Button>Gruppe</Button>
            </Form>
        </Page>
    )
}