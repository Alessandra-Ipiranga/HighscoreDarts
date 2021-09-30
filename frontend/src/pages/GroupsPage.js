import Button from "../components/Button";
import Page from "../components/Page"

import Form from "../components/Form";
import Ul from "../components/Ul";

export default function GroupsPage(props) {
    //const {number} = useParams()

    return (
        <Page>
            <Form>
                <Ul>{props.groups}</Ul>
                <Button>Gruppe</Button>
            </Form>
        </Page>
    )
}