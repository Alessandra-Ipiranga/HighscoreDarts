import './App.css';
import GroupsPage from "./pages/GroupsPage";
import PlayersListPage from "./pages/PlayersListPage";
import PlayersPage from "./pages/PlayersPage";
import CreateTournamentPage from "./pages/CreateTournamentPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {useState} from "react";

export default function App() {

    const [tournament, setTournament] = useState();

    return (
        <Router>
            <Switch>
                <Route exact path="/">
                    <CreateTournamentPage
                    setTournament={setTournament}
                    />
                </Route>
                <Route path="/players" component={PlayersPage}/>
                <Route path="/groups" >
                    <GroupsPage
                        setTournament={setTournament}
                    />
                </Route>

                />
                <Route path="/playersList" component={PlayersListPage}/>
            </Switch>
        </Router>
    );
}