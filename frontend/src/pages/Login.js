import LoginPage from "../components/LoginPage";
import Header from "../components/Header";
import Form from "../components/Form"
import Input from "../components/Input"
import Button from "../components/Button"

export default function Login() {

    return (
        <LoginPage>
            <Form>
                <Header/>
                <Input type="text" name="username" id="userId" placeholder="Username"/>
                <Input type="password" name="password" id="examplePassword" placeholder="Password"/>
                <Button>Login</Button>
            </Form>
        </LoginPage>
    );
}


