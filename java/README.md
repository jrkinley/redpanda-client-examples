# Redpanda Java Client Examples

## Set environment variables

```shell
export REDPANDA_BROKERS="<seed_address>"
export REDPANDA_SCHEMA_REGISTRY="<schema_registry_url>"
export REDPANDA_USERNAME="<sasl_username>"
export REDPANDA_PASSWORD="<sasl_password>"
```

## Delete existing schema

```shell
curl -X DELETE -u "${REDPANDA_USERNAME}:${REDPANDA_PASSWORD}" \
    ${REDPANDA_SCHEMA_REGISTRY}/subjects/nasdaq_historical_avro-value | jq .

curl -X DELETE -u "${REDPANDA_USERNAME}:${REDPANDA_PASSWORD}" \
    ${REDPANDA_SCHEMA_REGISTRY}/subjects/nasdaq_historical_proto-value | jq .
```

## Run Protobuf example

```shell
mvn clean compile assembly:single
java -cp target/protobuf-example-1.0.0-jar-with-dependencies.jar \
    com.redpanda.ProtobufExample \
    -s "${REDPANDA_BROKERS}" \
    -r "${REDPANDA_SCHEMA_REGISTRY}" \
    -u "${REDPANDA_USERNAME}" -p "${REDPANDA_PASSWORD}"
```
