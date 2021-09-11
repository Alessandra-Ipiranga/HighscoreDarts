import './App.css';
import NewTournamentPage from "./pages/NewTournamentPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

export default function App() {
    return (
        <Router>
            <Switch>
                <Route path="/newtournament" component={NewTournamentPage}/>
            </Switch>
        </Router>
    );
}