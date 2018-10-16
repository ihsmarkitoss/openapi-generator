/* 
 * OpenAPI Petstore
 *
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * OpenAPI spec version: 1.0.0
 * 
 * Generated by: https://github.com/openapitools/openapi-generator.git
 */

using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Runtime.Serialization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using OpenAPIDateConverter = Org.OpenAPITools.Client.OpenAPIDateConverter;

namespace Org.OpenAPITools.Model
{
    /// <summary>
    /// Must be named &#x60;File&#x60; for test.
    /// </summary>
    [DataContract]
    public partial class File :  IEquatable<File>
    {
        /// <summary>
        /// Initializes a new instance of the <see cref="File" /> class.
        /// </summary>
        /// <param name="sourceURI">Test capitalization.</param>
        public File(string sourceURI = default(string))
        {
            this.SourceURI = sourceURI;
        }
        
        /// <summary>
        /// Test capitalization
        /// </summary>
        /// <value>Test capitalization</value>
        [DataMember(Name="sourceURI", EmitDefaultValue=false)]
        public string SourceURI { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class File {\n");
            sb.Append("  SourceURI: ").Append(SourceURI).Append("\n");
            sb.Append("}\n");
            return sb.ToString();
        }
  
        /// <summary>
        /// Returns the JSON string presentation of the object
        /// </summary>
        /// <returns>JSON string presentation of the object</returns>
        public virtual string ToJson()
        {
            return JsonConvert.SerializeObject(this, Formatting.Indented);
        }

        /// <summary>
        /// Returns true if objects are equal
        /// </summary>
        /// <param name="input">Object to be compared</param>
        /// <returns>Boolean</returns>
        public override bool Equals(object input)
        {
            return this.Equals(input as File);
        }

        /// <summary>
        /// Returns true if File instances are equal
        /// </summary>
        /// <param name="input">Instance of File to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(File input)
        {
            if (input == null)
                return false;

            return 
                (
                    this.SourceURI == input.SourceURI ||
                    (this.SourceURI != null &&
                    this.SourceURI.Equals(input.SourceURI))
                );
        }

        /// <summary>
        /// Gets the hash code
        /// </summary>
        /// <returns>Hash code</returns>
        public override int GetHashCode()
        {
            unchecked // Overflow is fine, just wrap
            {
                int hashCode = 41;
                if (this.SourceURI != null)
                    hashCode = hashCode * 59 + this.SourceURI.GetHashCode();
                return hashCode;
            }
        }
    }

}
