/**
 * OpenAPI Petstore
 * This is a sample server Petstore server. For this sample, you can use the api key `special-key` to test the authorization filters.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: team@openapitools.org
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 */

package io.swagger.server.model
import java.util.Date

case class Order(
  id: Option[Long],

  petId: Option[Long],

  quantity: Option[Int],

  shipDate: Option[Date],

  /* Order Status */
  status: Option[String],

  complete: Option[Boolean]

 )
