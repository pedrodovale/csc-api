## Keycloak

[Keycloak](https://www.keycloak.org/) will act as the trusted [OAuth2.0](https://oauth.net/2/) third-party for the CSC API domain.

It provides two things to this solution:
* the means for a client to obtain an access token;
* the means for the resource server, i.e. csc-api, to validate (formally referred to as introspect) access tokens sent in the clients request HTTP Authorization Header.

This folder contains configuration files to be imported to a clean keycloak instance.

---

### How?
The steps to obtain a working Keycloak are the following:
1. Install an keycloak application: it will be deployd in a wildfly container
1. Import the JSON file containing the necessary configuration

The next sections describe what customizations were made to a clean keycloak instance. These customizations are all in a JSON file. 

#### Realm

The `csc-api` realm where the client scope and clients will be configured.
The name of the real will have an impact on the `authorization`, `token` and `instrospection URLs:

```
http://localhost:8080/auth/realms/csc-api/protocol/openid-connect/auth
http://localhost:8080/auth/realms/csc-api/protocol/openid-connect/token
http://localhost:8080/auth/realms/csc-api/protocol/openid-connect/token/introspect
```

#### Client Scope

The client scope `service` to be added as the scope for issued access tokens to CSC-API clients

#### Postman Client

A client `csc-api-postman-client`:

* A confidential client with Client Id and Secret Authentication method (see `Credentials` tab)
* With only OAuth2.0 Client Credentials flow enabled, i.e. checked `Service Accounts Enabled`
* With optional scope `service` (see `Client Scopes` tab)

[1] Client id and client secret must match the postman configuration

#### Spring boot Resource Server

A client `csc-api-resource-server`

* A confidential client with Client Id and Secret Authentication method[2] (see `Credentials` tab)
* With no authentication flows (the resource server does not need to obtain an access token in order to introspect access tokens) 

[2] Client id and client secret must match the Spring boot configuration

Useful links:
* [Getting Started on Keycloak](https://www.keycloak.org/getting-started/getting-started-zip)
* [Exporting and Importing keycloak DB](https://www.keycloak.org/docs/6.0/server_admin/#_export_import)
* [Deploy keycloak in other than default port (to avoid port conflicts)](https://www.keycloak.org/docs/latest/getting_started/#adjusting-the-port-used-by-keycloak)
---

### Export

The command used to export the csc-api realm that results in the JSON file csc-realm.json was the following:

```bash
./standalone.sh \
-Djboss.socket.binding.port-offset=100 \
-Dkeycloak.migration.provider=singleFile \
-Dkeycloak.migration.realmName=csc-api \
-Dkeycloak.migration.usersExportStrategy=REALM_FILE \
-Dkeycloak.migration.action=export \
-Dkeycloak.migration.file=<A_PATH>/csc-realm.json
```
NOTE: executed from Keycloak's bin folder

---

### Import

To import keycloak configuration that will work with the existing Spring Boot resource server (the CSC-API), and the Postman collection with requests (the client of the CSC-API) 

```bash
./standalone.sh \
-Dkeycloak.migration.action=import \
-Dkeycloak.migration.provider=singleFile \
-Dkeycloak.migration.file=<CONFIGURATIONS_ZIP_FOLDER>/keycloak/csc-realm.json \
-Dkeycloak.migration.strategy=OVERWRITE_EXISTING
```

NOTE: execute from Keycloak's `bin` folder \
NOTE 2: the `csc-realm.json` is either on the project `/configurations/keycloak/` folder or in the configurations module is built, the resulting zip will include the same file in the `/keycloak/` folder  