import Page from "../components/Page"
import Input from "../components/Input";
import Form from "../components/Form";

export default function PlayersPage() {
    return (
        <Page>
            <Form>
            <Input
                type="text"
                name="group"
                placeholder="Name eingeben"
                />
            <Input
                type="text"
                name="group"
                placeholder="Name eingeben"
            />
            <Input
                type="text"
                name="group"
                placeholder="Name eingeben"
            />
            </Form>
        </Page>
    )
}