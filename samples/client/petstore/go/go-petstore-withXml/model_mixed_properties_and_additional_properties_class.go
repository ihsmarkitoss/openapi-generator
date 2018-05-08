/*
 * OpenAPI Petstore
 *
 * This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
 *
 * API version: 1.0.0
 * Generated by: OpenAPI Generator (https://openapi-generator.tech)
 */

package petstore
import (
	"time"
)

type MixedPropertiesAndAdditionalPropertiesClass struct {
	Uuid string `json:"uuid,omitempty" xml:"uuid"`
	DateTime time.Time `json:"dateTime,omitempty" xml:"dateTime"`
	Map map[string]Animal `json:"map,omitempty" xml:"map"`
}
