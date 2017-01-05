# coding: utf-8

"""
    Swagger Petstore

    This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\

    OpenAPI spec version: 1.0.0
    Contact: apiteam@swagger.io
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from pprint import pformat
from six import iteritems
import re


class ArrayTest(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self, array_of_string=None, array_array_of_integer=None, array_array_of_model=None):
        """
        ArrayTest - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'array_of_string': 'list[str]',
            'array_array_of_integer': 'list[list[int]]',
            'array_array_of_model': 'list[list[ReadOnlyFirst]]'
        }

        self.attribute_map = {
            'array_of_string': 'array_of_string',
            'array_array_of_integer': 'array_array_of_integer',
            'array_array_of_model': 'array_array_of_model'
        }

        self._array_of_string = array_of_string
        self._array_array_of_integer = array_array_of_integer
        self._array_array_of_model = array_array_of_model

    @property
    def array_of_string(self):
        """
        Gets the array_of_string of this ArrayTest.

        :return: The array_of_string of this ArrayTest.
        :rtype: list[str]
        """
        return self._array_of_string

    @array_of_string.setter
    def array_of_string(self, array_of_string):
        """
        Sets the array_of_string of this ArrayTest.

        :param array_of_string: The array_of_string of this ArrayTest.
        :type: list[str]
        """

        self._array_of_string = array_of_string

    @property
    def array_array_of_integer(self):
        """
        Gets the array_array_of_integer of this ArrayTest.

        :return: The array_array_of_integer of this ArrayTest.
        :rtype: list[list[int]]
        """
        return self._array_array_of_integer

    @array_array_of_integer.setter
    def array_array_of_integer(self, array_array_of_integer):
        """
        Sets the array_array_of_integer of this ArrayTest.

        :param array_array_of_integer: The array_array_of_integer of this ArrayTest.
        :type: list[list[int]]
        """

        self._array_array_of_integer = array_array_of_integer

    @property
    def array_array_of_model(self):
        """
        Gets the array_array_of_model of this ArrayTest.

        :return: The array_array_of_model of this ArrayTest.
        :rtype: list[list[ReadOnlyFirst]]
        """
        return self._array_array_of_model

    @array_array_of_model.setter
    def array_array_of_model(self, array_array_of_model):
        """
        Sets the array_array_of_model of this ArrayTest.

        :param array_array_of_model: The array_array_of_model of this ArrayTest.
        :type: list[list[ReadOnlyFirst]]
        """

        self._array_array_of_model = array_array_of_model

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()

    def __eq__(self, other):
        """
        Returns true if both objects are equal
        """
        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
