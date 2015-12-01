package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.TypeRef;

import java.util.Map;
import io.swagger.client.model.Order;

import java.util.*;
import feign.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2015-12-01T16:10:23.565+08:00")
public interface StoreApi extends io.swagger.client.ApiClient.Api {


  /**
   * Returns pet inventories by status
   * Returns a map of status codes to quantities
   * @return Map<String, Integer>
   */
  @RequestLine("GET /store/inventory")
  @Headers({
    
  })
  Map<String, Integer> getInventory () throws ApiException;
  
  /**
   * Place an order for a pet
   * 
   * @param body order placed for purchasing the pet
   * @return Order
   */
  @RequestLine("POST /store/order")
  @Headers({
    
  })
  Order placeOrder (@Param("body") Order body) throws ApiException;
  
  /**
   * Find purchase order by ID
   * For valid response try integer IDs with value &lt;= 5 or &gt; 10. Other values will generated exceptions
   * @param orderId ID of pet that needs to be fetched
   * @return Order
   */
  @RequestLine("GET /store/order/{orderId}")
  @Headers({
    
  })
  Order getOrderById (@Param("orderId") String orderId) throws ApiException;
  
  /**
   * Delete purchase order by ID
   * For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors
   * @param orderId ID of the order that needs to be deleted
   * @return void
   */
  @RequestLine("DELETE /store/order/{orderId}")
  @Headers({
    
  })
  void deleteOrder (@Param("orderId") String orderId) throws ApiException;
  
}
