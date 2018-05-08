/**
 * OpenAPI Petstore
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * OpenAPI spec version: 1.0.0
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 *
 * OpenAPI Generator version: 3.0.0-SNAPSHOT
 *
 * Do not edit the class manually.
 *
 */

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module.
    define(['ApiClient'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    module.exports = factory(require('../ApiClient'));
  } else {
    // Browser globals (root is window)
    if (!root.OpenApiPetstore) {
      root.OpenApiPetstore = {};
    }
    root.OpenApiPetstore.OuterComposite = factory(root.OpenApiPetstore.ApiClient);
  }
}(this, function(ApiClient) {
  'use strict';




  /**
   * The OuterComposite model module.
   * @module model/OuterComposite
   * @version 1.0.0
   */

  /**
   * Constructs a new <code>OuterComposite</code>.
   * @alias module:model/OuterComposite
   * @class
   */
  var exports = function() {
    var _this = this;




  };

  /**
   * Constructs a <code>OuterComposite</code> from a plain JavaScript object, optionally creating a new instance.
   * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
   * @param {Object} data The plain JavaScript object bearing properties of interest.
   * @param {module:model/OuterComposite} obj Optional instance to populate.
   * @return {module:model/OuterComposite} The populated <code>OuterComposite</code> instance.
   */
  exports.constructFromObject = function(data, obj) {
    if (data) {
      obj = obj || new exports();

      if (data.hasOwnProperty('my_number')) {
        obj['my_number'] = 'Number'.constructFromObject(data['Number']);
      }
      if (data.hasOwnProperty('my_string')) {
        obj['my_string'] = 'String'.constructFromObject(data['String']);
      }
      if (data.hasOwnProperty('my_boolean')) {
        obj['my_boolean'] = 'Boolean'.constructFromObject(data['Boolean']);
      }
    }
    return obj;
  }

  /**
   * @member {Number} my_number
   */
  exports.prototype['my_number'] = undefined;
  /**
   * @member {String} my_string
   */
  exports.prototype['my_string'] = undefined;
  /**
   * @member {Boolean} my_boolean
   */
  exports.prototype['my_boolean'] = undefined;



  return exports;
}));


