import './App.css';
import GroupsPage from "./pages/GroupsPage";
import PlayersPage from "./pages/PlayersPage";
import TournamentPage from "./pages/TournamentPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";

export default function App() {

    return (
        <Router>
            <Switch>
                <Route path="/newtournament" component={TournamentPage}/>
                <Route path="/groups/:number" component={GroupsPage}/>
                <Route path="/players" component={PlayersPage}/>
            </Switch>
        </Router>
    );
}