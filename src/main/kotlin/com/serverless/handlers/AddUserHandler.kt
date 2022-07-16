package com.serverless.handlers

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.responses.ApiGatewayResponse
import org.apache.logging.log4j.LogManager


class AddUserHandler: RequestHandler<Map<String, Any>, ApiGatewayResponse> {
    override fun handleRequest(input:Map<String, Any>, context: Context): ApiGatewayResponse {
        LOG.info("received: " + input.keys.toString())
        val client = AmazonDynamoDBClientBuilder.defaultClient()
        LOG.info(client)
        val dynamoDB = DynamoDB(client)
        val table: Table = dynamoDB.getTable(System.getenv("DYNAMODB_USER_TABLE"))

        val item = Item()
            .withString("primary_key", "Dimuthu11").withString("email", "dimuthu@fjfj.ll")
        table.putItem(item)

        return ApiGatewayResponse.build {
            statusCode = 201
            rawBody = "User Added"
            headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(Handler::class.java)
    }
}