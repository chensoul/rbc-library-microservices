# rbc-library-microservices

This project which was forked from https://github.com/ProductDock demonstrates how to implement OAuth2 authentication in
a Spring Cloud MicroService Project using OAuth2
login with Google.

This project consists of several microservices and a front ui app.

- [rbc-library-gateway](https://github.com/ProductDock/rbc-library-gateway)
- [rbc-library-catalog](https://github.com/ProductDock/rbc-library-catalog)
- [rbc-library-inventory](https://github.com/ProductDock/rbc-library-inventory)
- [rbc-library-notification](https://github.com/ProductDock/rbc-library-notification)
- [rbc-library-rental](https://github.com/ProductDock/rbc-library-rental)
- [rbc-library-search](https://github.com/ProductDock/rbc-library-search)
- [rbc-library-user-profiles](https://github.com/ProductDock/rbc-library-user-profiles)
- [rbc-library-ui](https://github.com/ProductDock/rbc-library-ui)

## Features

- Spring Cloud Gateway
- Spring OAuth2 client with Google
- Elasticsearch
- MongoDB
- Kafka

## Prerequisites

- Java 17 or later
- Maven
- Google Cloud account (for Google OAuth)
- NodeJs

## Google OAuth2 Setup

1. Go to [Google Cloud Console](https://console.cloud.google.com/)

2. Create a new project or select an existing one

3. Configure the OAuth consent screen:
    - Go to "APIs & Services" > "OAuth consent screen"
    - Choose "External" user type
    - Fill in required information:
        - App name
        - User support email
        - Developer contact information
    - Add scopes: email, profile, openid
    - Add test users if using external user type

4. Create OAuth2 credentials:
    - Go to "APIs & Services" > "Credentials"
    - Click "Create Credentials" > "OAuth client ID"
    - Choose "Web application"
    - Add these URLs:
      ```
      Authorized JavaScript origins:
      http://localhost:3000
 
      Authorized redirect URIs:
      http://localhost:3000/login/oauth2/code/google
      ```
    - Note your client ID and client secret

## GitHub OAuth Setup

1. Go to [GitHub Developer Settings](https://github.com/settings/developers)

2. Click "New OAuth App"

3. Fill in the application details:
   ```
   Application name: Your App Name
   Homepage URL: http://localhost:3000
   Authorization callback URL: http://localhost:3000/login/oauth2/code/github
   ```

4. Register the application

5. Note your client ID and generate a client secret
