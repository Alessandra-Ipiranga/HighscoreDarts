import {useHistory, useParams} from "react-router";
import {useEffect, useState} from "react";
import {findTournament} from "../service/api-service";
import Input from "../components/Input";
import Page from "../components/Page";
import styled from 'styled-components/macro'
import Label from "../components/Label";
import Button from "../components/Button";

export default function MatchPage() {

    const {tournamentId, groupName} = useParams()
    const [players, setPlayers] = useState([])
    const history = useHistory()

    useEffect(() => {
        findTournament(tournamentId)
            .then(tournament => {
                const groups = tournament.groups
                const group = groups.find(group => group.name === groupName)
                setPlayers(group.players.map(player => ({...player, score: 0})))
            })
            .catch(console.log)

    }, [tournamentId, groupName])

    const handleChange = (event, index) => {
        const newPlayers = [...players]
        newPlayers[index] = {...newPlayers[index], score:event.target.value}
        setPlayers(newPlayers)
    }

    const calculateWinner = () => {
        let winner = players[0]
        for(let i = 1; i < players.length; i++){
            if(winner.score < players[i].score){
                winner = players[i]
            }
        } history.push(`/winner/${winner.name}/${winner.score}`)
    }

    return (
        <Page>
            <Wrapper>
                {players && (players.map((player, index) =>
                    <Label key={player.id}>{player.name} <br/>
                        <Input value={players[index].score} onChange={(event) =>
                            handleChange(event, index)} type="number"/></Label>
                ))}

            </Wrapper>
            <Button onClick={calculateWinner}>Validate Winner</Button>
        </Page>
    )
}

const Wrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  align-items: center;
`
