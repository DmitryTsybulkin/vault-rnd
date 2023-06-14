
# https://hub.docker.com/_/vault

docker run --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=secret_token' -p 8200:8200 -d --name=dev-vault vault

# vault kv put secret/github github.oauth2.key=foobar