import './App.css';
import NewTournamentPage from "./pages/NewTournamentPage";
import GroupsPage from "./pages/GroupsPage";
import PlayersPage from "./pages/PlayersPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

export default function App(...props) {
    return (
        <Router>
            <Switch>
                <Route path="/newtournament" component={NewTournamentPage}/>
                <Route path="/groups/:number" component={GroupsPage}/>
                <Route path="/players" component={PlayersPage}/>
            </Switch>
        </Router>
    );
}