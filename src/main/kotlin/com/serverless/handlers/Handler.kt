package com.serverless.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.serverless.responses.ApiGatewayResponse
import com.serverless.responses.Response
import org.apache.logging.log4j.LogManager

class Handler:RequestHandler<Map<String, Any>, ApiGatewayResponse> {
  override fun handleRequest(input:Map<String, Any>, context:Context): ApiGatewayResponse {
    LOG.info("received: " + input.keys.toString())

    return ApiGatewayResponse.build {
      statusCode = 200
      rawBody = input.keys.toString()
      headers = mapOf("X-Powered-By" to "AWS Lambda & serverless")
    }
  }

  companion object {
    private val LOG = LogManager.getLogger(Handler::class.java)
  }
}
