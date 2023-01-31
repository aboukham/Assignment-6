// WAA Lab 6

// Summary

This application was designed with spring-boot and enabled token-based authentication & authorization with Spring Security & JWT.

// To ensure maximum security, we added multiple things to the Refresh Token, including a different value and expiration time than the Access Token:

1) The application settings should include secret keys.
2)To avoid using an access token as a refresh token, use different secret keys for them.
3) If someone gains access to refresh tokens, we can force all users to log out or revoke all active refresh tokens for specific users.
4) If the user attempts to refresh his access token with an expired refresh token, the app will remove the token from the database and ask the user to re-login.
5) The previous access token does not need to be sent to refresh the access token. The refresh token will be enough.


Group Members:

Baha Amayreh
Abdelaziz Aboukhame



