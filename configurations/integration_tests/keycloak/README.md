## Keycloak

[Keycloak](https://www.keycloak.org/) will act as the trusted third-party for the CSC API domain.

It provides two things to this solution:
1. the means for a client to obtain an access token;
1. the means for the resource server, i.e. csc-api, to validate (formally referred to as introspect) access tokens sent in the clients' requests.

This folder contains the client and resource server files to be imported on keycloak.

Useful links:
* [Getting Started on Keycloak](https://www.keycloak.org/getting-started/getting-started-zip)
* [Creating the 'service' scope]()