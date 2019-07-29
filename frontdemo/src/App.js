import React from 'react';
import ApolloClient from "apollo-boost";
import { Query , ApolloProvider} from "react-apollo";
import './App.css';
import { gql } from "apollo-boost";

function App() {

    const client = new ApolloClient({
        uri: "http://localhost:8080/graphql"
    });


    const PatientsQuery = gql`
      
       query  
          patientPage($first:Int!, $after: String!) {
           patientsByPage(first:$first,after:$after){
               edges{
                   node {
                       id
                       lastname
                       firstname
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



    return (
    <div className="App">
        <ApolloProvider client={client}>
            <h4>Patient List</h4>

            <table border="1">
                <tbody>
                <Query
                    query={gql`

                   {
                      patients {
                        id
                        lastname
                        firstname
                      }
                  }

                `}
                >
                    {({ loading, error, data }) => {
                        if (loading) return <p>Loading...</p>;
                        if (error) return <p>Error :(</p>;

                        return data.patients.map(({ id, lastname, firstname }) => (
                            <tr key={id}>
                                <td>{id} </td>
                                <td>{lastname} </td>
                                <td>{firstname} </td>
                            </tr>
                        ));
                    }}
                </Query>
                </tbody>
            </table>



            <h4>Patient List Pagination</h4>
            <table border="1">
                <tbody>
            <Query query={PatientsQuery}
                   variables={{ first:3 , after:"0"}}
               >
                {({ data , loading, fetchMore }) => {

                    console.log(data)

                    return <tr>

                    </tr>
                }
                }
            </Query>
                </tbody>
            </table>

        </ApolloProvider>

    </div>
  );
}

export default App;
