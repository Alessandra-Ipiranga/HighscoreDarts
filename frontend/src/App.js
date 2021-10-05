import './App.css';
import GroupsPage from "./pages/GroupsPage";
import PlayersListPage from "./pages/PlayersListPage";
import PlayersPage from "./pages/PlayersPage";
import AddPlayersPage from "./pages/AddPlayersPage";
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
                <Route path="/players">
                    <PlayersPage
                        tournament={tournament}
                    />
                </Route>
                <Route path="/groups">
                    <GroupsPage
                        tournament={tournament}
                    />
                </Route>

                <Route path="/addPlayers/:tournamentId/:groupName" component = {AddPlayersPage}/>
                <Route path="/playersList">
                    <PlayersListPage
                        tournament={tournament}
                    />
                </Route>
            </Switch>
        </Router>
    );
}