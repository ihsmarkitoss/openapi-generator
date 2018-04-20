/**
* Swagger Petstore
* This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).  For this sample, you can use the api key `special-key` to test the authorization filters.
*
* OpenAPI spec version: 1.0.0
* Contact: apiteam@swagger.io
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
/*
 * StoreApi.h
 *
 * 
 */

#ifndef StoreApi_H_
#define StoreApi_H_


#include <pistache/endpoint.h>
#include <pistache/http.h>
#include <pistache/router.h>
#include <pistache/http_headers.h>

#include "Order.h"
#include <map>
#include <string>

namespace io {
namespace swagger {
namespace server {
namespace api {

using namespace io::swagger::server::model;

class  StoreApi {
public:
    StoreApi(Pistache::Address addr);
    virtual ~StoreApi() {};
    void init(size_t thr);
    void start();
    void shutdown();

    const std::string base = "/v2";

private:
    void setupRoutes();

    void delete_order_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void get_inventory_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void get_order_by_id_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void place_order_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);
    void store_api_default_handler(const Pistache::Rest::Request &request, Pistache::Http::ResponseWriter response);

    std::shared_ptr<Pistache::Http::Endpoint> httpEndpoint;
    Pistache::Rest::Router router;


    /// <summary>
    /// Delete purchase order by ID
    /// </summary>
    /// <remarks>
    /// For valid response try integer IDs with value &lt; 1000. Anything above 1000 or nonintegers will generate API errors
    /// </remarks>
    /// <param name="orderId">ID of the order that needs to be deleted</param>
    virtual void delete_order(const std::string &orderId, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Returns pet inventories by status
    /// </summary>
    /// <remarks>
    /// Returns a map of status codes to quantities
    /// </remarks>
    virtual void get_inventory(Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Find purchase order by ID
    /// </summary>
    /// <remarks>
    /// For valid response try integer IDs with value &lt;&#x3D; 5 or &gt; 10. Other values will generated exceptions
    /// </remarks>
    /// <param name="orderId">ID of pet that needs to be fetched</param>
    virtual void get_order_by_id(const int64_t &orderId, Pistache::Http::ResponseWriter &response) = 0;

    /// <summary>
    /// Place an order for a pet
    /// </summary>
    /// <remarks>
    /// 
    /// </remarks>
    /// <param name="order">order placed for purchasing the pet</param>
    virtual void place_order(const std::shared_ptr<Order> &order, Pistache::Http::ResponseWriter &response) = 0;

};

}
}
}
}

#endif /* StoreApi_H_ */

