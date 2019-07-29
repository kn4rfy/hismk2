import React from 'react';
import ApolloClient from "apollo-boost";
import { Query,Mutation , ApolloProvider} from "react-apollo";
import './App.css';
import { gql } from "apollo-boost";

const client = new ApolloClient({
    uri: "http://localhost:8080/graphql"
});



const GETPATIENTS=gql`

    {
        patients {
            id
            lastname
            firstname,
            patientNo
        }
    }

`;


const PATIENTSBYPAGE = gql`

    query
    patientPage($first:Int!, $after: String!) {
        patients:  patientsByPage (first:$first,after:$after){
            edges{
                node {
                    id
                    lastname
                    firstname
                    patientNo
                }
                cursor
            }
            pageInfo {
                hasNextPage
                hasPreviousPage
                startCursor
                endCursor
            }
        }

    }


`;


const  ADDPATIENT = gql`



    mutation ($fields:Map_String_ObjectScalar){
        upsertPatient(fields: $fields){
            id
            lastname
            firstname
            patientNo
        }
    }
`;


class App extends React.Component {


    state={
        lastname:'',
        firstname:''
    };
    render() {

        return (
            <div className="App">
                <ApolloProvider client={client}>
                    <h4>Patient List</h4>

                    <table border="1">
                        <tbody>
                        <Query
                            query={GETPATIENTS}
                        >
                            {({ loading, error, data }) => {
                                if (loading) return <tr><td><p>Loading...</p></td></tr>;
                                if (error) return  <tr><td><p>Error...</p></td></tr>;

                                return data.patients.map(({ id, lastname, firstname,patientNo }) => (
                                    <tr key={id}>
                                        <td>{id} </td>
                                        <td>{lastname} </td>
                                        <td>{firstname} </td>
                                        <td>{patientNo}</td>
                                    </tr>
                                ));
                            }}
                        </Query>
                        </tbody>
                    </table>

                    <legend>Form Sample</legend>
                    <Mutation
                        mutation={ADDPATIENT}
                        update={(cache, { data: { upsertPatient } }) => {


                            const { patients } = cache.readQuery({ query: GETPATIENTS });

                            cache.writeQuery({
                                query: GETPATIENTS,
                                data: { patients: patients.concat(upsertPatient) },
                            });

                        }}

                    >
                        {(addPatient, { data }) => {

                            return  <form
                                onSubmit={e => {
                                    e.preventDefault();

                                    addPatient({ variables: { fields: this.state } });
                                    this.setState({
                                        lastname:'',
                                        firstname:''
                                    })
                                }}
                            >

                                <p>Lastname: <input value={this.state.lastname} type={"text"} onChange={(e)=>{

                                    this.setState({
                                        lastname:e.target.value
                                    })
                                }
                                }/></p>

                                <p>Firstname: <input value={this.state.firstname} type={"text"} onChange={(e)=>{
                                    this.setState({
                                        firstname:e.target.value
                                    })
                                }
                                }/></p>


                                <button type="submit">Add Patient</button>


                            </form>
                        }
                        }


                    </Mutation>


                    <hr/>
                    <h4>Patient List Pagination</h4>


                    <Query query={PATIENTSBYPAGE}
                           variables={{ first:3 , after:"0"}}
                    >
                        {({ data , loading,error, fetchMore }) => {

                            if (loading) return  <p>Loading...</p> ;
                            if (error) return   <p>Error...</p> ;
                            let patients = data.patients.edges.map((item)=>{ return item.node});


                            let forrender=   patients.map(({ id, lastname, firstname,patientNo }) => (
                                <tr key={id}>
                                    <td>{id} </td>
                                    <td>{lastname} </td>
                                    <td>{firstname} </td>
                                    <td>{patientNo}</td>
                                </tr>
                            ));


                            return <div>
                                <table border="1">
                                    <tbody>
                                    {forrender}
                                    </tbody>
                                </table>
                                <button type={"button"} onClick={(e)=>{

                                    fetchMore({
                                        variables: {
                                            after: data.patients.pageInfo.endCursor
                                        },
                                        updateQuery:(previousResult, { fetchMoreResult }) => {

                                            const newEdges = fetchMoreResult.patients.edges;
                                            const pageInfo = fetchMoreResult.patients.pageInfo;

                                            console.log(pageInfo)
                                            return newEdges.length
                                                ? {
                                                    // Put the new records at the end of the list and update `pageInfo`
                                                    // so we have the new `endCursor` and `hasNextPage` values
                                                    patients: {
                                                        __typename: previousResult.patients.__typename,
                                                        edges: [...previousResult.patients.edges, ...newEdges],
                                                        pageInfo
                                                    }
                                                }
                                                : previousResult;

                                        }
                                    })
                                }
                                }>Load More</button>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>

                                    </div>

                                }
                                }


                            </Query>
                        </ApolloProvider>
                        </div>
                        )
        }
}

export default App;
