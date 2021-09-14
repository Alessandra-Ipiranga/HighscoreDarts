import Page from "../components/Page"
import Button from "../components/Button";
import {Link} from "react-router-dom";
import {useParams} from "react-router";

export default function GroupsPage() {
    const {number} = useParams()

    return (
        <Page>
            <Button><Link to={"/players"}>Gruppe {number}</Link></Button>
        </Page>
    )
}