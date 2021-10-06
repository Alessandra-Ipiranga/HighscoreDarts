import {useHistory, useParams} from "react-router";
import styled from 'styled-components/macro'
import Button from "../components/Button";

export default function WinnerPage() {

    const {winnerName, winnerScore} = useParams()
    const history = useHistory()

    const handleNewGame  = () => {
        history.push("/")
    }

    return (
        <Page>
            <h1>{winnerName}, you are the best!</h1>
            <h2>Your score is {winnerScore}</h2>
            <h2>Congrats!</h2>
            <Button onClick={handleNewGame}>New Game</Button>
        </Page>
    )
}

const Page = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: var(--background-light);
  background-color: grey;
  color: var(--neutral-light);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  `