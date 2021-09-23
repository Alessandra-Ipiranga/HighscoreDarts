import Page from "../components/Page";
import {getAllPlayer} from "../service/api-service";
import {useEffect, useState} from "react";
import Header from "../components/Header";


export default function PlayersList() {

    const [name, setName] = useState([])

    useEffect(() => {
        getAllPlayer()
            .then(setName)
    }, [])

    const namesList = name.map(name => name.name)

    console.log(namesList)

    return (
        <Page>
           <section>
               <Header/>
               <ul><li>{namesList}</li></ul>
           </section>
        </Page>
    )

}