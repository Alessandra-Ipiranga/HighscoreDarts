import './App.css';
import TournamentPage from "./pages/TournamentPage";
import GroupsPage from "./pages/GroupsPage";
import PlayersPage from "./pages/PlayersPage";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import axios from "axios";
import {useEffect, useState} from "react";
import {getPlayer} from "./service/api-service";

export default function App() {

    const [setPlayer] = useState()

    const createPlayer = name =>
        axios.post("/"+name)
            .then(() => setPlayer)
            .then(player => setPlayer(player))
            .catch(error => console.log(error))

    useEffect(() => {
        getPlayer()
            .then(player => setPlayer(player))
            .catch(error => console.log(error))
    }, [])

    return (
        <Router>
            <Switch>
                <Route path="/newtournament" component={TournamentPage}/>
                <Route path="/groups/:number" component={GroupsPage}/>
                <Route onAdd={createPlayer} path="/players" component={PlayersPage}/>
            </Switch>
        </Router>
    );
}